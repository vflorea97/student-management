package ro.mycode.studentmanagement.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.mycode.studentmanagement.StudentManagementApplication;
import ro.mycode.studentmanagement.model.Student;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StudentManagementApplication.class)
@Transactional
class StudentRepositoryTest {


    @Autowired
    StudentRepository studentRepository;

    @BeforeEach

    public void clean(){

        studentRepository.deleteAll();
    }


    @Test
    public  void  test(){
        Student student = Student.builder().prenume("Alex").nume("Marian").varsta(23).email("dsadas@dasd.com").parola("toto12").build();
        Student student2 = Student.builder().prenume("Mihai").nume("Gona").varsta(21).email("fsdfs@fd.com").parola("toto12").build();
        Student student3 = Student.builder().prenume("Jan").nume("Dobrea").varsta(20).email("55gg@fdasd.com").parola("toto12").build();
        Student student4 = Student.builder().prenume("Daniel").nume("Mic").varsta(27).email("ddasdan@gamil.ro").parola("toto12").build();
        Student student5 = Student.builder().prenume("George").nume("Ion").varsta(24).email("zor1400@gmail.com").parola("toto12").build();

        studentRepository.saveAndFlush(student);
        studentRepository.saveAndFlush(student2);
        studentRepository.saveAndFlush(student3);
        studentRepository.saveAndFlush(student4);
        studentRepository.saveAndFlush(student5);


        List<Student> students =studentRepository.getStudentsOrderByNameAsc().get();

        assertEquals(student3,students.get(0));

    }

    @Test
    public void getStudentsOrderByNameDesc(){
        Student student = Student.builder().prenume("Alex").nume("Marian").varsta(23).email("dsadas@dasd.com").parola("toto12").build();
        Student student2 = Student.builder().prenume("Mihai").nume("Gona").varsta(21).email("fsdfs@fd.com").parola("toto12").build();
        Student student3 = Student.builder().prenume("Jan").nume("Dobrea").varsta(20).email("55gg@fdasd.com").parola("toto12").build();
        Student student4 = Student.builder().prenume("Daniel").nume("Mic").varsta(27).email("ddasdan@gamil.ro").parola("toto12").build();
        Student student5 = Student.builder().prenume("George").nume("Ion").varsta(24).email("zor1400@gmail.com").parola("toto12").build();

        studentRepository.saveAndFlush(student);
        studentRepository.saveAndFlush(student2);
        studentRepository.saveAndFlush(student3);
        studentRepository.saveAndFlush(student4);
        studentRepository.saveAndFlush(student5);


        List<Student> students = studentRepository.getStudentsOrderByNameDesc().get();

        assertEquals(student4,students.get(0));

    }

    @Test
    public void findStudentByEmail(){
        Student student = Student.builder().prenume("Alex").nume("Marian").varsta(23).email("dsadas@dasd.com").parola("toto12").build();
        Student student2 = Student.builder().prenume("Mihai").nume("Gona").varsta(21).email("fsdfs@fd.com").parola("toto12").build();
        Student student3 = Student.builder().prenume("Jan").nume("Dobrea").varsta(20).email("55gg@fdasd.com").parola("toto12").build();
        Student student4 = Student.builder().prenume("Daniel").nume("Mic").varsta(27).email("ddasdan@gamil.ro").parola("toto12").build();
        Student student5 = Student.builder().prenume("George").nume("Ion").varsta(24).email("zor1400@gmail.com").parola("toto12").build();

        studentRepository.saveAndFlush(student);
        studentRepository.saveAndFlush(student2);
        studentRepository.saveAndFlush(student3);
        studentRepository.saveAndFlush(student4);
        studentRepository.saveAndFlush(student5);

        Student students = studentRepository.findStudentByEmail("fsdfs@fd.com").get();

        assertEquals(student2, students);
    }

    @Test
    @Transactional
    public void removeStudentByEmail(){
        Student student = Student.builder().prenume("Alex").nume("Marian").varsta(23).email("dsadas@dasd.com").parola("toto12").build();
        Student student2 = Student.builder().prenume("Mihai").nume("Gona").varsta(21).email("fsdfs@fd.com").parola("toto12").build();
        Student student3 = Student.builder().prenume("Jan").nume("Dobrea").varsta(20).email("55gg@fdasd.com").parola("toto12").build();
        Student student4 = Student.builder().prenume("Daniel").nume("Mic").varsta(27).email("ddasdan@gamil.ro").parola("toto12").build();
        Student student5 = Student.builder().prenume("George").nume("Ion").varsta(24).email("zor1400@gmail.com").parola("toto12").build();

        studentRepository.saveAndFlush(student);
        studentRepository.saveAndFlush(student2);
        studentRepository.saveAndFlush(student3);
        studentRepository.saveAndFlush(student4);
        studentRepository.saveAndFlush(student5);
        studentRepository.removeStudentByEmail("ddasdan@gamil.ro");
        List<Student> students = studentRepository.getStudentsOrderByNameAsc().get();
        assertEquals(false,students.contains(student4));
    }

    @Test
    public void updateStudentEmail() {
        Student student = Student.builder().prenume("Alex").nume("Marian").varsta(23).email("dsadas@dasd.com").parola("toto12").build();


        studentRepository.saveAndFlush(student);

        studentRepository.updateStudentEmail("pocopcpocpoc","dsadas@dasd.com");

        assertNotEquals(Optional.empty(),studentRepository.findStudentByEmail("pocopcpocpoc"));
        assertEquals(Optional.empty(),studentRepository.findStudentByEmail("dsadas@dasd.com"));
    }

    @Test
    public void updateStudentNume() {
        Student student = Student.builder().prenume("Alex").nume("Marian").varsta(23).email("dsadas@dasd.com").parola("toto12").build();

        studentRepository.saveAndFlush(student);

        studentRepository.updateStudentNume("Ionut","dsadas@dasd.com");

        assertNotEquals(Optional.empty(), studentRepository.findStudentByEmailAndNume("dsadas@dasd.com","Ionut" ));
    }

    @Test
    public void updateStudentParola(){
        Student student = Student.builder().prenume("Alex").nume("Marian").varsta(23).email("dsadas@dasd.com").parola("toto12").build();

        studentRepository.saveAndFlush(student);

        studentRepository.updateStudentParola("POCPOC12", "dsadas@dasd.com");

        assertNotEquals(Optional.empty(), studentRepository.findStudentByEmailAndParola("dsadas@dasd.com", "POCPOC12"));

    }
}