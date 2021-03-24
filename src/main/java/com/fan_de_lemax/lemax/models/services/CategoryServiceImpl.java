package com.fan_de_lemax.lemax.models.services;

import com.fan_de_lemax.lemax.models.entities.Category;
import com.fan_de_lemax.lemax.models.repositories.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="categoryService")
public class CategoryServiceImpl implements CategoryService{

  @Autowired
  private CategoryRepository categoryRepository;

  public CategoryServiceImpl(
      CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Override
  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  @Override
  public Optional<Category> getCategoryById(Integer id) {
    return categoryRepository.findById(id);
  }
}
