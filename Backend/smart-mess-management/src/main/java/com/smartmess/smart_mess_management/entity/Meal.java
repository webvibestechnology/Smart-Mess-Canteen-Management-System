package com.smartmess.smart_mess_management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "meals")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name; // Breakfast, Lunch, Dinner, Snacks

    private String description;

    private String serveTime; // e.g., "07:00 - 09:00"

    @ManyToOne
    @JoinColumn(name = "mess_id")
    private Mess mess;
}