package com.fan_de_lemax.lemax.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="article")
public class Article {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="name")
  private String name;

  @Column(name="sku")
  private Integer sku;
  @Column(name="released")
  private String released;
  @Column(name="retired")
  private String retired;
  @Column(name="imagePath")
  private String imagePath;
  @Column(name="categoryId")
  private Integer categoryId;

  public Article() {
  }

  public Article(String name, Integer sku, String released, String retired,
      String imagePath, Integer categoryId) {
    this.name = name;
    this.sku = sku;
    this.released = released;
    this.retired = retired;
    this.imagePath = imagePath;
    this.categoryId = categoryId;
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

  public Integer getSku() {
    return sku;
  }

  public void setSku(Integer sku) {
    this.sku = sku;
  }

  public String getReleased() {
    return released;
  }

  public void setReleased(String released) {
    this.released = released;
  }

  public String getRetired() {
    return retired;
  }

  public void setRetired(String retired) {
    this.retired = retired;
  }

  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  public Integer getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }

  @Override
  public String toString() {
    return "Article{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", sku=" + sku +
        ", released='" + released + '\'' +
        ", retired='" + retired + '\'' +
        ", imagePath='" + imagePath + '\'' +
        ", categoryId='" + categoryId + '\'' +
        '}';
  }
}
