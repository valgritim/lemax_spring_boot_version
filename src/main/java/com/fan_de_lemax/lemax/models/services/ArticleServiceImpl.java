package com.fan_de_lemax.lemax.models.services;

import com.fan_de_lemax.lemax.models.entities.Article;
import com.fan_de_lemax.lemax.models.repositories.ArticleRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="articleService")
public class ArticleServiceImpl implements ArticleService{

  @Autowired
  private ArticleRepository articleRepository;

  public ArticleServiceImpl(ArticleRepository articleRepository) {
    this.articleRepository = articleRepository;
  }

  @Override
  public Optional<List<Article>> getAllArticles() {
    return Optional.of(articleRepository.findAll());
  }

  @Override
  public Optional<Article> getArticleById(Long id) {
    return articleRepository.findById(id);
  }

  @Override
  public Optional<Article> getArticleByName(String name) {
    return articleRepository.findByName(name);
  }

  @Override
  public Optional<List<Article>> getArticleByCategoryId(Integer id) {
    return articleRepository.findByCategoryId(id);
  }

  @Override
  public List<Article> getAllretiredArticles() {
    return articleRepository.findRetiredArticles();
  }

  @Override
  public Optional<Article> getArticleBySku(String sku) {
    return (articleRepository.getArticleBySku(sku));
  }
}
