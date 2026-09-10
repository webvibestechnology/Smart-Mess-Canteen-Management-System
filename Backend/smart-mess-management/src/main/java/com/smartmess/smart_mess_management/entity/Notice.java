package com.smartmess.smart_mess_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import enums.NoticeType;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Table(name = "notices")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Notice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @Column(length = 5000)
    private String content;

    @Enumerated(EnumType.STRING)
    private NoticeType type; // GENERAL, URGENT, MAINTENANCE, HOLIDAY

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    private LocalDate validUntil;
    private Boolean isActive = true;

    @CreationTimestamp
    private LocalDateTime createdAt;
}