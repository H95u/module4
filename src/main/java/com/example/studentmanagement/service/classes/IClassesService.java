package com.example.studentmanagement.service.classes;

import com.example.studentmanagement.model.Classes;
import com.example.studentmanagement.model.Student;

import java.util.List;

public interface IClassesService{
    List<Classes> findAll();

    Classes findById(Long id);

    void save(Classes classes);

    void remove(Long id);

}
