package internship.intern.service;

import internship.intern.dto.ExpanseDTO;
import internship.intern.entity.Expanse;

public interface ExpanseService {

	Expanse postExpanse(ExpanseDTO expanseDTO);
}
