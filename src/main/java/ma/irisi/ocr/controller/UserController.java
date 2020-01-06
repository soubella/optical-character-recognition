package ma.irisi.ocr.controller;
import ma.irisi.ocr.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@RestController
public class UserController {
    @RequestMapping("test")
    public String test() {
        return "Hello dear";
    }

    @RequestMapping(
            value = "/users/registder",
            method = RequestMethod.POST)
    public void process(@RequestBody User user)
            throws Exception {

        System.out.println("user");

    }

    private User user;
    @CrossOrigin(origins = "*")
    @PostMapping(path= "/users/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addUser(@RequestBody User user)
            throws Exception
    {
        //Generate resource id

        user.setId((long) 1);
        System.out.println(user.getId());
        //add resource
        //User.addUser(user);

        //Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        //Send location in response
        return ResponseEntity.created(location).build();
    }

}
