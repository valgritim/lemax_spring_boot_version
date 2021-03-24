package com.fan_de_lemax.lemax.models.repositories;

import com.fan_de_lemax.lemax.models.entities.Article;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

  @Query(value = " select * from article where name=:name", nativeQuery= true)
  Optional<Article>findByName(String name);

  @Query(value = "select * from article where categoryId = :categoryId order by released DESC", nativeQuery = true)
  Optional<List<Article>> findByCategoryId(Integer categoryId);

  @Query(value="select * from article where retired IS NOT NULL order by retired DESC", nativeQuery = true)
  List<Article> findRetiredArticles();

  @Query(value="select * from article WHERE sku=:sku", nativeQuery = true)
  Optional<Article> getArticleBySku(String sku);
}
