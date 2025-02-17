package internship.intern.ServiceImpl;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import internship.intern.dto.BudgetDTO;
import internship.intern.dto.CategoryDTO;
import internship.intern.entity.Budget;
import internship.intern.entity.Category;
import internship.intern.entity.User;
import internship.intern.repository.BudgetRepository;
import internship.intern.repository.CategoryRepository;
import internship.intern.repository.UserRepository;
import internship.intern.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

     private final UserRepository userRepository;
     
     private final BudgetRepository budgetRepository;
     private final CategoryRepository categoryRepository;


    public Category postCategory(CategoryDTO categoryDTO, BudgetDTO budgetDTO){
        return saveOrUpdateCategory(new Category(), categoryDTO , budgetDTO);
    }
    public Category saveOrUpdateCategory(Category category, CategoryDTO categoryDTO , BudgetDTO budgetDTO){
    category.setName(categoryDTO.getName());

    System.out.println("name is "+categoryDTO.getName());
        category.setUser(getUser());
        category.setBudget(getBudget(budgetDTO, new Budget()));
       return  categoryRepository.save(category);

    }
    public User getUser(){
      Optional <User> optional= userRepository.findById(2L);
      if(optional.isPresent()){
          return optional.get();
      }else{
          throw new  EntityNotFoundException("user is not found");
      }
   }
  public Budget getBudget(BudgetDTO budgetDTO, Budget budget) {
       budget.setAmount(budgetDTO.getAmount());
       budget.setStartDate(budgetDTO.getStartDate());
       budget.setEndDate(budgetDTO.getEndDate());
       budget.setUser(getUser());
    return budgetRepository.save(budget);
	}
  public void deleteCategory(Long id){
    categoryRepository.deleteById(id);
    System.out.println("deleted successfully");
  }
  public List<Category> allCategories(){
    return categoryRepository.findAll().stream()
    .collect(Collectors.toList());
  }
    
  public Category updateCategory(Long id, CategoryDTO categoryDTO, BudgetDTO budgetDTO){
  Optional <Category> optional=  categoryRepository.findById(id);
  if(optional.isPresent()){
    return saveOrUpdateCategory(optional.get(), categoryDTO,budgetDTO);


  }else{
    throw new  EntityNotFoundException("expanse is not present with ihe id "+ id);
  }
   
  }

  public Category getCategory(Long id){
    Optional <Category> option= categoryRepository.findById(id);
    if(option.isPresent()){
      return option.get();
    }else{
      throw new EntityNotFoundException("no data found");
    }
  }
  @Override
  public Category getCategoryByName(String name) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getCategoryByName'");
  }
}
 
