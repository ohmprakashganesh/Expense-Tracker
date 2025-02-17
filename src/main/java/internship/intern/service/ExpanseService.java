package internship.intern.service;

import java.util.List;


import internship.intern.dto.ExpanseDTO;
import internship.intern.dto.ExpanseSummeryDTO;
import internship.intern.entity.Category;
import internship.intern.entity.Expanse;

public interface ExpanseService {

	Expanse postExpanse(ExpanseDTO expanseDTO);

	List<Expanse> getAllExpenses();

	Expanse getExpenseById (Long id);

	void deleteById(Long id);
 
    Double getTotalExpenses();

	Category getCategoryByName(String categoryName);




    ExpanseSummeryDTO getSummery();
	
	// List<CategoryExpenseDTO> getExpensesByCategory();

	// List<MonthlyExpenseDTO> getMonthlyExpenseReport();


	
	Expanse updateExpanse(Long id, ExpanseDTO expanseDTO);
}
