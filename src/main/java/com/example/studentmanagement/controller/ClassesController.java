package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Classes;
import com.example.studentmanagement.service.classes.ClassesService;
import com.example.studentmanagement.service.classes.IClassesService;
import com.example.studentmanagement.service.student.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/classes")
public class ClassesController {
    @Autowired
    private ClassesService classesService;
    @Autowired
    private IStudentService studentService;

    @GetMapping
    public ModelAndView displayAll() {
        ModelAndView modelAndView = new ModelAndView("/classes/home");
        modelAndView.addObject("countStudent", classesService.countStudent());
        modelAndView.addObject("classes", classesService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/classes/create");
        modelAndView.addObject("class", new Classes());
        return modelAndView;
    }

    @PostMapping("/create")
    public String createClasses(Classes classes) {
        classesService.save(classes);
        return "redirect:/classes";
    }

    @GetMapping("/update/{id}")
    public ModelAndView showUpdateForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/classes/update");
        Classes classes = classesService.findById(id);
        modelAndView.addObject("classes", classes);
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public String updateClasses(@ModelAttribute("classes") Classes updateClasses, @PathVariable Long id) {
        updateClasses.setId(id);
        classesService.save(updateClasses);
        return "redirect:/classes";
    }

    @GetMapping("/sortClass")
    public ModelAndView sortClassAsc() {
        ModelAndView modelAndView = new ModelAndView("/classes/home");
        List<Classes> classes = classesService.sortClassByStudentCount();
        modelAndView.addObject("countStudent", classesService.countStudentAsc());
        modelAndView.addObject("classes", classes);
        return modelAndView;
    }

    @GetMapping("/sortClassDes")
    public ModelAndView sortClassDes() {
        ModelAndView modelAndView = new ModelAndView("/classes/home");
        List<Classes> classes = classesService.sortClassByStudentCountDes();
        modelAndView.addObject("countStudent", classesService.countStudentDes());
        modelAndView.addObject("classes", classes);
        return modelAndView;
    }

    @GetMapping("/getAvg")
    public ModelAndView showAvgOfClass() {
        ModelAndView modelAndView = new ModelAndView("/classes/home");
        List<Float> avgPoint = classesService.getAvg();
        modelAndView.addObject("countStudent", classesService.countStudent());
        modelAndView.addObject("classes", classesService.findAll());
        modelAndView.addObject("avgPoint", avgPoint);
        return modelAndView;
    }

}
