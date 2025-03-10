package internship.intern.ServiceImpl;


import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import internship.intern.dto.BudgetDTO;
import internship.intern.dto.CategoryDTO;
import internship.intern.dto.ExpanseDTO;
import internship.intern.entity.Budget;
import internship.intern.entity.Category;
import internship.intern.entity.Expanse;
import internship.intern.entity.User;
import internship.intern.repository.BudgetRepository;
import internship.intern.repository.CategoryRepository;
import internship.intern.repository.UserRepository;
import internship.intern.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final BudgetRepository budgetRepository;
  private final CategoryRepository categoryRepository;
  private final UserRepository userRepository;
    

  public User getLoggedUser(){
    Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
    String email=authentication.getName();
    Optional<User>opt = userRepository.findByEmail(email);
    return opt.get();
}

  
    public Category postCategory(CategoryDTO categoryDTO){
        return saveOrUpdateCategory(new Category(), categoryDTO);
    }

    public Category saveOrUpdateCategory(Category category, CategoryDTO categoryDTO){
    category.setName(categoryDTO.getName());
    category.setUser(getLoggedUser());
       Category obj = categoryRepository.save(category);
      return obj;


    }

  public Budget getBudget(BudgetDTO budgetDTO, Budget budget, Category category, User user) {
       budget.setAmount(budgetDTO.getAmount());
       budget.setStartDate(budgetDTO.getStartDate());
       budget.setEndDate(budgetDTO.getEndDate());
       budget.setUser(user);
       budget.setCategory(category);
    return budgetRepository.save(budget);
	}
  public void deleteCategory(Long id){
    categoryRepository.deleteById(id);
    System.out.println("deleted successfully");
  }



    
  public Category updateCategory(Long id, CategoryDTO categoryDTO){
  Optional <Category> optional=  categoryRepository.findById(id);
  if(optional.isPresent()){
    return saveOrUpdateCategory(optional.get(),categoryDTO );
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
    throw new UnsupportedOperationException("Unimplemented method 'getCategoryByName'");
  }


  public List<Category> allCategories(){
    return categoryRepository.findAll().stream()
    .collect(Collectors.toList());
  }


  @Override
  public List<CategoryDTO> categoryByUser() {
     List<Category> categories= categoryRepository.findByUser(getLoggedUser());
     List<CategoryDTO> categoryDTOs=new ArrayList<>();

     //loop to convert category to category dto
      for(Category category: categories){
         CategoryDTO dto= new CategoryDTO();
         dto.setName(category.getName());
         dto.setAmount(category.getBudget().getAmount());
         dto.setEndDate(category.getBudget().getEndDate());
         dto.setStartDate(category.getBudget().getStartDate());
         //loop to convert expanse dto to expanse dto
         List<ExpanseDTO> expanseDTOs=new ArrayList<>();
         for(Expanse expanse: category.getExpanses()){
          ExpanseDTO obj= new ExpanseDTO();
          obj.setId(expanse.getId());
          obj.setAmount(expanse.getAmount());
          obj.setTitle(expanse.getTitle());     
          obj.setDescription(expanse.getDescription());
          obj.setDate(expanse.getDate());
          expanseDTOs.add(obj);
         }
         dto.setExpanses(expanseDTOs);
         categoryDTOs.add(dto);
      }
      return categoryDTOs;
      

  }

}
 
