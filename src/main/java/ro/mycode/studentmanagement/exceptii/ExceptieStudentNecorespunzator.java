package ro.mycode.studentmanagement.exceptii;

public class ExceptieStudentNecorespunzator extends RuntimeException{

    public ExceptieStudentNecorespunzator(){
        super("Niciun student nu corespunde cu criteriile selectate!!");
    }
}
