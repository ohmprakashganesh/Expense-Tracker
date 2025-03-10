package internship.intern.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;


import lombok.Data;

@Data
public class BudgetDTO {
    
    private  Double amount;

     @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    
     private UserDTO userDTO;

     private List<ExpanseDTO> expanseDTOs;
     private List<CategoryDTO> categoryDTOs;
}
