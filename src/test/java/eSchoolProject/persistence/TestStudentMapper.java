package eSchoolProject.persistence;

import eSchoolProject.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by JoKeR on 28.12.2016.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class TestStudentMapper extends AbstractTestNGSpringContextTests {

    @Autowired
    private StudentMapper studentMapper;




    @Test
    public void testStudentsList() throws Exception {
        List<Student> studentsList = studentMapper.getAllStudents();
        Assert.assertNotNull(studentsList);
        Assert.assertTrue(studentsList.size() >= 0);
    }

    @Test
    public void testAddStudent(){
        int size = studentMapper.getAllStudents().size();
        Student student = new Student();
        student.setName("asdf");
        student.setSurname("qwerty");
        student.setPhoneNumber("7984651321");
        student.seteMail("zxcv@bnm");
        studentMapper.insertStudent(student);
        Assert.assertNotEquals(size, studentMapper.getAllStudents().size());
        Assert.assertEquals(studentMapper.getAllStudents().size(), size + 1);
    }

    @Test
    public void testDeleteStudent() {
        Student student = new Student();
        student.setName("asdf");
        student.setSurname("qwerty");
        student.setPhoneNumber("7984651321");
        student.seteMail("zxcv@bnm");
        studentMapper.insertStudent(student);

        List<Student> students  =  studentMapper.getAllStudents();
        Student student1 = students.get(0);

        int sizeBeforeDelete = studentMapper.getAllStudents().size();

        studentMapper.deleteStudent(student1.getId());

        int sizeAfterDelete = studentMapper.getAllStudents().size();

        Assert.assertEquals(sizeAfterDelete, sizeBeforeDelete - 1);
        Assert.assertNotEquals(sizeBeforeDelete, sizeAfterDelete);

    }
}
