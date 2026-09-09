package com.smartmess.smart_mess_management.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "canteens")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Canteen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private String location;
    private String openingTime;
    private String closingTime;
    private String contactNumber;
    private Boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @CreationTimestamp
    private LocalDateTime createdAt;
}