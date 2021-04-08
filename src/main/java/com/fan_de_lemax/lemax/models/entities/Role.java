package com.fan_de_lemax.lemax.models.entities;

import com.fan_de_lemax.lemax.security.EnumRole;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name="role")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @NaturalId
  @Column(length = 20)
  private EnumRole roleName;

  public Role(EnumRole roleName) {
    this.roleName = roleName;
  }

  public Role() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public EnumRole getRoleName() {
    return roleName;
  }

  public void setRole(EnumRole roleName) {
    this.roleName = roleName;
  }

  @Override
  public String toString() {
    return "Role{" +
        "id=" + id +
        ", role=" + roleName +
        '}';
  }
}
