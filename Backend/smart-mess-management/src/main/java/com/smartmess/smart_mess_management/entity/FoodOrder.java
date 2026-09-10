package com.smartmess.smart_mess_management.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "food_orders")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class FoodOrder {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "canteen_id")
    private Canteen canteen;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // PENDING, CONFIRMED, READY, DELIVERED, CANCELLED

    private BigDecimal totalAmount;

    @CreationTimestamp
    private LocalDateTime orderedAt;

    @OneToMany(mappedBy = "foodOrder", cascade = CascadeType.ALL)
    private List<FoodOrderItem> orderItems;
}