package internship.intern.ServiceImpl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import internship.intern.dto.ExpanseDTO;
import internship.intern.entity.Expanse;
import internship.intern.entity.User;
import internship.intern.repository.ExpanseRepository;
import internship.intern.repository.UserRepository;
import internship.intern.service.ExpanseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpanseServiceImpl implements ExpanseService {

	private final ExpanseRepository expanseRepository;
	private final UserRepository userRepository;

	@Override
	public Expanse getExpenseById(Long id) {
		Optional<Expanse> optionalExpanse= expanseRepository.findById(id);
		if(optionalExpanse.isPresent()){
			return optionalExpanse.get();
		}else{
			throw new EntityNotFoundException("expanse not fount "+ id);
		}

	}

	public void deleteById(Long id) {
		 expanseRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Expanse with ID " + id + " not found"));
		expanseRepository.deleteById(id);
	}
	


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
		 expanse.setUser(getUser());
		return expanseRepository.save(expanse);
	}
	public User getUser() {
		Optional<User> optional = userRepository.findById(11L);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new EntityNotFoundException("User not found for the given ID");
		}
	}
	


	public List<Expanse> getAllExpenses(){
		return expanseRepository.findAll().stream()
		.sorted(Comparator.comparing(Expanse::getDate).reversed())
		.collect(Collectors.toList());
	}

	
	public Expanse updateExpanse(Long id, ExpanseDTO expanseDTO){
		Optional <Expanse> optional = expanseRepository.findById(id);
		if(optional.isPresent()){
			return  saveUpdateExpanse(optional.get(), expanseDTO);
		}else{
			throw new  EntityNotFoundException("expanse is not present with ihe id "+ id);
		}
	}

	

}
