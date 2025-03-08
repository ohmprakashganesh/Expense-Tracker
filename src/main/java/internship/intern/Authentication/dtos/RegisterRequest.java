package internship.intern.Authentication.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

    private  String name;
    private String email;
    private String password;
    private String  image;
     private int number;
    private String address;





    
}
