package com.smartmess.smart_mess_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartmess.smart_mess_management.entity.Subscription;

import enums.SubscriptionStatus;


public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    List<Subscription> findByStudentIdAndStatus(
            Long studentId,
            SubscriptionStatus status);

    boolean existsByStudentIdAndMessIdAndStatus(
            Long studentId,
            Long messId,
            SubscriptionStatus status);
}