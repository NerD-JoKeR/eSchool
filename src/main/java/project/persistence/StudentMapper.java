package project.persistence;

import project.model.Student;

import java.util.List;

/**
 * Created by JoKeR on 27.12.2016.
 */
public interface StudentMapper {
    /**
     * @return List of students
     */
    public List<Student> getAllStudents();

    /**
     * @param Student
     * @return the number of rows affected
     */
    public int insertStudent(Student Student);

    /**
     * @param id
     * @return the number of rows affected
     */
    public int deleteStudent(int id);
}
