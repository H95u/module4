package com.example.studentmanagement.service.classes;

import com.example.studentmanagement.model.Classes;
import com.example.studentmanagement.repository.IClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassesService implements IClassesService {
    @Autowired
    private IClassesRepository classesRepository;

    @Override
    public List<Classes> findAll() {
        return (List<Classes>) classesRepository.findAll();
    }

    @Override
    public Classes findById(Long id) {
        return classesRepository.findById(id).get();
    }

    @Override
    public void save(Classes classes) {
        classesRepository.save(classes);
    }

    @Override
    public void remove(Long id) {
        classesRepository.deleteById(id);
    }

    public List<Integer> countStudent() {
        return classesRepository.countStudent();
    }

    public List<Integer> countStudentDes() {
        return classesRepository.countStudentDes();
    }

    public List<Integer> countStudentAsc() {
        return classesRepository.countStudentAsc();
    }

    public List<Classes> sortClassByStudentCount() {
        return classesRepository.sortClassByStudentCountAsc();
    }

    public List<Classes> sortClassByStudentCountDes() {
        return classesRepository.sortClassByStudentCountDes();
    }

    public List<Float> getAvg() {
        return classesRepository.getAvg();
    }
}
