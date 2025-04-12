package internship.intern.Authentication.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AuthResponse {

    // private String name;
    // private String email;
    private String accessToken;
    // private String refreshToken;
}
