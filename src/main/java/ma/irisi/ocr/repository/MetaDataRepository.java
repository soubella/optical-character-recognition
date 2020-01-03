package ma.irisi.ocr.repository;

import ma.irisi.ocr.model.MetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetaDataRepository extends JpaRepository<MetaData,Long> {
}
