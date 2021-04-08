package com.fan_de_lemax.lemax.forms;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginForm {

  @NotNull
  @Email
  private String username;

  @NotNull
  @Size(min=8,max=20)
  private String password;

  public LoginForm(){
  }

  public String getEmail() {
    return username;
  }

  public void setEmail(String username) {
    this.username = username;
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
        "email='" + username + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}
