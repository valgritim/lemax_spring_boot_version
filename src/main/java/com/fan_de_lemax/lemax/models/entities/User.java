package com.fan_de_lemax.lemax.models.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="user")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name="username")
   @NotNull(message = "Username cannot be null")
   @Email(message = "Username should be valid")
   private String username;

   @Column(name="password")
   @Size(min = 8, message = "password must be at least 8 characters")
   private String password;

   @Column(name="pseudo")
   @NotNull(message = "Pseudo cannot be null")
   @Size(min=2 , message="Pseudo must be at least 2  characters")
   private String pseudo;

   @Column(name="articles")
   @ManyToMany(cascade = CascadeType.ALL)
   @OnDelete(action = OnDeleteAction.CASCADE)
   private Set<Article> articles = new HashSet<>();

   @ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(name = "user_roles",
       joinColumns = @JoinColumn(name = "user_id"),
       inverseJoinColumns = @JoinColumn(name = "role_id"))
   private Set<Role> roles = new HashSet<>();


   public User() {
   }

   public User(@NotNull(message = "Email cannot be null") @Email(message = "Email should be valid") String username,
             @Size(min = 8, message = "password must be at least 8 characters") String password,
             @NotNull(message = "Pseudo cannot be null") @Size(min = 2, message = "Pseudo must be at least 2  characters") String pseudo,
             Set<Article> articles) {
      this.username = username;
      this.password = password;
      this.pseudo = pseudo;
      this.articles = articles;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getPseudo() {
      return pseudo;
   }

   public void setPseudo(String pseudo) {
      this.pseudo = pseudo;
   }

   public Set<Article> getArticles() {
      return articles;
   }

   public void setArticles(Set<Article> articles) {
      this.articles = articles;
   }

   public Set<Role> getRoles() {
      return roles;
   }

   public void setRoles(Set<Role> roles) {
      this.roles = roles;
   }

   @Override
   public String toString() {
      return "User{" +
          "id=" + id +
          ", username='" + username + '\'' +
          ", password='" + password + '\'' +
          ", pseudo='" + pseudo + '\'' +
          ", articles=" + articles +
          ", roles=" + roles +
          '}';
   }
}
