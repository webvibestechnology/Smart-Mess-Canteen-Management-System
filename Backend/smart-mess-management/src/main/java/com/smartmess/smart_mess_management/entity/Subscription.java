package com.smartmess.smart_mess_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "subscriptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "mess_id")
    private Mess mess;

    private LocalDate startDate;

    private LocalDate endDate;

  //  @Enumerated(EnumType.STRING)
 //   private SubscriptionPlan plan;

   // @Enumerated(EnumType.STRING)
   // private SubscriptionStatus status;

    private BigDecimal amount;

    @CreationTimestamp
    private LocalDateTime createdAt;
}