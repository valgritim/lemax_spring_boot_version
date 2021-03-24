package com.fan_de_lemax.lemax.models.services;

import com.fan_de_lemax.lemax.models.entities.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryService {

  List<Category> getAllCategories();
  Optional<Category> getCategoryById(Integer id);

}
