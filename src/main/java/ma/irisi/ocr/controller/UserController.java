package ma.irisi.ocr.controller;
import ma.irisi.ocr.model.Entreprise;
import ma.irisi.ocr.model.Plan;
import ma.irisi.ocr.model.Role;
import ma.irisi.ocr.model.User;
import ma.irisi.ocr.repository.EntrepriseRepository;
import ma.irisi.ocr.repository.RoleRepository;
import ma.irisi.ocr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    @RequestMapping("test")
    public String test() {
        return "Hello dear";
    }

    @GetMapping("/users")
    public List<User> index(){
        return userRepository.findAll();
    }
    @GetMapping("/user/{id}")
    public Optional<User> show(@PathVariable String id){
        Long userId = Long.parseLong(id);
        return userRepository.findById(userId);
    }
    @DeleteMapping("user/{id}")
    public boolean delete(@PathVariable String id){
        Long userId = Long.parseLong(id);
        userRepository.deleteById(userId);
        return true;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path= "/users/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addUser(@RequestBody User user)
            throws Exception
    {

        System.out.println(user.getId());
        //add resource
        Long ent =new Long(1);
        Double prix = new Double(200);
        /*   Optional<Entreprise> entreprise = entrepriseRepository.findById(ent) ;*/
        user.setEntreprise((new Entreprise(ent,"irisi",new Plan(ent,"",prix))));
        user.setRole(new Role(ent,"Admin"));

        userRepository.save(user);


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
