package ma.irisi.ocr.controller;
import ma.irisi.ocr.model.Entreprise;
import ma.irisi.ocr.model.Plan;
import ma.irisi.ocr.model.Role;
import ma.irisi.ocr.model.User;
import ma.irisi.ocr.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class RoleController {
    @Autowired
    RoleRepository roleRepository ;

    @CrossOrigin(origins = "*")
    @PostMapping(path= "/roles/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addUser(@RequestBody Role role)
            throws Exception
    {
        //add resource
        Long ent =new Long(1);
        roleRepository.save(role);
        //Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(role.getId())
                .toUri();

        //Send location in response
        return ResponseEntity.created(location).build();
    }

}
