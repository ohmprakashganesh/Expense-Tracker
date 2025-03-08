package internship.intern.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Budget {

    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Id
    private Long bid;

    @NotNull
    private  Double amount;

    @NotNull(message = "please insert date")
     @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @NotNull(message = "please insert date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
     private User user;

     @OneToOne  
     @JoinColumn(name = "category_id", nullable = false, unique = true )
     private Category category;






}
