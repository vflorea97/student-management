package ro.mycode.studentmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity(name = "Student")
@Table(schema = "studenti")
public class Student implements Comparable<Student> {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id

    private Long id;
    private String nume;
    private String prenume;
    private String email;
    private int varsta;
    private String parola;

    @Override
    public int compareTo(Student student) {
        if (this.email.compareTo(student.email) > 0){
            return 1;
        }
        if (this.email.compareTo(student.email) < 0){
            return -1;
        }else{
            return 0;
        }
    }

    @Override
    public boolean equals(Object o){
        Student student = (Student) o;
        return this.email.equals(student.email);
    }
}
