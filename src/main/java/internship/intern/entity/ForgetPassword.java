package internship.intern.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class ForgetPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long fid;
    private Date expirationTime;
    private int  otp;
    
  
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    
}
