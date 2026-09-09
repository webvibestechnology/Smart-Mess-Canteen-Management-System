package com.smartmess.smart_mess_management.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "menus")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate menuDate;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @ManyToOne
    @JoinColumn(name = "mess_id")
    private Mess mess;

    @ManyToOne
    @JoinColumn(name = "meal_id")
    private Meal meal;

    private String items; // comma-separated food items for simplicity

    @CreationTimestamp
    private LocalDateTime createdAt;
}