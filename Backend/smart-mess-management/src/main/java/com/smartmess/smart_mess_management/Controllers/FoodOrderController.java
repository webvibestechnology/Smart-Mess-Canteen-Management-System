package com.smartmess.smart_mess_management.Controllers;

import com.smartmess.smart_mess_management.Services.FoodOrderService;
import com.smartmess.smart_mess_management.entity.FoodOrder;
import enums.OrderStatus;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class FoodOrderController {

    private final FoodOrderService orderService;

    @PostMapping
    public ResponseEntity<FoodOrder> create(@RequestBody FoodOrder order) {
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodOrder> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping
    public ResponseEntity<List<FoodOrder>> getAll() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<FoodOrder> updateStatus(
            @PathVariable Long id,
            @RequestParam OrderStatus status) {

        return ResponseEntity.ok(orderService.updateOrderStatus(id, status));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<FoodOrder>> getByStudent(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(orderService.getOrdersByStudent(studentId));
    }
}