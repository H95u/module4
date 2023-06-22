package com.example.studentmanagement.service.student;

import com.example.studentmanagement.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStudentService {
    Page<Student> findAll(Pageable pageable);

    Student findById(Long id);

    void save(Student student);

    void remove(Long id);

    Page<Student> searchByName(Pageable pageable, String name);

    List<Student> sortAllByPoint();
}
