package ma.irisi.ocr.repository;

import ma.irisi.ocr.model.MetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RepositoryRestResource
public interface MetaDataRepository extends JpaRepository<MetaData,Long> {
}
