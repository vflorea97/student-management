package ro.mycode.studentmanagement.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    @NotNull
    private Long id;
    private String name = "";
    private String email = "";
    private String parola = "";
}
