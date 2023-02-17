package ro.mycode.studentmanagement.rest;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ro.mycode.studentmanagement.dto.StudentDTO;
import ro.mycode.studentmanagement.model.Student;
import ro.mycode.studentmanagement.repository.StudentRepository;
import ro.mycode.studentmanagement.service.StudentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class StudentResource {
    private final StudentRepository studentRepository;

    private StudentService studentService;

    public StudentResource(StudentService studentService,
                           StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    @GetMapping("api/v1/student/getAllStudents")
    public ResponseEntity<List<Student>> getAllStudent() {
        log.info("REST request to get all students");
        List<Student> students = studentService.gelAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("api/v1/student/getAllStudentsByNameAsc")
    public ResponseEntity<List<Student>> getAllStudentsByNameAsc() {
        log.info("REST request to get all students by name ascendent");
        List<Student> students = studentService.ordineNumeAsc();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("api/v1/student/getAllStudentsByNameDesc")
    public ResponseEntity<List<Student>> getAllStudentsByNameDesc() {
        log.info("REST request to get all students by name descendent");
        List<Student> students = studentService.ordineNumeDesc();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("api/v1/student/getStudentByEmail/{email}")
    public ResponseEntity<Student> getStudentByEmail(@PathVariable String email) {
        log.info("REST request to gel a student by email = {}", email);
        Student student = studentService.getStudentByEmail(email).get();
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping("api/v1/student/add")
    public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student) {

        log.info("Rest api to add a new magain {}", student);
        this.studentService.addStudent(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @DeleteMapping("api/v1/student/remove")
    public ResponseEntity<String> removeStudent( @RequestParam String email){
        log.info("REST request to remove one student");
        studentService.removeStudent(email);
        return new ResponseEntity<>("Ai sters cu succes un student", HttpStatus.OK);
    }

    @PutMapping("api/v1/student/update")
    public ResponseEntity<String> updateStudent(@Valid @RequestBody StudentDTO studentDTO){
        log.info("REST request to update student{}", studentDTO);
        studentService.updateStudent(studentDTO);
        return new ResponseEntity<>("Ai updata atributul cu succes", HttpStatus.OK);

    }
}
