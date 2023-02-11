package ro.mycode.studentmanagement.exceptii;

public class ExceptieStudentExisten extends RuntimeException{

    public ExceptieStudentExisten(){
        super("Acest student exista deja in baza de date!!");
    }
}
