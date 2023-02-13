package ro.mycode.studentmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity(name = "Student")
@Table(schema = "studenti")
public class Student implements Comparable<Student> {


    @Id
    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")

    private Long id;

    @Column(name = "nume", nullable = false)
    private String nume;

    @Column(name = "prenume", nullable = false)
    private String prenume;

    @Column(name = "email", nullable = false)
    @Size(min = 10,message = "Emailul trebue sa fie de minim 10 caractere")
    private String email;

    @Column(name = "varsta", nullable = false)
    @Max(value = 50, message = "Persoanele care se inscriu trebuie sa aiba maxim 50 de ani")
    private int varsta;

    @Column(name = "parola")
    @Size(min = 6, message = "Parola trebuie sa fie de minim 6 caractere")
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
