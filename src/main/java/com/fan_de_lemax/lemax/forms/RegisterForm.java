package com.fan_de_lemax.lemax.forms;

import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterForm {
    @NotNull
    @Size(min=8, max=20)
    private String email;

    @NotNull
    @Email
    private String password;

    private Set<String> role;

  public RegisterForm(
      @NotNull @Size(min = 8, max = 20) String email,
      @NotNull @Email String password, Set<String> role) {
    this.email = email;
    this.password = password;
    this.role = role;
  }

  public RegisterForm() {
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "RegisterForm{" +
        "email='" + email + '\'' +
        ", password='" + password + '\'' +
        ", role=" + role +
        '}';
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<String> getRole() {
    return role;
  }

  public void setRole(Set<String> role) {
    this.role = role;
  }
}
