package com.smartmess.smart_mess_management.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smartmess.smart_mess_management.Services.CanteenService;
import com.smartmess.smart_mess_management.entity.Canteen;
import com.smartmess.smart_mess_management.repository.CanteenRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CanteenServiceImpl implements CanteenService {

    private final CanteenRepository canteenRepository;

    @Override
    public Canteen createCanteen(Canteen canteen) {
        return canteenRepository.save(canteen);
    }

    @Override
    public Canteen getCanteenById(Long id) {
        return canteenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Canteen not found"));
    }

    @Override
    public List<Canteen> getAllCanteens() {
        return canteenRepository.findAll();
    }

    @Override
    public Canteen updateCanteen(Long id, Canteen canteen) {
        Canteen existing = getCanteenById(id);

        existing.setName(canteen.getName());
        existing.setLocation(canteen.getLocation());
        existing.setContactNumber(canteen.getContactNumber());
        existing.setIsActive(canteen.getIsActive());

        return canteenRepository.save(existing);
    }

    @Override
    public void deleteCanteen(Long id) {
        canteenRepository.deleteById(id);
    }
}