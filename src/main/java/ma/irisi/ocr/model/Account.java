package ma.irisi.ocr.model; /***********************************************************************
 * Module:  Compte.java
 * Author:  ELIOT
 * Purpose: Defines the Class Compte
 ***********************************************************************/

import javax.persistence.OneToOne;
import java.util.*;

public class Account {
   private Long id;
   private String email;
   private String password;
   private boolean state;
   @OneToOne
   public Role role;

   public Account(Long id, String email, String password, boolean state) {
      this.id = id;
      this.email = email;
      this.password = password;
      this.state = state;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
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
}