package com.smartmess.smart_mess_management.Controllers;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartmess.smart_mess_management.Services.AdminService;
import com.smartmess.smart_mess_management.entity.Admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping
    public ResponseEntity<Admin> create(
            @Valid @RequestBody Admin admin) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(adminService.createAdmin(admin));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                adminService.getAdminById(id));
    }

    @GetMapping
    public ResponseEntity<List<Admin>> getAll() {

        return ResponseEntity.ok(
                adminService.getAllAdmins());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> update(
            @PathVariable Long id,
            @Valid @RequestBody Admin admin) {

        return ResponseEntity.ok(
                adminService.updateAdmin(id, admin));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        adminService.deleteAdmin(id);

        return ResponseEntity.noContent().build();
    }
}