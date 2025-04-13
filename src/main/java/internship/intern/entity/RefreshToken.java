package internship.intern.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class RefreshToken {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;

    @Column(nullable = false, length = 500)
    @NotBlank(message = "Please enter refresh token value!")
    private String refreshToken;

    @Column(nullable = false)
    private Instant expirationTime;

   @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private Long tokenId;

    // @Column(nullable = false, length = 500)
    // @NotBlank(message = "Please enter refresh token value!")
    // private String refreshToken;


    // @Column(nullable = false)
    // private  Instant expirationTime;

    // @OneToOne
    // private User user;
    
}
