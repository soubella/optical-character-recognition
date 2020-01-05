package ma.irisi.ocr.model; /***********************************************************************
 * Module:  Role.java
 * Author:  ELIOT
 * Purpose: Defines the Class Role
 ***********************************************************************/

import javax.persistence.*;
import java.util.*;

@Entity
public class Role {
   @Id
   @GeneratedValue(strategy= GenerationType.AUTO)
   private Long id;
   private String name;
   @OneToMany
   private List<Account> accounts;

   public Role() {
   }

   public Role(Long id, String name) {
      this.id = id;
      this.name = name;
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

   public List<Account> getAccounts() {
      return accounts;
   }

   public void setAccounts(List<Account> accounts) {
      this.accounts = accounts;
   }
}