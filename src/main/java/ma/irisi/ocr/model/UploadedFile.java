package ma.irisi.ocr.model; /***********************************************************************
 * Module:  UploadedFile.java
 * Author:  ELIOT
 * Purpose: Defines the Class UploadedFile
 ***********************************************************************/

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
public class UploadedFile {
   @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)
   private Long id;
   private String type;
   private Long size;
   private String extension;

   @ManyToOne
   @JsonIgnore
   private Entreprise entreprise;
   @OneToMany(mappedBy = "uploadedFile")
   @JsonIgnore
   public List<MetaData> metaData;

   public UploadedFile() {
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

   public String getExtension() {
      return extension;
   }

   public void setExtension(String extension) {
      this.extension = extension;
   }

   public List<MetaData> getMetaData() {
      return metaData;
   }

   public void setMetaData(List<MetaData> metaData) {
      this.metaData = metaData;
   }

   public Entreprise getEntreprise() {
      return entreprise;
   }

   public void setEntreprise(Entreprise entreprise) {
      this.entreprise = entreprise;
   }
}