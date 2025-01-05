package internship.intern.dto;

import lombok.Data;

import jakarta.validation.constraints.NotNull;

@Data
public class CategoryDTO {
    
    @NotNull(message = "Name cannot be null")
    private String name;
}
