package com.smartmess.smart_mess_management.repository;



import com.smartmess.smart_mess_management.entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {

    List<FoodItem> findByCategory(String category);

    List<FoodItem> findByIsAvailable(Boolean isAvailable);

}