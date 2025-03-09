package internship.intern.Authentication.ServiceImpl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import internship.intern.Authentication.AuthServices.AuthService;
import internship.intern.Authentication.AuthServices.JwtService;
import internship.intern.Authentication.dtos.AuthResponse;
import internship.intern.Authentication.dtos.LoginRequest;
import internship.intern.Authentication.dtos.RegisterRequest;
import internship.intern.entity.User;
import internship.intern.entity.UserRole;
import internship.intern.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class authServiceImpl  implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenImpl refreshTokenImpl;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest req) {
        User user = new User();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setAddress(req.getAddress());
        user.setNumber(req.getNumber());
        user.setRole(UserRole.USER);
        user.setImage(req.getImage());

        User saved= userRepository.save(user);
        var accessToken= jwtService.generateToken(saved);
        var refreshToken= refreshTokenImpl.createRefreshToken(saved.getEmail());


         return AuthResponse.builder()
         .accessToken(accessToken)
         .refreshToken(refreshToken.getRefreshToken())
         .name(saved.getName())
         .email(saved.getEmail())
         .build();
       
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        System.out.println("this is form inner ");
        try{
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        }catch(Exception ex){
            System.out.println(ex.getMessage()+ ex.getStackTrace());
        }

        User user=userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(()-> new UsernameNotFoundException("not found"));
        var accessToken=jwtService.generateToken(user);

        return  AuthResponse.builder()
        .accessToken(accessToken)
        .refreshToken(accessToken)
        .name(user.getName())
        .email(user.getEmail())
        .build();   
    }
    
}
