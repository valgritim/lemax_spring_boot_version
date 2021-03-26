package com.fan_de_lemax.lemax.controllers;

import com.fan_de_lemax.lemax.models.entities.Article;
import com.fan_de_lemax.lemax.models.services.ArticleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin("*")
@RestController
@RequestMapping("api/articles")
public class ArticleController {

  @Autowired
  private ArticleService articleService;

  @GetMapping(value = "")
  public ResponseEntity<List<Article>> getAllArticles(){

    List<Article> allArticlesFromDb = articleService.getAllArticles()
        .orElseThrow(() -> {
          return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Impossible de charger la liste des articles");
        });

    return new ResponseEntity<List<Article>>(allArticlesFromDb, HttpStatus.OK);
  }

  @GetMapping(value = "/retired")
  public ResponseEntity<List<Article>> getRetiredArticles(){

    List<Article> retiredArticlesFromDb = articleService.getAllretiredArticles();
    return new ResponseEntity<List<Article>>(retiredArticlesFromDb, HttpStatus.OK);
  }
  @GetMapping(value="/id/{id}")
  public ResponseEntity<Article> getArticleById(@PathVariable(value="id") Long id){
    Article article = articleService.getArticleById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "l'article id n°: " + id + " n'existe pas!"));

    return new ResponseEntity<Article>(article, HttpStatus.OK);
  }

  @GetMapping(value="/{categoryId}")
  public ResponseEntity<List<Article>> getArticlesByCategory(@PathVariable(value="categoryId") Integer categoryId){
      List<Article> articles = articleService.getArticleByCategoryId(categoryId)
          .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Il n'y a pas d'article dans la category " + categoryId));
      return new ResponseEntity<List<Article>>(articles, HttpStatus.OK);
  }

  @GetMapping(value="/sku/{sku}")
  public ResponseEntity<Article> getArticleBySku(@PathVariable(value="sku") Integer sku){
    Article articleBySku = articleService.getArticleBySku(sku)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Il n'y a aucun article ayant le sku " + sku)
        );
    return new ResponseEntity<Article>(articleBySku, HttpStatus.OK);
  }

}
