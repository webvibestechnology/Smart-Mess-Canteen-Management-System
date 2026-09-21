package com.smartmess.smart_mess_management.serviceimpl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.smartmess.smart_mess_management.Services.MessService;
import com.smartmess.smart_mess_management.entity.Mess;
import com.smartmess.smart_mess_management.repository.MessRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessServiceImpl implements MessService {

    private final MessRepository messRepository;

    @Override
    public Mess createMess(Mess mess) {
        return messRepository.save(mess);
    }

    @Override
    public Mess getMessById(Long id) {
        return messRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mess not found"));
    }

    @Override
    public List<Mess> getAllMesses() {
        return messRepository.findAll();
    }

    @Override
    public Mess updateMess(Long id, Mess mess) {
        Mess existing = getMessById(id);

        existing.setName(mess.getName());
        existing.setLocation(mess.getLocation());
        existing.setContactNumber(mess.getContactNumber());
        existing.setCapacity(mess.getCapacity());
        existing.setType(mess.getType());

        return messRepository.save(existing);
    }

    @Override
    public void deleteMess(Long id) {
        messRepository.deleteById(id);
    }
}
