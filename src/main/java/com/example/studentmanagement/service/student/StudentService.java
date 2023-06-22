package com.example.studentmanagement.service.student;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService {
    @Autowired
    private IStudentRepository studentRepository;

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void remove(Long id) {
        studentRepository.deleteById(id);
    }


    public Page<Student> searchByName(Pageable pageable, String name) {
        return studentRepository.searchByName(pageable, "%" + name + "%");
    }

    @Override
    public List<Student> sortAllByPoint() {
        return studentRepository.findAllByOrderByPointAsc();
    }

    public List<Student> sortAllByPointDes() {
        return studentRepository.findAllByOrderByPointDesc();
    }

    public List<Student> searchByClass(Long classId) {
        return studentRepository.findStudentByClasses_Id(classId);
    }
}
