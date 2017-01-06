package project.controller;

import project.model.Address;
import project.model.Student;
import project.persistence.AddressMapper;
import project.persistence.StudentMapper;
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

    @Autowired
    private AddressMapper addressMapper;

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

    @GetMapping("/editStudent/{studentId}")
    public String editStudentForm(Model model, @PathVariable(value = "studentId") int id) {
        Student student = studentMapper.getStudentById(id);
        model.addAttribute("student", student);
        return "editStudent";
    }

    @PostMapping("/editStudent")
    public String editStudentSubmit(@ModelAttribute Student student) {
        studentMapper.updateStudent(student);
        return "redirect:/studentsList";
    }

    @GetMapping("/studentProfile/{studentId}")
    public String studentProfile(Model model, @PathVariable(value = "studentId") int id) {
        Student student = studentMapper.getStudentById(id);
        List<Address> addresses = addressMapper.getAddressByStudentId(id);
        model.addAttribute("student", student);
        model.addAttribute("addresses", addresses);
        return "studentProfile";
    }
}


