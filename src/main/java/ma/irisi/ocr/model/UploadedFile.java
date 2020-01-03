package ma.irisi.ocr.model; /***********************************************************************
 * Module:  UploadedFile.java
 * Author:  ELIOT
 * Purpose: Defines the Class UploadedFile
 ***********************************************************************/

import javax.persistence.OneToMany;
import java.util.*;

public class UploadedFile {
   private Long id;
   private String type;
   private Long size;
   @OneToMany
   public List<MetaData> metaData;

   public UploadedFile(Long id, String type, Long size) {
      this.id = id;
      this.type = type;
      this.size = size;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public Long getSize() {
      return size;
   }

   public void setSize(Long size) {
      this.size = size;
   }

   public List<MetaData> getMetaData() {
      return metaData;
   }

   public void setMetaData(List<MetaData> metaData) {
      this.metaData = metaData;
   }
}