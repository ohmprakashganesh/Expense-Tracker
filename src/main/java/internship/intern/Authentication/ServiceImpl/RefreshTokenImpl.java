package internship.intern.Authentication.ServiceImpl;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import internship.intern.entity.RefreshToken;
import internship.intern.entity.User;
import internship.intern.excepction.NotFoundWithName;
import internship.intern.excepction.RunTimeExp;
import internship.intern.repository.RefreshRepository;
import internship.intern.repository.UserRepository;

@Service
public class RefreshTokenImpl {

    private final UserRepository userRepository;
    private final  RefreshRepository refreshRepository;

    public RefreshTokenImpl(UserRepository userRepository, RefreshRepository refreshRepository) {
        this.userRepository = userRepository;
        this.refreshRepository = refreshRepository;
    }

    public RefreshToken createRefreshToken(String username){
      
           Optional< User> opt= userRepository.findByEmail(username);   
            User user=opt.get();

            RefreshToken refreshToken=user.getRefreshToken();

            if(refreshToken==null){
                long refreshTokenValidity=30*100000;
                refreshToken= RefreshToken.builder()
                .refreshToken(UUID.randomUUID().toString())
                .expirationTime(Instant.now().plusMillis(refreshTokenValidity))
                .user(user)
                .build();

                refreshRepository.save(refreshToken);
            }
            return refreshToken;

           

    }

    public RefreshToken veriRefreshToken(String refreshToken ){

        RefreshToken refToken= refreshRepository.findByRefreshToken(refreshToken).orElseThrow(()-> new NotFoundWithName(refreshToken));
        if(refToken.getExpirationTime().compareTo(Instant.now())<0){
            refreshRepository.delete(refToken);
            throw new  RunTimeExp("refresh token is expired");
        }

        return refToken;


    }

    
    
}
