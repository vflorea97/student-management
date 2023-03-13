package ro.mycode.studentmanagement.comparatori;

import ro.mycode.studentmanagement.model.Student;

import java.util.Comparator;

public class StudentComparatorNumeDesc implements Comparator<Student> {

    @Override
    public int compare(Student s1, Student s2) {
        if (s1.getNume().compareTo(s2.getNume()) > 0){
            return -1;
        }
        if (s1.getNume().compareTo(s2.getNume()) < 0){
            return 1;
        }
        else {
            return 0;
        }
    }
}
