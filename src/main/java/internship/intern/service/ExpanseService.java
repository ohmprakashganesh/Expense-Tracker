package internship.intern.service;

import java.util.List;

import internship.intern.dto.ExpanseDTO;
import internship.intern.entity.Expanse;

public interface ExpanseService {

	Expanse postExpanse(ExpanseDTO expanseDTO);

	List<Expanse> getAllExpenses();

	Expanse getExpenseById (Long id);

	void deleteById(Long id);
	
	Expanse updateExpanse(Long id, ExpanseDTO expanseDTO);
}
