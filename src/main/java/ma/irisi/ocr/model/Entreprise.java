package ma.irisi.ocr.model; /***********************************************************************
 * Module:  Entreprise.java
 * Author:  samoou
 * Purpose: Defines the Class Entreprise
 ***********************************************************************/

import javax.persistence.*;
import java.util.*;

@Entity
public class Entreprise {
   @Id
   @GeneratedValue(strategy= GenerationType.AUTO)
   private Long id;
   private String name;
   @OneToMany(mappedBy = "entreprise")
   public List<User> user;
   @ManyToOne
   public Plan plan;
   @OneToMany(mappedBy = "entreprise")
   public List<UploadedFile> uploadedFile;

   public Entreprise() {
   }

   public Entreprise(Long id, String name, List user, Plan plan) {
      this.id = id;
      this.name = name;
      this.user = user;
      this.plan = plan;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public List getUser() {
      return user;
   }

   public void setUser(List user) {
      this.user = user;
   }

   public Plan getPlan() {
      return plan;
   }

   public void setPlan(Plan plan) {
      this.plan = plan;
   }

   public List<UploadedFile> getUploadedFile() {
      return uploadedFile;
   }

   public void setUploadedFile(List<UploadedFile> uploadedFile) {
      this.uploadedFile = uploadedFile;
   }
}
