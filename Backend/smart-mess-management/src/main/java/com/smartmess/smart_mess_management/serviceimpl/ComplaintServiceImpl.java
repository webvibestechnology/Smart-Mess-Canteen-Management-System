package com.smartmess.smart_mess_management.serviceimpl;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.smartmess.smart_mess_management.Services.ComplaintService;
import com.smartmess.smart_mess_management.entity.Complaint;
import com.smartmess.smart_mess_management.repository.ComplaintRepository;

import enums.ComplaintStatus;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;

    @Override
    public Complaint createComplaint(Complaint complaint) {
        return complaintRepository.save(complaint);
    }

    @Override
    public Complaint getComplaintById(Long id) {
        return complaintRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Complaint not found with id: " + id));
    }

    @Override
    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    @Override
    public Complaint updateComplaint(Long id, Complaint complaint) {

        Complaint existing = getComplaintById(id);

        existing.setStudent(complaint.getStudent());
        existing.setTitle(complaint.getTitle());
        existing.setDescription(complaint.getDescription());
        existing.setStatus(complaint.getStatus());
        existing.setCategory(complaint.getCategory());
        existing.setResolution(complaint.getResolution());

        return complaintRepository.save(existing);
    }

    @Override
    public void deleteComplaint(Long id) {
        complaintRepository.deleteById(id);
    }

    @Override
    public List<Complaint> getComplaintsByStatus(ComplaintStatus status) {
        return complaintRepository.findByStatus(status);
    }

    @Override
    public Complaint resolveComplaint(Long complaintId, String resolution) {

        Complaint complaint = getComplaintById(complaintId);

        complaint.setResolution(resolution);
        complaint.setStatus(ComplaintStatus.RESOLVED);
        complaint.setResolvedAt(LocalDateTime.now());

        return complaintRepository.save(complaint);
    }
}