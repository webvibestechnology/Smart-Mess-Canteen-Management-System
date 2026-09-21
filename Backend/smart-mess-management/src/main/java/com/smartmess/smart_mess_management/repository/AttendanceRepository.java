package com.smartmess.smart_mess_management.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartmess.smart_mess_management.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByStudentIdAndAttendanceDateBetween(
            Long studentId,
            LocalDate from,
            LocalDate to);

    long countByStudentIdAndAttendanceDateBetweenAndIsPresentTrue(
            Long studentId,
            LocalDate from,
            LocalDate to);
}