package com.fan_de_lemax.lemax.forms;

import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterForm {
    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min=8)
    private String password;

    @NotNull
    @Size(min=2)
    private String pseudo;

  public RegisterForm(
      @NotNull @Size(min = 8, max = 20) String email,
      @NotNull @Email String password,
      @NotNull @Size(min = 2) String pseudo, Set<String> role) {
    this.email = email;
    this.password = password;
    this.pseudo = pseudo;  }

  public RegisterForm() {
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

  public String getPseudo() {
    return pseudo;
  }

  public void setPseudo(String pseudo) {
    this.pseudo = pseudo;
  }

  @Override
  public String toString() {
    return "RegisterForm{" +
        "email='" + email + '\'' +
        ", password='" + password + '\'' +
        ", pseudo='" + pseudo + '\'' +
        '}';
  }
}
