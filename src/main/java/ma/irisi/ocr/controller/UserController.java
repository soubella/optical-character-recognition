package ma.irisi.ocr.controller;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ma.irisi.ocr.model.User;
import ma.irisi.ocr.repository.EntrepriseRepository;
import ma.irisi.ocr.repository.RoleRepository;
import ma.irisi.ocr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*")
@RestController
public class UserController {
    @Autowired
    UserRepository userRepository ;
    @Autowired
    EntrepriseRepository entrepriseRepository;
    @Autowired
    RoleRepository roleRepository ;


    @RequestMapping(value = "/users/api" ,   method = RequestMethod.GET)
    public List<User> search(){
        return (List<User>) userRepository.findAll();
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


}
