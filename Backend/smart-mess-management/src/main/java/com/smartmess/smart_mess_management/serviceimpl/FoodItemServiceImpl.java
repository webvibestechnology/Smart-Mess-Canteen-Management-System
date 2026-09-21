package com.smartmess.smart_mess_management.serviceimpl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.smartmess.smart_mess_management.Services.FoodItemService;
import com.smartmess.smart_mess_management.entity.FoodItem;
import com.smartmess.smart_mess_management.repository.FoodItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodItemServiceImpl implements FoodItemService {

    private final FoodItemRepository foodItemRepository;

    @Override
    public FoodItem createFoodItem(FoodItem foodItem) {
        return foodItemRepository.save(foodItem);
    }

    @Override
    public FoodItem getFoodItemById(Long id) {
        return foodItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FoodItem not found"));
    }

    @Override
    public List<FoodItem> getAllFoodItems() {
        return foodItemRepository.findAll();
    }

    @Override
    public FoodItem updateFoodItem(Long id, FoodItem foodItem) {
        FoodItem existing = getFoodItemById(id);

        existing.setName(foodItem.getName());
        existing.setDescription(foodItem.getDescription());
        existing.setPrice(foodItem.getPrice());
        existing.setCategory(foodItem.getCategory());
        existing.setIsAvailable(foodItem.getIsAvailable());
        existing.setImageUrl(foodItem.getImageUrl());
        existing.setCanteen(foodItem.getCanteen());

        return foodItemRepository.save(existing);
    }

    @Override
    public void deleteFoodItem(Long id) {
        foodItemRepository.deleteById(id);
    }

    @Override
    public List<FoodItem> getAvailableItemsByCanteen(Long canteenId) {
        return foodItemRepository.findByCanteenIdAndIsAvailableTrue(canteenId);
    }
}