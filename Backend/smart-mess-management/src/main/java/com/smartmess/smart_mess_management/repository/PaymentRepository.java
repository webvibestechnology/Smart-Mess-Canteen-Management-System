package com.smartmess.smart_mess_management.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.smartmess.smart_mess_management.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}