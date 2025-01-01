package internship.intern.dto;

import java.util.List;

import lombok.Data;


@Data
public class UserDTO {

        private Long uid;
    private  String name;
    private String email;
    private String  image;
    private int number;
    private String address;

    private List<ExpanseDTO> expansesDtos;
    
    private List<BudgetDTO> budgetsDtos;


    private List<CategoryDTO> categoriesDtos;

    
    
}
