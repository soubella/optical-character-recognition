package ma.irisi.ocr.model; /***********************************************************************
 * Module:  Plan.java
 * Author:  ELIOT
 * Purpose: Defines the Class Plan
 ***********************************************************************/

import javax.persistence.*;
import java.util.*;
@Entity
public class Plan {
   @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)
   private Long id;
   private String name;
   private Double price;
   @OneToMany(mappedBy = "plan")
   private Set<Entreprise> entreprises;

   public Plan() {
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

   public Set<Entreprise> getEntreprises() {
      return entreprises;
   }

   public void setEntreprises(Set<Entreprise> entreprises) {
      this.entreprises = entreprises;
   }
}