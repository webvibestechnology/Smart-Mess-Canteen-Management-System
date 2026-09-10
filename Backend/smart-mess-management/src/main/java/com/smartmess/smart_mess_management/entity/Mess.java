package com.smartmess.smart_mess_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import enums.MessType;

import java.time.LocalDateTime;
@Entity
@Table(name = "messes")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Mess {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    private String location;
    private String contactNumber;
    private Integer capacity;

    @Enumerated(EnumType.STRING)
    private MessType type; // VEG, NON_VEG, BOTH

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @CreationTimestamp
    private LocalDateTime createdAt;
}