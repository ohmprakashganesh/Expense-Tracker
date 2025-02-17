package internship.intern.dto;

import java.util.List;

import internship.intern.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExpanseSummeryDTO {

    private Double totalExpenses;
    // private List<CategoryExpenseDTO> expanseByCategory;
    private Category categories;
    
}
