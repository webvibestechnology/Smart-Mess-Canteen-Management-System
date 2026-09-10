package com.smartmess.smart_mess_management.repository;


import com.smartmess.smart_mess_management.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {

    List<Meal> findByName(String name);

}