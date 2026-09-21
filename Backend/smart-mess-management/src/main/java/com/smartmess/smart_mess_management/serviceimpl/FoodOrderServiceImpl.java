package com.smartmess.smart_mess_management.serviceimpl;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.smartmess.smart_mess_management.Services.FoodOrderService;
import com.smartmess.smart_mess_management.entity.FoodOrder;

import com.smartmess.smart_mess_management.repository.FoodOrderRepository;

import enums.OrderStatus;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodOrderServiceImpl implements FoodOrderService {

    private final FoodOrderRepository foodOrderRepository;

    @Override
    public FoodOrder createOrder(FoodOrder order) {

        BigDecimal total = order.getOrderItems().stream()
                .map(item -> item.getUnitPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalAmount(total);
        order.setStatus(OrderStatus.PENDING);

        return foodOrderRepository.save(order);
    }

    @Override
    public FoodOrder getOrderById(Long id) {
        return foodOrderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order not found with id: " + id));
    }

    @Override
    public List<FoodOrder> getAllOrders() {
        return foodOrderRepository.findAll();
    }

    @Override
    public void deleteOrder(Long id) {
        foodOrderRepository.deleteById(id);
    }

    @Override
    public List<FoodOrder> getOrdersByStudent(Long studentId) {
        return foodOrderRepository.findByStudentId(studentId);
    }

    @Override
    public List<FoodOrder> getOrdersByStatus(OrderStatus status) {
        return foodOrderRepository.findByStatus(status);
    }

    @Override
    public FoodOrder updateOrderStatus(Long orderId, OrderStatus status) {

        FoodOrder order = foodOrderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order not found with id: " + orderId));

        order.setStatus(status);

        return foodOrderRepository.save(order);
    }
}