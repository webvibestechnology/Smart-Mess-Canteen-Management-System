package com.smartmess.smart_mess_management.repository;

import com.smartmess.smart_mess_management.entity.Mess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessRepository extends JpaRepository<Mess, Long> {

    List<Mess> findByType(String type);

    Optional<Mess> findByName(String name);
}