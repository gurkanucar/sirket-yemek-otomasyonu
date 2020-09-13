package com.gucarsoft.bulutmdyemek.repository;

import com.gucarsoft.bulutmdyemek.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
    Food findByName(String name);
}