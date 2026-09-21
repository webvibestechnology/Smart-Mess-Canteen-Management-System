package com.smartmess.smart_mess_management.serviceimpl;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.smartmess.smart_mess_management.Services.AdminService;
import com.smartmess.smart_mess_management.entity.Admin;
import com.smartmess.smart_mess_management.repository.AdminRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public Admin createAdmin(Admin admin) {
        // TODO: hash password before saving (use BCrypt)
        return adminRepository.save(admin);
    }

    @Override
    public Admin getAdminById(Long id) {
        return adminRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Admin not found with id: " + id));
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin updateAdmin(Long id, Admin admin) {
        Admin existing = getAdminById(id);
        existing.setName(admin.getName());
        existing.setPhone(admin.getPhone());
        existing.setRole(admin.getRole());
        return adminRepository.save(existing);
    }

    @Override
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    @Override
    public Optional<Admin> findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }
}