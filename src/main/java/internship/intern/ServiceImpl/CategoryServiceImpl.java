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
    
  
    public Category postCategory(CategoryDTO categoryDTO, BudgetDTO budgetDTO){
        return saveOrUpdateCategory(new Category(), categoryDTO , budgetDTO);
    }


    public Category saveOrUpdateCategory(Category category, CategoryDTO categoryDTO , BudgetDTO budgetDTO){

       Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
       String email=authentication.getName();
       Optional<User>opt = userRepository.findByEmail(email);
      

      

    category.setName(categoryDTO.getName());
    category.setUser(opt.get());

    System.out.println("name is "+categoryDTO.getName());
       Category obj = categoryRepository.save(category);
      getBudget(budgetDTO, new Budget(), obj , opt.get());
      return categoryRepository.findByName(category.getName());


    }

  //   public User getUser(){
  //     Optional <User> optional= userRepository.findById(2L);
  //     if(optional.isPresent()){
  //         return optional.get();
  //     }else{
  //         throw new  EntityNotFoundException("user is not found");
  //     }
  //  }
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
    throw new UnsupportedOperationException("Unimplemented method 'getCategoryByName'");
  }

  @Override
  public List<CategoryDTO> categoryByUser(User user) {
     List<Category> categories= categoryRepository.findByUser(user);
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
 
