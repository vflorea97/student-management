package ro.mycode.studentmanagement.service;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import ro.mycode.studentmanagement.dto.StudentDTO;
import ro.mycode.studentmanagement.exceptii.ExceptieStudentDBEmpty;
import ro.mycode.studentmanagement.exceptii.ExceptieStudentExisten;
import ro.mycode.studentmanagement.exceptii.ExceptieStudentNeexistent;
import ro.mycode.studentmanagement.model.Student;
import ro.mycode.studentmanagement.repository.StudentRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;

    }

    public List<Student> gelAllStudents() throws ExceptieStudentDBEmpty{
        List<Student> students = studentRepository.findAll();
        if (students.size() > 0) {
            return students;
        }
        throw new ExceptieStudentDBEmpty();
    }

    public Optional<Student> getStudentByEmail(String email) throws ExceptieStudentNeexistent{
        Optional<Student> student = studentRepository.findStudentByEmail(email);
        if (student.isPresent()){
            return student;
        }
        throw new ExceptieStudentNeexistent();
    }

    @Transactional
    public void addStudent(Student student) throws ExceptieStudentExisten {
        Optional<Student> student1 = studentRepository.findStudentByEmail(student.getEmail());
        if (student1.equals(Optional.empty())){
            studentRepository.saveAndFlush(student);
        }else{
            throw new ExceptieStudentExisten();
        }
    }

    @Transactional
    public void removeStudent(String email) throws ExceptieStudentNeexistent {
        Optional<Student> student = studentRepository.findStudentByEmail(email);
        if (student.isPresent()){
            studentRepository.removeStudentByEmail(email);
        }else{
            throw new ExceptieStudentNeexistent();
        }
    }

    public List<Student> ordineNumeAsc() throws ExceptieStudentDBEmpty{
        List<Student> students = studentRepository.getStudentsOrderByNameAsc().get();
        if (students.size() > 0){
            return students;
        }else{
            throw new ExceptieStudentDBEmpty();
        }
    }

    public List<Student> ordineNumeDesc() throws ExceptieStudentDBEmpty{
        List<Student> students = studentRepository.getStudentsOrderByNameDesc().get();
        if (students.size() > 0){
            return students;
        }else{
            throw new ExceptieStudentDBEmpty();
        }
    }

    @Transactional
    @Modifying
    public void updateStudentEmail(String emailNou, String email) throws ExceptieStudentNeexistent{
        Optional<Student> student = studentRepository.findStudentByEmail(email);
        if (student.isPresent()){
            studentRepository.updateStudentEmail(emailNou, email);
        }else{
            throw new ExceptieStudentNeexistent();
        }
    }

    @Transactional
    @Modifying
    public void updateStudentNume(String nume, String email) throws ExceptieStudentNeexistent{
        Optional<Student> student = studentRepository.findStudentByEmail(email);
        if (student.isPresent()){
            studentRepository.updateStudentEmail(nume, email);
        }else{
            throw new ExceptieStudentNeexistent();
        }
    }

    @Transactional
    @Modifying
    public void updateStudentParola(String parola, String email) throws ExceptieStudentNeexistent{
        Optional<Student> student = studentRepository.findStudentByEmail(email);
        if (student.isPresent()){
            studentRepository.updateStudentEmail(parola, email);
        }else{
            throw new ExceptieStudentNeexistent();
        }
    }

    @Transactional
    @Modifying
    public void updateStudent(StudentDTO studentDTO) throws ExceptieStudentNeexistent{

        Optional<Student> student = studentRepository.findById(studentDTO.getId());
        if (student.isPresent()){
            Student s = student.get();

            if (!studentDTO.getEmail().equals("")){
                s.setEmail(studentDTO.getEmail());
            }
            if (!studentDTO.getNume().equals("")){
                s.setNume(studentDTO.getNume());
            }
            if (!studentDTO.getParola().equals("")){
                s.setParola(studentDTO.getParola());
            }
            studentRepository.saveAndFlush(s);
        }else {
            throw new ExceptieStudentNeexistent();
        }

    }
}
