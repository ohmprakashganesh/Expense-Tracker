package internship.intern.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;

@Data
public class CategoryDTO {
    
    @NotNull(message = "Name cannot be null")
    private String name;
    private Double amount;
 
     @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private UserDTO user;
    private BudgetDTO budget;
    private List<ExpanseDTO> expanses;




}
