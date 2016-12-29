package hello.controller;

import hello.model.Student;
import hello.persistence.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Created by JoKeR on 25.12.2016.
 */
@Controller
public class StudentsController {

    @Autowired
    private StudentMapper studentMapper;


    @GetMapping("/studentsList")
    public String studentsList(Model model) {
        List<Student> studentsList = studentMapper.getAllStudents();
        model.addAttribute("studentsList", studentsList);
        return "studentsList";
    }


    @GetMapping("/addStudent")
    public String addStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "addStudent";
    }

    @PostMapping("/addStudent")
    public String addStudentSubmit(@ModelAttribute Student student) {
        System.out.println(student);
        studentMapper.insertStudent(student);
        return "redirect:/studentsList";
    }


    @GetMapping("/deleteStudent/{studentId}")
    public String deleteStudent(@PathVariable(value = "studentId") int id) {
        studentMapper.deleteStudent(id);
        return "redirect:/studentsList";
    }
}
