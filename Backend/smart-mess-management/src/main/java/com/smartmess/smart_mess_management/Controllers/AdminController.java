package com.smartmess.smart_mess_management.Controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.smartmess.smart_mess_management.Services.AdminService;
import com.smartmess.smart_mess_management.entity.Admin;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admins")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;


    // =========================
    // REGISTER ADMIN
    // =========================

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Admin admin) {

        if (admin.getEmail() == null || admin.getEmail().isBlank()) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message", "Email is required"));
        }

        if (admin.getPassword() == null || admin.getPassword().isBlank()) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message", "Password is required"));
        }

        if (adminService.findByEmail(admin.getEmail()).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "Email already registered"));
        }

        Admin savedAdmin = adminService.createAdmin(admin);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of(
                        "message", "Registration successful",
                        "adminId", savedAdmin.getId()
                ));
    }


    // =========================
    // LOGIN ADMIN
    // =========================

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody Map<String, String> credentials) {

        String email = credentials.get("email");
        String password = credentials.get("password");

        if (email == null || password == null) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message", "Email and password are required"));
        }

        return adminService.findByEmail(email)

                .filter(admin ->
                        admin.getPassword().equals(password))

                .map(admin ->
                        ResponseEntity.ok(
                                Map.of(
                                        "message", "Login successful",
                                        "adminId", admin.getId()
                                )
                        )
                )

                .orElse(
                        ResponseEntity
                                .status(HttpStatus.UNAUTHORIZED)
                                .body(Map.of(
                                        "message",
                                        "Invalid email or password"
                                ))
                );
    }
}