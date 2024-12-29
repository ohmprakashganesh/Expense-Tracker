package internship.intern.service;

import org.springframework.stereotype.Service;

import internship.intern.dto.ExpanseDTO;
import internship.intern.entity.Expanse;
import internship.intern.repository.ExpanseRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpanseServiceImpl implements ExpanseService {

	private final ExpanseRepository expanseRepository;
	
	public Expanse postExpanse(ExpanseDTO expanseDTO) {
		
		return saveUpdateExpanse(new Expanse(), expanseDTO);
	}
	
	private Expanse saveUpdateExpanse(Expanse expanse,ExpanseDTO expanseDTO)
	{
		
		expanse.setTitle(expanseDTO.getTitle());
		expanse.setCategory(expanseDTO.getCategory());
		expanse.setAmount(expanseDTO.getAmount());
         expanse.setDate(expanseDTO.getDate());
         expanse.setDescription(expanseDTO.getDescription());
  
		
		return expanseRepository.save(expanse);
	}
	
	
}
