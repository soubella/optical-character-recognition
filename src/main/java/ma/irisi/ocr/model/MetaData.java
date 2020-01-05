package ma.irisi.ocr.model;

import javax.persistence.*;

/***********************************************************************
 * Module:  MetaData.java
 * Author:  ELIOT
 * Purpose: Defines the Class MetaData
 ***********************************************************************/

@Entity
public class MetaData {
   @Id
   @GeneratedValue(strategy= GenerationType.AUTO)
   private Long id;
   private String key;
   private String value;
   @ManyToOne
   private UploadedFile uploadedFile;

   public MetaData() {
   }

   public MetaData(Long id, String key, String value) {
      this.id = id;
      this.key = key;
      this.value = value;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getKey() {
      return key;
   }

   public void setKey(String key) {
      this.key = key;
   }

   public String getValue() {
      return value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public UploadedFile getUploadedFile() {
      return uploadedFile;
   }

   public void setUploadedFile(UploadedFile uploadedFile) {
      this.uploadedFile = uploadedFile;
   }
}