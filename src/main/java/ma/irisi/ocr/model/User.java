package ma.irisi.ocr.model; /***********************************************************************
 * Module:  User.java
 * Author:  ELIOT
 * Purpose: Defines the Class User
 ***********************************************************************/

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class User {
   @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)
   private Long id;
   private String firstName;
   private String lastName;
   @Column(unique=true)
   private String email;
   private String password;
   private boolean state;
   @ManyToOne
   @JsonIgnore
   private Role role;
   @ManyToOne
   @JsonIgnore
   private  Entreprise entreprise;

   public User() {   
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public boolean isState() {
      return state;
   }

   public void setState(boolean state) {
      this.state = state;
   }

   public Role getRole() {
      return role;
   }

   public void setRole(Role role) {
      this.role = role;
   }

   public Entreprise getEntreprise() {
      return entreprise;
   }

   public void setEntreprise(Entreprise entreprise) {
      this.entreprise = entreprise;
   }
}