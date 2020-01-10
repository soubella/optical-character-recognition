package ma.irisi.ocr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.nio.file.Path;

@RestController
public class UploadedFileController {

    @Autowired
    private FileController fileService;
    @PostMapping("/api/upload")
    public ModelAndView uploadFile(@RequestParam("file") MultipartFile file) {
        FileController resultInfo = new FileController() ;

        ModelAndView modelAndView = new ModelAndView("files");
        modelAndView.addObject("uploadMessage", resultInfo);
        modelAndView.addObject("filesMetadata", fileService);
        return modelAndView;
    }
}
