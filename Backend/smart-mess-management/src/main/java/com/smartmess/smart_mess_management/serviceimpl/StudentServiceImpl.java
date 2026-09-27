package com.smartmess.smart_mess_management.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.smartmess.smart_mess_management.Services.StudentService;
import com.smartmess.smart_mess_management.entity.Student;
import com.smartmess.smart_mess_management.repository.StudentRepository;

import enums.StudentStatus;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;


    // =========================================
    // CREATE STUDENT
    // =========================================

    @Override
    public Student createStudent(Student student) {

        // New students will be ACTIVE by default
        if (student.getStatus() == null) {
            student.setStatus(StudentStatus.ACTIVE);
        }

        return studentRepository.save(student);
    }


    // =========================================
    // GET STUDENT BY ID
    // =========================================

    @Override
    public Student getStudentById(Long id) {

        return studentRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Student not found with id: " + id
                    )
                );
    }


    // =========================================
    // GET ALL STUDENTS
    // =========================================

    @Override
    public List<Student> getAllStudents() {

        return studentRepository.findAll();
    }


    // =========================================
    // UPDATE STUDENT
    // =========================================

    @Override
    public Student updateStudent(
            Long id,
            Student student) {

        Student existing = getStudentById(id);

        existing.setName(student.getName());
        existing.setPhone(student.getPhone());
        existing.setRollNumber(student.getRollNumber());
        existing.setDepartment(student.getDepartment());
        existing.setHostelName(student.getHostelName());
        existing.setRoomNumber(student.getRoomNumber());

        return studentRepository.save(existing);
    }


    // =========================================
    // DELETE STUDENT
    // =========================================

    @Override
    public void deleteStudent(Long id) {

        studentRepository.deleteById(id);
    }


    // =========================================
    // FIND STUDENT BY EMAIL
    // =========================================

    @Override
    public Optional<Student> findByEmail(String email) {

        return studentRepository.findByEmail(email);
    }
}