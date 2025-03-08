package internship.intern.Authentication.AuthController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internship.intern.Authentication.AuthServices.AuthService;
import internship.intern.Authentication.dtos.AuthResponse;
import internship.intern.Authentication.dtos.LoginRequest;
import internship.intern.Authentication.dtos.RegisterRequest;
import internship.intern.entity.User;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    @PostMapping("/register")
     public ResponseEntity<User>  Register(@RequestBody RegisterRequest req){  
     return ResponseEntity.ok(authService.register(req));
     }

     @PostMapping("/login")
     public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest req) {
      System.out.println("hello guys ");
        return ResponseEntity.ok( authService.login(req));
        
     }
     
    
}
