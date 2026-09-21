package com.smartmess.smart_mess_management.serviceimpl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.smartmess.smart_mess_management.Services.MealService;
import com.smartmess.smart_mess_management.entity.Meal;
import com.smartmess.smart_mess_management.repository.MealRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;

    @Override
    public Meal createMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    @Override
    public Meal getMealById(Long id) {
        return mealRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meal not found"));
    }

    @Override
    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    @Override
    public Meal updateMeal(Long id, Meal meal) {
        Meal existing = getMealById(id);

        existing.setName(meal.getName());
        existing.setDescription(meal.getDescription());
        existing.setServeTime(meal.getServeTime());

        return mealRepository.save(existing);
    }

    @Override
    public void deleteMeal(Long id) {
        mealRepository.deleteById(id);
    }
}