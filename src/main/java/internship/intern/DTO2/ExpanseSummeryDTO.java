package internship.intern.DTO2;


import java.util.List;

import internship.intern.dto.CategoryDTO;
import lombok.Data;

@Data
public class ExpanseSummeryDTO {

    private Double totalExpenses;
    // private List<String> categoryNames;
    // private List <String> Expanses;
    private List<CategoryDTO> categoryDTOs;

   
    
}
