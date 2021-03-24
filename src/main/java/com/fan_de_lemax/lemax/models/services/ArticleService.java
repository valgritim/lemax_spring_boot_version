package com.fan_de_lemax.lemax.models.services;


import com.fan_de_lemax.lemax.models.entities.Article;
import java.util.List;
import java.util.Optional;
import javax.swing.text.html.Option;

public interface ArticleService {

  Optional<List<Article>> getAllArticles();
  Optional<Article> getArticleById(Long id);
  Optional<Article> getArticleByName(String name);
  Optional<List<Article>> getArticleByCategoryId(Integer id);
  List<Article> getAllretiredArticles();
  Optional<Article> getArticleBySku(String sku);




}
