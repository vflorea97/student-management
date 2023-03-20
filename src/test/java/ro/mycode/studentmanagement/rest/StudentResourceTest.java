package ro.mycode.studentmanagement.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ro.mycode.studentmanagement.model.Student;
import ro.mycode.studentmanagement.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class StudentResourceTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentResource studentResource;

    private ObjectMapper mapper = new ObjectMapper();

    private MockMvc restMockMvc;

    @BeforeEach
    void initialConfig() {
        restMockMvc = MockMvcBuilders.standaloneSetup(studentResource).build();
    }
    @Captor
    ArgumentCaptor<Student> studentArgumentCaptor;

    @Test
    public void getAllStudents() throws Exception {
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

        doReturn(students).when(studentService).gelAllStudents();

        restMockMvc.perform(get("/api/v1/student/getAllStudents")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(students)));
    }

    @Test
    public void getAllStudentsByNameAsc() throws Exception {
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

        doReturn(students).when(studentService).ordineNumeAsc();

        restMockMvc.perform(get("/api/v1/student/getAllStudentsByNameAsc")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(students)));
    }

    @Test
    public void getAllStudentsByNameDesc()throws Exception {
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

        doReturn(students).when(studentService).ordineNumeDesc();

        restMockMvc.perform(get("/api/v1/student/getAllStudentsByNameDesc")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(students)));
    }

    @Test
    public void getAllStudentByEmail() throws Exception {
        Student student = Student.builder().prenume("Alex").nume("Marian").varsta(23).email("test@gmail.com").parola("toto12").build();

        doReturn(Optional.of(student)).when(studentService).getStudentByEmail("test@gmail.com");

        restMockMvc.perform(get("/api/v1/student/getStudentByEmail/test@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(student)));
    }

    @Test
    public void add() throws Exception {
        Student student = Student.builder().prenume("Alex").nume("Marian").varsta(23).email("test@gmail.com").parola("toto12").build();

        restMockMvc.perform(post("/api/v1/student/add")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(mapper.writeValueAsBytes(student)))
                .andExpect(status().isCreated())
                .andExpect(content().string(mapper.writeValueAsString(student)));

        verify(studentService, times(1)).addStudent(studentArgumentCaptor.capture());

        assertEquals(student,studentArgumentCaptor.getValue());
    }

    @Test
    public void remove() throws Exception {
        Student student = Student.builder().prenume("Alex").nume("Marian").varsta(23).email("test@gmail.com").parola("toto12").build();

        restMockMvc.perform(delete("/api/v1/student/remove")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(mapper.)
                .andExpect(status().isOk())
                .andExpect(content().string("Ai sters cu succes un student"));

    }



}