package com.smartmess.smart_mess_management.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import enums.PaymentStatus;
import enums.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PaymentType type; // MESS_FEE, CANTEEN_ORDER, SUBSCRIPTION

    @Enumerated(EnumType.STRING)
    private PaymentStatus status; // PENDING, COMPLETED, FAILED, REFUNDED

    private String transactionId;
    private String paymentMethod; // CASH, UPI, CARD, ONLINE

    @CreationTimestamp
    private LocalDateTime paidAt;
}