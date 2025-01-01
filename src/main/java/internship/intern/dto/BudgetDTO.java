package internship.intern.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BudgetDTO {
    
    private  Double amount;
    private Date startDate;
    private Date endDate;
     private UserDTO userDTO;
}
