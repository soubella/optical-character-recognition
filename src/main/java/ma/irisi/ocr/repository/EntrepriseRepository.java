package ma.irisi.ocr.repository;

import ma.irisi.ocr.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
 public interface EntrepriseRepository extends JpaRepository<Entreprise,Long> {
}
