package ro.mycode.studentmanagement.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.mycode.studentmanagement.comparatori.StudentComparatorNumeDesc;
import ro.mycode.studentmanagement.dto.StudentDTO;
import ro.mycode.studentmanagement.exceptii.ExceptieStudentDBEmpty;
import ro.mycode.studentmanagement.exceptii.ExceptieStudentExisten;
import ro.mycode.studentmanagement.exceptii.ExceptieStudentNeexistent;
import ro.mycode.studentmanagement.model.Student;
import ro.mycode.studentmanagement.repository.StudentRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Captor
    ArgumentCaptor<Student> studentArgumentCaptor;
    @Captor
    ArgumentCaptor<String> studentField1;
    @Captor
    ArgumentCaptor<String> studentField2;



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

        doReturn(Optional.of(student)).when(studentRepository).findStudentByEmail("ddasdangamil.ro");

        assertEquals(Optional.of(student), studentService.getStudentByEmail("ddasdangamil.ro"));
    }
    @Test
    public void getStudentByEmailException() {
        doReturn(Optional.empty()).when(studentRepository).findStudentByEmail("dsadasdasd.com");
        assertThrows(ExceptieStudentNeexistent.class, () -> {
            studentService.getStudentByEmail("dsadasdasd.com");
        });
    }

    @Test
    public void addStudent(){
        Student student = Student.builder().prenume("Alex").nume("Marian").varsta(23).email("test@gmail.com").parola("toto12").build();

        doReturn(Optional.empty()).when(studentRepository).findStudentByEmail("test@gmail.com");

        studentService.addStudent(student);

        verify(studentRepository,times(1)).saveAndFlush(studentArgumentCaptor.capture());

        assertEquals(studentArgumentCaptor.getValue(),student);
    }
    @Test
    public void addStudentExceptie(){
        Student student = Student.builder().prenume("Alex").nume("Marian").varsta(23).email("test@gmail.com").parola("toto12").build();

        doReturn(Optional.of(student)).when(studentRepository).findStudentByEmail("test@gmail.com");

        assertThrows(ExceptieStudentExisten.class, () ->{
            studentService.addStudent(student);
        });
    }

    @Test
    public void removeStudent(){
        Student student = Student.builder().prenume("Alex").nume("Marian").varsta(23).email("test@gmail.com").parola("toto12").build();

        doReturn(Optional.of(student)).when(studentRepository).findStudentByEmail("test@gmail.com");

        studentService.removeStudent("test@gmail.com");

        verify(studentRepository,times(1)).removeStudentByEmail(studentField1.capture());

        assertEquals("test@gmail.com",studentField1.getValue());
    }
    @Test
    public void removeStudentExceptie() {
        doReturn(Optional.empty()).when(studentRepository).findStudentByEmail("test@gmail.com");

        assertThrows(ExceptieStudentNeexistent.class, () ->{
           studentService.removeStudent("test@gmail.com");
        });
    }

    @Test
    public void ordineNumeAsc() {
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


        Collections.sort(students);
        doReturn(Optional.of(students)).when(studentRepository).getStudentsOrderByNameAsc();
        studentService.ordineNumeAsc();

        assertEquals(student3, students.get(0));
    }
    @Test
    public void ordineNumeAscExceptie(){
        List<Student> students = new ArrayList<>();

        doReturn(Optional.of(students)).when(studentRepository).getStudentsOrderByNameAsc();

        assertThrows(ExceptieStudentDBEmpty.class, () ->{
           studentService.ordineNumeAsc();
        });
    }

    @Test
    public void  ordineNumeDesc() {
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


        Collections.sort(students, new StudentComparatorNumeDesc());

        doReturn(Optional.of(students)).when(studentRepository).getStudentsOrderByNameDesc();
        studentService.ordineNumeDesc();

        assertEquals(student4, students.get(0));
    }
    @Test
    public void ordineNumeDescExceptie(){
        List<Student> students = new ArrayList<>();

        doReturn(Optional.of(students)).when(studentRepository).getStudentsOrderByNameDesc();

        assertThrows(ExceptieStudentDBEmpty.class, () ->{
            studentService.ordineNumeDesc();
        });
    }

    @Test
    public void updateStudentEmail() {
       //todo:mock
        Student student = Student.builder().prenume("Alex").nume("Marian").varsta(23).email("test@gmail.com").parola("toto12").build();
        doReturn(Optional.of(student)).when(studentRepository).findStudentByEmail("vechi@test.com");

        studentService.updateStudentEmail("test@gmail.com","vechi@test.com");
        verify(studentRepository,times(1)).updateStudentEmail(studentField1.capture(),studentField2.capture());


        assertEquals("test@gmail.com",studentField1.getValue());
        assertEquals("vechi@test.com",studentField2.getValue());
    }

    @Test
    public void updateStudentEmailExceptie() {
        doReturn(Optional.empty()).when(studentRepository).findStudentByEmail("test@gmail.com");

        assertThrows(ExceptieStudentNeexistent.class, () ->{
            studentService.updateStudentEmail("nou@gmail.com","test@gmail.com");
        });
    }

    @Test
    public void updateStudentNume() {
        Student student = Student.builder().prenume("Alex").nume("Marian").varsta(23).email("test@gmail.com").parola("toto12").build();

        doReturn(Optional.of(student)).when(studentRepository).findStudentByEmail("test@gmail.com");

        studentService.updateStudentNume("Soptaru","test@gmail.com");

        verify(studentRepository, times(1)).updateStudentNume(studentField1.capture(), studentField2.capture());

        assertEquals("Soptaru",studentField1.getValue());
        assertEquals("test@gmail.com",studentField2.getValue());

    }

    @Test
    public void updateStudentNumeExceptie() {
        doReturn(Optional.empty()).when(studentRepository).findStudentByEmail("test@gmail.com");

        assertThrows(ExceptieStudentNeexistent.class, () ->{
            studentService.updateStudentNume("Soptaru","test@gmail.com");
        });
    }

    @Test
    public void updateStudentParola() {
        Student student = Student.builder().prenume("Alex").nume("Marian").varsta(23).email("test@gmail.com").parola("toto12").build();

        doReturn(Optional.of(student)).when(studentRepository).findStudentByEmail("test@gmail.com");

        studentService.updateStudentParola("pocpoc12","test@gmail.com");

        verify(studentRepository, times(1)).updateStudentParola(studentField1.capture(), studentField2.capture());

        assertEquals("pocpoc12",studentField1.getValue());
        assertEquals("test@gmail.com",studentField2.getValue());
    }

    @Test
    public void updateStudentParolaExceptie() {
        doReturn(Optional.empty()).when(studentRepository).findStudentByEmail("test@gmail.com");
        assertThrows(ExceptieStudentNeexistent.class, () ->{
            studentService.updateStudentParola("pocpoc12","test@gmail.com");
        });
    }

    @Test
    public void updateStudentDTO() {
        StudentDTO studentDTO = new StudentDTO(1L,"Popa","popa@gmail.com","pocpoc12");
        Student student = Student.builder().prenume("Alex").nume("Marian").varsta(23).email("test@gmail.com").parola("toto12").build();

        doReturn(Optional.of(student)).when(studentRepository).findById(studentDTO.getId());

        studentService.updateStudent(studentDTO);

        verify(studentRepository, times(1)).saveAndFlush(studentArgumentCaptor.capture());

        assertEquals(student,studentArgumentCaptor.getValue());

    }

    @Test
    public void updateStudentDTOExceptie() {
        StudentDTO studentDTO = new StudentDTO(1L,"Popa","popa@gmail.com","pocpoc12");
        doReturn(Optional.empty()).when(studentRepository).findById(1L);
        assertThrows(ExceptieStudentNeexistent.class, () ->{
           studentService.updateStudent(studentDTO);
        });
    }
}