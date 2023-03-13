package ro.mycode.studentmanagement.exceptii;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceptieStudentDBEmpty extends RuntimeException{

    public ExceptieStudentDBEmpty(){
        super("Baza de date este goala!!");
    }
}
