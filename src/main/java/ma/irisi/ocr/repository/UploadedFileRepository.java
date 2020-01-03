package ma.irisi.ocr.repository;

import ma.irisi.ocr.model.UploadedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadedFileRepository extends JpaRepository<UploadedFile,Long> {
}
