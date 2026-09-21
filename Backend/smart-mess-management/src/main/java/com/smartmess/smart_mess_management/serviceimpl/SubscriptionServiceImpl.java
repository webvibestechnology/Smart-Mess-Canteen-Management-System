package com.smartmess.smart_mess_management.serviceimpl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.smartmess.smart_mess_management.Services.SubscriptionService;
import com.smartmess.smart_mess_management.entity.Subscription;

import com.smartmess.smart_mess_management.repository.SubscriptionRepository;

import enums.SubscriptionStatus;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription createSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Subscription not found with id: " + id));
    }

    @Override
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    @Override
    public Subscription updateSubscription(
            Long id,
            Subscription subscription) {

        Subscription existing = getSubscriptionById(id);

        existing.setStartDate(subscription.getStartDate());
        existing.setEndDate(subscription.getEndDate());
        existing.setPlan(subscription.getPlan());
        existing.setStatus(subscription.getStatus());
        existing.setAmount(subscription.getAmount());

        return subscriptionRepository.save(existing);
    }

    @Override
    public void deleteSubscription(Long id) {
        subscriptionRepository.deleteById(id);
    }

    @Override
    public List<Subscription> getActiveSubscriptionsByStudent(Long studentId) {
        return subscriptionRepository.findByStudentIdAndStatus(
                studentId,
                SubscriptionStatus.ACTIVE);
    }

    @Override
    public boolean hasActiveSubscription(Long studentId, Long messId) {
        return subscriptionRepository.existsByStudentIdAndMessIdAndStatus(
                studentId,
                messId,
                SubscriptionStatus.ACTIVE);
    }
}