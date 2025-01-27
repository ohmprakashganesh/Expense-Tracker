package internship.intern.dto;

import lombok.Data;

import java.util.List;


import jakarta.validation.constraints.NotNull;

@Data
public class CategoryDTO {
    
    @NotNull(message = "Name cannot be null")
    private String name;
    
    private UserDTO user;
    private BudgetDTO budget;
    private List<ExpanseDTO> expanses;




}
