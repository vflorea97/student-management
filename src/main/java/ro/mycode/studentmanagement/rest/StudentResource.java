package ro.mycode.studentmanagement.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.mycode.studentmanagement.model.Student;
import ro.mycode.studentmanagement.repository.StudentRepository;
import ro.mycode.studentmanagement.service.StudentService;

import java.util.List;

@RestController
@Slf4j
public class StudentResource {
    private final StudentRepository studentRepository;

    private StudentService studentService;

    public StudentResource(StudentService studentService,
                           StudentRepository studentRepository){
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    @GetMapping("api/v1/student/getAllStudents")
    public ResponseEntity<List<Student>> getAllStudent(){
        log.info("REST request to get all students");
        List<Student> students = studentService.gelAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("api/v1/student/getAllStudentsByNameAsc")
    public ResponseEntity<List<Student>> getAllStudentsByNameAsc(){
        log.info("REST request to get all students by name ascendent");
        List<Student> students = studentService.ordineNumeAsc();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("api/v1/student/getAllStudentsByNameDesc")
    public ResponseEntity<List<Student>> getAllStudentsByNameDesc(){
        log.info("REST request to get all students by name descendent");
        List<Student> students = studentService.ordineNumeDesc();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("api/v1/student/getStudentByEmail/{email}")
    public ResponseEntity<Student> getStudentByEmail(@PathVariable String email){
        log.info("REST request to gel a student by email = {}", email);
        Student student = studentRepository.findStudentByEmail(email).get();
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping("api/v1/student/add")
    public ResponseEntity<Student> addStudent(@RequestBody Student student){

        log.info("Rest api to add a new magain {}", student);
        this.studentService.addStudent(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }
}
