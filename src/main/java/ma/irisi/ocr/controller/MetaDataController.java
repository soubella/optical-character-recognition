package ma.irisi.ocr.controller;
import ma.irisi.ocr.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MetaDataController {

    @Autowired
    private FileController fileService;

    @Autowired
    private UserController userService;


    @PostMapping(value = "/addNote")
    public ModelAndView createNote(@RequestParam(value = "subject") String subject, @RequestParam(value = "text") String text, @RequestParam(value = "id") String id) {
        ModelAndView modelAndView = new ModelAndView("file");
        modelAndView.addObject("file", fileService);
        modelAndView.addObject("metadata", fileService);
        return modelAndView;
    }

    @GetMapping(value = "/showAllMetadata")
    public String getAllNotes() {
        return userService.userRepository.toString();
    }
}
