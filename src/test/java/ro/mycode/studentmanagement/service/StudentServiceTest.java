package ro.mycode.studentmanagement.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.mycode.studentmanagement.exceptii.ExceptieStudentDBEmpty;
import ro.mycode.studentmanagement.exceptii.ExceptieStudentNeexistent;
import ro.mycode.studentmanagement.model.Student;
import ro.mycode.studentmanagement.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;


    @Test
    public  void getAllStudents(){
        Student student = Student.builder().prenume("Alex").nume("Marian").varsta(23).email("dsadas@dasd.com").parola("toto12").build();
        Student student2 = Student.builder().prenume("Mihai").nume("Gona").varsta(21).email("fsdfs@fd.com").parola("toto12").build();
        Student student3 = Student.builder().prenume("Jan").nume("Dobrea").varsta(20).email("55gg@fdasd.com").parola("toto12").build();
        Student student4 = Student.builder().prenume("Daniel").nume("Mic").varsta(27).email("ddasdan@gamil.ro").parola("toto12").build();
        Student student5 = Student.builder().prenume("George").nume("Ion").varsta(24).email("zor1400@gmail.com").parola("toto12").build();

        List<Student> students = new ArrayList<>();

        students.add(student);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);

        doReturn(students).when(studentRepository).findAll();

        assertEquals(5,studentService.gelAllStudents().size());


    }

    @Test
    public  void getAllStudentsEmpty(){
        List<Student> students = new ArrayList<>();
        doReturn(students).when(studentRepository).findAll();
        assertThrows(ExceptieStudentDBEmpty.class, () -> {
            studentService.gelAllStudents();
        });
    }

    @Test
    public void getStudentByEmail() {
        Student student = Student.builder().prenume("Alex").nume("Marian").varsta(23).email("dsadas@dasd.com").parola("toto12").build();

        doReturn(Optional.of(student)).when(studentRepository).findStudentByEmail("ddasdan@gamil.ro");

        assertEquals(Optional.of(student), studentService.getStudentByEmail("ddasdan@gamil.ro"));
    }

    @Test
    public void getStudentByEmailEmpty() {
        doReturn().when(studentRepository).findStudentByEmail("dsadas@dasd.com");
        assertThrows(ExceptieStudentNeexistent.class, () -> {
            studentService.getStudentByEmail("dsadas@dasd.com");
        });
    }


}