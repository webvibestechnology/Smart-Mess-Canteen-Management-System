package com.smartmess.smart_mess_management.serviceimpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.smartmess.smart_mess_management.Services.AttendanceService;
import com.smartmess.smart_mess_management.entity.Attendance;
import com.smartmess.smart_mess_management.repository.AttendanceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    @Override
    public Attendance createAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @Override
    public Attendance getAttendanceById(Long id) {
        return attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Attendance not found with id: " + id));
    }

    @Override
    public List<Attendance> getAllAttendances() {
        return attendanceRepository.findAll();
    }

    @Override
    public Attendance updateAttendance(
            Long id,
            Attendance attendance) {

        Attendance existing = getAttendanceById(id);

        existing.setStudent(attendance.getStudent());
        existing.setMeal(attendance.getMeal());
        existing.setAttendanceDate(attendance.getAttendanceDate());
        existing.setIsPresent(attendance.getIsPresent());

        return attendanceRepository.save(existing);
    }

    @Override
    public void deleteAttendance(Long id) {
        attendanceRepository.deleteById(id);
    }

    @Override
    public List<Attendance> getAttendanceByStudentAndDateRange(
            Long studentId,
            LocalDate from,
            LocalDate to) {

        return attendanceRepository
                .findByStudentIdAndAttendanceDateBetween(
                        studentId,
                        from,
                        to);
    }

    @Override
    public long countPresentByStudentAndMonth(
            Long studentId,
            int month,
            int year) {

        LocalDate from = LocalDate.of(year, month, 1);

        LocalDate to = from.withDayOfMonth(
                from.lengthOfMonth());

        return attendanceRepository
                .countByStudentIdAndAttendanceDateBetweenAndIsPresentTrue(
                        studentId,
                        from,
                        to);
    }
}