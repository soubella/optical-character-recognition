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
   private String keyMeta;
   private String valueMeta;
   @ManyToOne
   private UploadedFile uploadedFile;

   public MetaData() {
   }

   public MetaData(Long id, String keyMeta, String valueMeta) {
      this.id = id;
      this.keyMeta = keyMeta;
      this.valueMeta = valueMeta;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getKeyMeta() {
      return keyMeta;
   }

   public void setKeyMeta(String key) {
      this.keyMeta = key;
   }

   public String getValueMeta() {
      return valueMeta;
   }

   public void setValueMeta(String value) {
      this.valueMeta = value;
   }

   public UploadedFile getUploadedFile() {
      return uploadedFile;
   }

   public void setUploadedFile(UploadedFile uploadedFile) {
      this.uploadedFile = uploadedFile;
   }
}