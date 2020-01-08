package ma.irisi.ocr.controller;
import ma.irisi.ocr.model.UploadedFile;
import ma.irisi.ocr.model.User;
import ma.irisi.ocr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users/login")
    public String findUser(@RequestParam("email") String email, @RequestParam("password") String password) {
        System.out.println(email);
        System.out.println(password);
        User user = userRepository.findUserByEmailAndPassword(email,password);
        if(user==null)
            return "NO";
        return ""+user.getId()+"-"+user.getEntreprise().getId();
    }

    @GetMapping("/users/list")
    public List findAll(){
        return userRepository.findAll();
    }

}
