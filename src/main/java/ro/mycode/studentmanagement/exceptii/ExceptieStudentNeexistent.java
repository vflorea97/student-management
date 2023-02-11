package ro.mycode.studentmanagement.exceptii;

public class ExceptieStudentNeexistent extends RuntimeException{

    public ExceptieStudentNeexistent(){
        super("Studentul nu exista in baza de date!!");
    }
}
