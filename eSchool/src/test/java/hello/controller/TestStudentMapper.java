package hello.controller;

import hello.model.Student;
import hello.persistence.StudentMapper;
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

    @Autowired //mynau sen ushin obyekt sozdavat etip beredi
    private StudentMapper studentMapper;




    @Test
    public void testStudentsList() throws Exception {
        List<Student> studentsList = studentMapper.getAllStudents();
        Assert.assertNotNull(studentsList); //NOT NULL
        Assert.assertTrue(studentsList.size() >= 0); // BAZADAN KELGEN STUDENTTERDIN SANY 0 nemese kop bolu kerek
        //sonda myna test eki assert birden zhasaidy
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
        Assert.assertEquals(studentMapper.getAllStudents().size(), size + 1);// 2 emes!!!! sen bilmeisin goi nachalniy size kansha bolatynyn
    }

    @Test
    public void testDeleteStudent() {
        //men alip tastagan narse zhogardy metodta zhazlyp tur kerek zhok ol tekseru
        Student student = new Student();
        student.setName("asdf");
        student.setSurname("qwerty");
        student.setPhoneNumber("7984651321");
        student.seteMail("zxcv@bnm");
        studentMapper.insertStudent(student);

        List<Student> students  =  studentMapper.getAllStudents();
        Student student1 = students.get(0);

        //SIZE DO UDAENIYA
        int sizeBeforeDelete = studentMapper.getAllStudents().size(); //10

        //DELELTE
        studentMapper.deleteStudent(student1.getId());

        //SIZE POSLE UDALENIYA
        int sizeAfterDelete = studentMapper.getAllStudents().size(); //9

        Assert.assertEquals(sizeAfterDelete, sizeBeforeDelete - 1);
        Assert.assertNotEquals(sizeBeforeDelete, sizeAfterDelete);// ok zapusti

    }
}
