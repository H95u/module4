package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Classes;
import com.example.studentmanagement.service.classes.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/classes")
public class ClassesControllerAPI {
    @Autowired
    private ClassesService classesService;

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/classes-test/create");
        modelAndView.addObject("classes", classesService.findAll());
        return modelAndView;
    }

    @GetMapping
    public ResponseEntity<Iterable<Classes>> findAllStudent() {
        List<Classes> classes = classesService.findAll();

        if (classes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classes> findStudentById(@PathVariable Long id) {
        Classes classes = classesService.findById(id);
        if (classes == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Classes> saveStudent(@RequestBody Classes classes) {
        return new ResponseEntity<>(classesService.save(classes), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Classes> updateStudent(@PathVariable Long id, @RequestBody Classes classes) {
        Classes classesUpdate = classesService.findById(id);
        if (classesUpdate == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        classes.setId(classesUpdate.getId());
        return new ResponseEntity<>(classesService.save(classes), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Classes> deleteStudent(@PathVariable Long id) {
        Classes classes = classesService.findById(id);
        if (classes == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        classesService.remove(id);
        return new ResponseEntity<>(classes, HttpStatus.NO_CONTENT);
    }
}
