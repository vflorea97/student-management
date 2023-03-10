package ro.mycode.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.mycode.studentmanagement.model.Student;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select s from Student s order by s.nume asc")
    Optional<List<Student>> getStudentsOrderByNameAsc();

    @Query("select s from Student s order by s.nume desc ")
    Optional<List<Student>> getStudentsOrderByNameDesc();

    Optional<Student> findStudentByEmail(String email);
    Optional<Student> findStudentByEmailAndNume(String email, String nume);
    Optional<Student> findStudentByEmailAndParola(String email, String parola);

    @Transactional
    Optional<Student> removeStudentByEmail(String email);

    @Transactional
    @Modifying
    @Query("update Student s set s.email = ?1 where s.email = ?2")
    void updateStudentEmail(String emailNou, String email);

    @Transactional
    @Modifying
    @Query("update Student s set s.nume = ?1 where s.email = ?2")
    void updateStudentNume(String nume, String email);

    @Transactional
    @Modifying
    @Query("update Student s set s.parola = ?1 where s.email = ?2")
    void updateStudentParola(String parola, String email);


}
