package com.fan_de_lemax.lemax.controllers;


import com.fan_de_lemax.lemax.models.entities.Article;
import com.fan_de_lemax.lemax.models.entities.ArticleId;
import com.fan_de_lemax.lemax.models.entities.User;
import com.fan_de_lemax.lemax.models.services.ArticleService;
import com.fan_de_lemax.lemax.models.services.UserService;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/api/users")
public class UserController {

  @Autowired
  private UserService userService;
  @Autowired
  private ArticleService articleService;
  private Article Article;

  @GetMapping(value="")
  public ResponseEntity<List<User>> getAllUsers(){

    List<User> usersFromDb = userService.getAllUsers();
    return new ResponseEntity<List<User>>(usersFromDb, HttpStatus.OK);
  }

  @GetMapping(value="/id/{userId}")
  public ResponseEntity<User> getUserById(@PathVariable(value="userId") Long userId){
        User user = userService.getUserById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User id " + userId + " not found in DB"));
    return new ResponseEntity<User>(user, HttpStatus.OK);
  }

  @GetMapping(value="/{userId}")
  public ResponseEntity<Set<Article>> fetchArticlesByUser(@PathVariable(value="userId") Long userId){
    User user = userService.getUserById(userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "user Id nr " + userId + " not found in DB"));
    Set<Article> articlesList = user.getArticles();

    return new ResponseEntity<Set<Article>>(articlesList, HttpStatus.OK);
  }

  @PutMapping(value="/{userId}")
  public ResponseEntity<Boolean> addArticleByUser(@RequestBody ArticleId articleIdRequest , @PathVariable(value="userId") Long userId){
    //Je récupère le user dans la DB
    System.out.println("l'id est " + articleIdRequest.getId());
    User user = userService.getUserById(userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "user Id nr " + userId + " not found in DB"));

    if(articleIdRequest != null){
      //Je vais chercher l'article dans la DB
      Article article = articleService.getArticleById(articleIdRequest.getId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Article non trouvé dans la DB"));
      /* je rajoute l'article dans la liste des articles du user */
      user.getArticles().add(article);
      //je sauvegarde le user
      try{
        boolean userUpdated = userService.saveOrUpdateUser(user);
        return new ResponseEntity<Boolean>(userUpdated, HttpStatus.OK);
      } catch (Exception e){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "erreur dans la sauvegarde de l'article pour le user " + userId);
      }

    }  else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune donnée en paramètre");
    }
  }


  @DeleteMapping(value="/{userId}/{articleId}")
  public ResponseEntity<String> deleteArticleByUser(@PathVariable(value="userId") Long userId, @PathVariable(value="articleId") Long articleId) {
    //Je récupère le user dans la DB
    if (userService.getUserById(userId).isPresent()) {
      User user = userService.getUserById(userId).get();
      //Je récupère l'article dans la DB
      Article articleToFetch = articleService.getArticleById(articleId).orElseThrow(
          () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
              "L'article ayant l'id " + articleId + " n'existe pas dans la DB"));
      Set<Article> articlesFromUser = user.getArticles();

      if (articlesFromUser.isEmpty() || !articlesFromUser.contains(articleToFetch)) {
        return new ResponseEntity<String>(
            "Article non trouvé dans la collection de cet utilisateur", HttpStatus.NOT_FOUND);
      } else {
        articlesFromUser.remove(articleToFetch);
        userService.saveOrUpdateUser(user);
      }
      return new ResponseEntity<String>("Article supprimé", HttpStatus.OK);
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"L'utilisateur ayant l'Id nr " + userId + " n'existe pas dans la DB");
    }
  }

}
