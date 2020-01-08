package ma.irisi.ocr.controller;


import ma.irisi.ocr.model.Entreprise;
import ma.irisi.ocr.model.MetaData;
import ma.irisi.ocr.model.UploadedFile;
import ma.irisi.ocr.repository.EntrepriseRepository;
import ma.irisi.ocr.repository.MetaDataRepository;
import ma.irisi.ocr.repository.UploadedFileRepository;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
@RestController
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    private static final String uploadDir = "C:\\Users\\ELIOT\\IdeaProjects\\optical-character-recognition\\src\\main\\resources\\uploads";

    @Autowired
    private UploadedFileRepository uploadedFileRepository;

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @Autowired
    private MetaDataRepository metaDataRepository;

    @PostMapping("/upload")
    public ResponseEntity uploadToLocalFileSystem(@RequestParam("file") MultipartFile file,@RequestParam("entreprise_id") String entreprise_id) throws TesseractException, IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String extension = file.getOriginalFilename().split("\\.")[1];

        UploadedFile uploadedFile = new UploadedFile();
        uploadedFile.setType(file.getContentType());
        uploadedFile.setSize(file.getSize());
        uploadedFile.setExtension(extension);
        Entreprise entreprise = entrepriseRepository.findById(new Long(entreprise_id)).orElse(null);
        uploadedFile.setEntreprise(entreprise);
        uploadedFileRepository.save(uploadedFile);

        String ocrResult = doOcr(file);
        System.out.println(ocrResult.length());
        MetaData metaData = new MetaData();
        metaData.setKeyMeta("Content");
        metaData.setValueMeta(ocrResult);
        metaData.setUploadedFile(uploadedFile);
        metaDataRepository.save(metaData);

        try {
            fileName=uploadedFile.getId().toString()+"."+extension;
            Path rootLocation=Paths.get(uploadDir);
            Files.copy(file.getInputStream(), rootLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/uploads/")
                .path(fileName)
                .toUriString();
        return ResponseEntity.ok(fileDownloadUri);
    }

    @PostMapping("/multi-upload")
    public ResponseEntity multiUpload(@RequestParam("files") MultipartFile[] files,@RequestParam("entreprise_id") String entreprise_id) {
        List<Object> fileDownloadUrls = new ArrayList<>();
        Arrays.asList(files).stream().forEach(file -> {
            try {
                fileDownloadUrls.add(uploadToLocalFileSystem(file,entreprise_id).getBody());
            } catch (TesseractException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return ResponseEntity.ok(fileDownloadUrls);
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource=null;
        Path fileStorageLocation=Paths.get(uploadDir).toAbsolutePath().normalize();;
        try {
            Path filePath = fileStorageLocation.resolve(fileName).normalize();
             resource = new UrlResource(filePath.toUri());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("files")
    public List findAll(){
        return uploadedFileRepository.findAll();
    }

    public static File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    public String doOcr(MultipartFile fileIn){
        String result="";
        Tesseract instance = new Tesseract();
        instance.setDatapath("C:\\Program Files (x86)\\Tesseract-OCR\\tessdata");
        instance.setLanguage("eng");
        instance.setHocr(false);

        try {
            File file = convert(fileIn);
            result = instance.doOCR(file);
        } catch (TesseractException | IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}