package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Classes;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.service.classes.ClassesService;
import com.example.studentmanagement.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassesService classesService;

    @ModelAttribute("classes")
    public List<Classes> classes() {
        return classesService.findAll();
    }

    @GetMapping("")
    public ModelAndView displayStudents(@PageableDefault(value = 4) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/student/home");
        modelAndView.addObject("students", studentService.findAll(pageable));
        return modelAndView;
    }


    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/student/create");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }

    @PostMapping("/create")
    public String createClasses(@ModelAttribute("student") Student student) {
        studentService.save(student);
        return "redirect:/students";
    }

    @GetMapping("/update/{id}")
    public ModelAndView showUpdateForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/student/update");
        Student student = studentService.findById(id);
        modelAndView.addObject("student", student);
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id, Student student) {
        student.setId(id);
        studentService.save(student);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.remove(id);
        return "redirect:/students";
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewStudent(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/student/view");
        Student student = studentService.findById(id);
        modelAndView.addObject("student", student);
        return modelAndView;
    }

    @PostMapping("/search")
    public ModelAndView searchByName(@RequestParam String nameSearch, @PageableDefault(value = 4) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/student/home");
        Page<Student> students = studentService.searchByName(pageable, nameSearch);
        modelAndView.addObject("students", students);
        modelAndView.addObject("name", nameSearch);
        return modelAndView;
    }

    @GetMapping("/search/{name}")
    public ModelAndView nextSearch(@PathVariable String name, @PageableDefault(value = 4) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/student/home");
        Page<Student> students = studentService.searchByName(pageable, name);
        modelAndView.addObject("students", students);
        return modelAndView;
    }


    @GetMapping("/sort")
    public ModelAndView sortByPoint() {
        ModelAndView modelAndView = new ModelAndView("/student/home");
        List<Student> students = studentService.sortAllByPoint();
        modelAndView.addObject("students", students);
        return modelAndView;
    }

    @GetMapping("/sortDes")
    public ModelAndView sortByPointDes() {
        ModelAndView modelAndView = new ModelAndView("/student/home");
        List<Student> students = studentService.sortAllByPointDes();
        modelAndView.addObject("students", students);
        return modelAndView;
    }

    @PostMapping("/searchByClass")
    public ModelAndView searchByClass(@RequestParam Long classId) {
        ModelAndView modelAndView = new ModelAndView("/student/home");
        List<Student> students = studentService.searchByClass(classId);
        modelAndView.addObject("students", students);
        return modelAndView;
    }
}
