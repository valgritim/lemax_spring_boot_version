package com.fan_de_lemax.lemax.models.entities;

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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="email")
  @NotNull(message = "Email cannot be null")
  @Email(message = "Email should be valid")
  private String email;

  @Column(name="password")
  @Size(min = 8, message = "password must be at least 8 characters")
  private String password;

  @Column(name="articles")
  @ManyToMany(cascade = CascadeType.ALL)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Set<Article> articles = new HashSet<>();

  @Column(name="roles")
  @ManyToMany(fetch =  FetchType.EAGER)
  private Set<Role> roles = new HashSet<>();

  public User(String email, String password, Set<Article> articles, Set<Role> roles) {
    this.email = email;
    this.password = password;
    this.articles = articles;
    this.roles = roles;
  }

  public User() {
  }

  public Set<Article> getArticles() {
    return articles;
  }

  public void setArticles(Set<Article> articles) {
    this.articles = articles;
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
        ", email='" + email + '\'' +
        ", password='" + password + '\'' +
        ", articles=" + articles +
        ", roles=" + roles +
        '}';
  }
}
