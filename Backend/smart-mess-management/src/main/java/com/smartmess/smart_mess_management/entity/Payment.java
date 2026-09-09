package com.smartmess.smart_mess_management.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private BigDecimal amount;

    //@Enumerated(EnumType.STRING)
   // private PaymentType type;

   // @Enumerated(EnumType.STRING)
  //  private PaymentStatus status;

    private String transactionId;

    private String paymentMethod;

    @CreationTimestamp
    private LocalDateTime paidAt;
}