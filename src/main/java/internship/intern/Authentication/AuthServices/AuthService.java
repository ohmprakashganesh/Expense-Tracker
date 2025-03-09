package internship.intern.Authentication.AuthServices;

import internship.intern.Authentication.dtos.AuthResponse;
import internship.intern.Authentication.dtos.LoginRequest;
import internship.intern.Authentication.dtos.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest registerRequest);

    AuthResponse login(LoginRequest loginRequest);


    
}
