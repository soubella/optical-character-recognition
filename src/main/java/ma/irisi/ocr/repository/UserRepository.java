package ma.irisi.ocr.repository;

import ma.irisi.ocr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "*")
@RepositoryRestResource
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByEmailAndPassword(String email,String password);

}
