package internship.intern.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Budget {

    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Id
    private Long bid;
    private  Double amount;
    private Date startDate;
    private Date endDate;
    @ManyToOne
@JoinColumn(name = "user_id", nullable = false)
private User user;

}
