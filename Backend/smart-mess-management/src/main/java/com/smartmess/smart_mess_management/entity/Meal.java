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
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Meal {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name; // Breakfast, Lunch, Dinner, Snacks

    private String description;
    private String serveTime; // e.g., "07:00 - 09:00"

    @ManyToOne
    @JoinColumn(name = "mess_id")
    private Mess mess;


    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getServeTime() {
        return serveTime;
    }

    public void setServeTime(String serveTime) {
        this.serveTime = serveTime;
    }

    public Mess getMess() {
        return mess;
    }

    public void setMess(Mess mess) {
        this.mess = mess;
    }
}