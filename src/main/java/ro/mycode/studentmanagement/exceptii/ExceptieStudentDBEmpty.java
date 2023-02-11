package ro.mycode.studentmanagement.exceptii;

public class ExceptieStudentDBEmpty extends RuntimeException{

    public ExceptieStudentDBEmpty(){
        super("Baza de date este goala!!");
    }
}
