package ma.irisi.ocr.model; /***********************************************************************
 * Module:  Plan.java
 * Author:  ELIOT
 * Purpose: Defines the Class Plan
 ***********************************************************************/

import javax.persistence.OneToMany;
import java.util.*;

public class Plan {
   private Long id;
   private String name;
   private Double price;
   @OneToMany
   List<Entreprise> entreprises;

   public Plan(Long id, String name, Double price) {
      this.id = id;
      this.name = name;
      this.price = price;
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

   public Double getPrice() {
      return price;
   }

   public void setPrice(Double price) {
      this.price = price;
   }

   public List<Entreprise> getEntreprises() {
      return entreprises;
   }

   public void setEntreprises(List<Entreprise> entreprises) {
      this.entreprises = entreprises;
   }
}