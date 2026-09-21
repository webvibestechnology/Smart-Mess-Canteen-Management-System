package com.smartmess.smart_mess_management.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartmess.smart_mess_management.entity.FoodOrder;

import enums.OrderStatus;


public interface FoodOrderRepository extends JpaRepository<FoodOrder, Long> {

    List<FoodOrder> findByStudentId(Long studentId);

    List<FoodOrder> findByStatus(OrderStatus status);
}