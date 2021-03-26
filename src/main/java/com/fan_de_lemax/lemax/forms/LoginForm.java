package com.fan_de_lemax.lemax.forms;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginForm {

  @NotNull
  @Email
  private String email;

  @NotNull
  @Size(min=8,max=20)
  private String password;

  public LoginForm(
      @NotNull @Email String email,
      @NotNull @Size(min = 8, max = 20) String password) {
    this.email = email;
    this.password = password;
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

  @Override
  public String toString() {
    return "LoginForm{" +
        "email='" + email + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}
