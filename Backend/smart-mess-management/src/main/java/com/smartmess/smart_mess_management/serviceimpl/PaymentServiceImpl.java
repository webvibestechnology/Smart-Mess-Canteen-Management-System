package com.smartmess.smart_mess_management.serviceimpl;



import java.util.List;

import org.springframework.stereotype.Service;

import com.smartmess.smart_mess_management.Services.PaymentService;
import com.smartmess.smart_mess_management.entity.Payment;
import com.smartmess.smart_mess_management.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}