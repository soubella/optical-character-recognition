package ma.irisi.ocr.model; /***********************************************************************
 * Module:  User.java
 * Author:  ELIOT
 * Purpose: Defines the Class User
 ***********************************************************************/



import javax.persistence.*;

@Entity
public class User {
   @Id
   @GeneratedValue(strategy= GenerationType.AUTO)
   private Long id;
   private String firstName;
   private String lastName;
   @OneToOne
   public Account account;

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

   public Account getAccount() {
      return account;
   }

   public void setAccount(Account account) {
      this.account = account;
   }

   public User(Long id, String firstName, String lastName, Account account) {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.account = account;
   }
}