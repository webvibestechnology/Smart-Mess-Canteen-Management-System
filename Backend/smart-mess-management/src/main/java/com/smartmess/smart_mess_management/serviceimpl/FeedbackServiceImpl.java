package com.smartmess.smart_mess_management.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smartmess.smart_mess_management.Services.FeedbackService;
import com.smartmess.smart_mess_management.entity.Feedback;
import com.smartmess.smart_mess_management.repository.FeedbackRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Override
    public Feedback createFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback getFeedbackById(Long id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Feedback not found with id: " + id));
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback updateFeedback(Long id, Feedback feedback) {

        Feedback existing = getFeedbackById(id);

        existing.setStudent(feedback.getStudent());
        existing.setMess(feedback.getMess());
        existing.setRating(feedback.getRating());
        existing.setComment(feedback.getComment());
        existing.setCategory(feedback.getCategory());

        return feedbackRepository.save(existing);
    }

    @Override
    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}