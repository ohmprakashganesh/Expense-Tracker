package internship.intern.ServiceImpl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import internship.intern.DTO2.ExpanseSummeryDTO;
import internship.intern.dto.ExpanseDTO;
import internship.intern.entity.Budget;
import internship.intern.entity.Category;
import internship.intern.entity.Expanse;
import internship.intern.entity.User;
import internship.intern.repository.BudgetRepository;
import internship.intern.repository.CategoryRepository;
import internship.intern.repository.ExpanseRepository;
import internship.intern.repository.UserRepository;
import internship.intern.service.ExpanseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpanseServiceImpl implements ExpanseService {

	private final ExpanseRepository expanseRepository;
	private final BudgetRepository budgetRepository;
	private final CategoryRepository categoryRepository;
	private final UserRepository userRepository;

	public User getLoggedUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String email=authentication.getName();
        Optional<User>opt = userRepository.findByEmail(email);
        return opt.get();
    }


	@Override
	public Expanse getExpenseById(Long id) {
		Optional<Expanse> optionalExpanse= expanseRepository.findById(id);
		if(optionalExpanse.isPresent()){
			return optionalExpanse.get();
		}else{
			throw new EntityNotFoundException("expanse not fount "+ id);
		}

	}


	


	public Expanse postExpanse(ExpanseDTO expanseDTO) {	
		Expanse expanse= new Expanse();
		expanse.setTitle(expanseDTO.getTitle());
		expanse.setAmount(expanseDTO.getAmount());
         expanse.setDate(expanseDTO.getDate());
         expanse.setDescription(expanseDTO.getDescription()); 
		 expanse.setUser(getLoggedUser());
		 expanse.setCategory(categoryGet(expanseDTO.getCategory()));
		return expanseRepository.save(expanse);
	}
	

	// private Expanse saveUpdateExpanse(Expanse expanse,ExpanseDTO expanseDTO)
	// {
		
	// 	expanse.setTitle(expanseDTO.getTitle());
	// 	expanse.setAmount(expanseDTO.getAmount());
    //      expanse.setDate(expanseDTO.getDate());
    //      expanse.setDescription(expanseDTO.getDescription()); 
	// 	 expanse.setUser(getLoggedUser());
	// 	 expanse.setCategory(categoryGet(expanseDTO.getCategory()));
	// 	return expanseRepository.save(expanse);
	// }

  

	public Category categoryGet(String name) {
		Optional<Category> optional = categoryRepository.findByName(name);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new EntityNotFoundException("category  not found for the given ID for expanse table");
		}
	}



	public Budget getBudget() {
		Optional<Budget> optional = budgetRepository.findById(36L);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new EntityNotFoundException("User not found for the given ID for expanse table");
		}
	}



	public List<Expanse> getAllExpenses(){
		return expanseRepository.findByUser(getLoggedUser()).stream()
		.sorted(Comparator.comparing(Expanse::getDate).reversed())
		.collect(Collectors.toList());
	}

	
	public Expanse updateExpanse(Long id, ExpanseDTO expanseDTO){

		Optional <Expanse> optional = expanseRepository.findById(id);
		if(optional.isPresent()){
  Category category = categoryRepository.findByName(expanseDTO.getCategory())
  .orElseThrow(() -> new EntityNotFoundException("Category not found with name " + expanseDTO.getCategory()));			

			   category.setName(expanseDTO.getCategory());
			   Expanse expanse= optional.get();
			   expanse.setCategory(category);
			   expanse.setAmount(expanseDTO.getAmount());
			   expanse.setDescription(expanseDTO.getDescription());
			   expanse.setTitle(expanseDTO.getTitle());
			return  expanseRepository.save(expanse);
		}else{
			throw new  EntityNotFoundException("expanse is not present with ihe id "+ id);
		}
	}


	public Double getTotalExpenses(){
		return expanseRepository.getTotalExpenses();
	
	}


	@Override
	public Category getCategoryByName(String name) {
		   return expanseRepository.getCategoryByName(name);
	}

	public ExpanseSummeryDTO getSummery(){
		return null;
	   }

	@Override
	public List<Expanse> expByCategory(String name) {
		return expanseRepository.findByCategoryName(name);
	}

	// @Override
	// public Double getTotalExpensesByUser(User user) {
	// 	return expanseRepository.findTotalExpanseByUser(user);
	// }


	@Override
	public void deleteById(Long id) {
		expanseRepository.findById(id)
		.orElseThrow(() -> new EntityNotFoundException("Expanse with ID " + id + " not found"));
	expanseRepository.deleteById(id);
	System.out.println("deleted sucessfully"+ id);
	}


	@Override
	public Double getTotalExpensesByUser(User user) {
		return expanseRepository.findAmountByUser(user);
	}



	
	




	// @Override
	// public ExpanseSummeryDTO getExpenseSummery() {
	// 	Double totalExpense=expanseRepository.getTotalExpenses(2L);

	// 	List <CategoryExpenseDTO> expenseByCategory= expanseRepository.getExpansesByCategory();
	// 	return new ExpanseSummeryDTO(totalExpense, expenseByCategory);

	// }

	// @Override
	// public List<CategoryExpenseDTO> getExpensesByCategory() {
	// 	// TODO Auto-generated method stub
	// 	throw new UnsupportedOperationException("Unimplemented method 'getExpensesByCategory'");
	// }

	// @Override
	// public List<MonthlyExpenseDTO> getMonthlyExpenseReport() {
	// 	// TODO Auto-generated method stub
	// 	throw new UnsupportedOperationException("Unimplemented method 'getMonthlyExpenseReport'");
	// }

	

}
