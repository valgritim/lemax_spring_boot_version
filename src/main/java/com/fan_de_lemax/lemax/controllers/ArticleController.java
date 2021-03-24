package com.fan_de_lemax.lemax.controllers;

import com.fan_de_lemax.lemax.models.entities.Article;
import com.fan_de_lemax.lemax.models.services.ArticleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

  public ResponseEntity<List<Article>> getAllArticles(){

    List<Article> allArticlesFromDb = articleService.getAllArticles()
        .orElseThrow(() -> {
          return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Problème avec la DB");
        });

    return new ResponseEntity<List<Article>>(allArticlesFromDb, HttpStatus.OK);
  }

}
