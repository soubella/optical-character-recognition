package ma.irisi.ocr.controller;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ma.irisi.ocr.model.User;
import ma.irisi.ocr.repository.EntrepriseRepository;
import ma.irisi.ocr.repository.RoleRepository;
import ma.irisi.ocr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@CrossOrigin(origins = "*")
@RestController
public class UserController {
    @Autowired
    UserRepository userRepository ;
    @Autowired
    EntrepriseRepository entrepriseRepository;
    @Autowired
    RoleRepository roleRepository ;


    @RequestMapping(value = "/users/api/{id}" ,   method = RequestMethod.GET)
    public List<User> search(@PathVariable String id){
        List<User> users = userRepository.findAll();
        List<User> users2=new ArrayList<>();
        for (User user:users) {
            if(user.getEntreprise().getId().toString().equals(id)){
                user.setEntreprise(null);
                user.setRole(null);
                users2.add(user);
            }
        }
        return users2;
    }
    @GetMapping("/user/{id}")
    public Optional<User> show(@PathVariable String id){
        Long userId = Long.parseLong(id);
        return userRepository.findById(userId);
    }


    @GetMapping("userdel/{id}")
    public boolean delete(@PathVariable String id){
        Long userId = Long.parseLong(id);
        userRepository.deleteById(userId);
        return true;
    }

    @PostMapping(path= "/users/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addUser(@RequestBody User user)
            throws Exception
    {

      /*  System.out.println(user.getId());
        //add resource
        Long ent =new Long(1);
        Double prix = new Double(200);
        Optional<Entreprise> entreprise = entrepriseRepository.findById(ent) ;
        user.setEntreprise((new Entreprise(ent,"irisi",new Plan(ent,"",prix))));
        user.setRole(new Role(ent,"Admin"));
        userReposit*/

        //Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        //Send location in response
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/users/login")
    public String findUser(@RequestParam("email") String email, @RequestParam("password") String password) {

        User user = userRepository.findUserByEmailAndPassword(email,password);
        if(user==null)
        {
            return "NO";
        }
        System.out.println(""+user.getId()+"-");
        return ""+user.getId()+"-"+user.getEntreprise().getId();
    }
    @GetMapping("user_pass/{email}/{state}")
    public String password(@PathVariable String email,@PathVariable String state){
        String AB = "0123456789abcdefghijklmnopqrstuvwxyz";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        sendMail(email,"Password for OCR Dashboard",sb.toString());
        return sb.toString();
    }
    @Autowired
    private JavaMailSender javaMailSender;
    public void sendMail(String email,String subject,String pass){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject(subject);
        msg.setText("Hello,  \nThis is your login :\nEmail: '"+email+"'"+"\nThis is your Password: '"+pass+"'");
        javaMailSender.send(msg);
        System.out.println("Email has been sent");
    }

}
