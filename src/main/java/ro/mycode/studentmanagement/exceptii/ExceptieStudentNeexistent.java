package ro.mycode.studentmanagement.exceptii;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceptieStudentNeexistent extends RuntimeException{

    public ExceptieStudentNeexistent(){
        super("Studentul nu exista in baza de date!!");
    }
}
