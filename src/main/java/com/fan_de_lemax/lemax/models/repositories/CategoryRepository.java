package com.fan_de_lemax.lemax.models.repositories;

import com.fan_de_lemax.lemax.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
