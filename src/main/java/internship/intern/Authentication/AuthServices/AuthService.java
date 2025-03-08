package internship.intern.Authentication.AuthServices;

import internship.intern.Authentication.dtos.AuthResponse;
import internship.intern.Authentication.dtos.LoginRequest;
import internship.intern.Authentication.dtos.RegisterRequest;
import internship.intern.entity.User;

public interface AuthService {
    User register(RegisterRequest registerRequest);

    AuthResponse login(LoginRequest loginRequest);


    
}
