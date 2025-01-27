package internship.intern.ServiceImpl;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

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


    public Category postCategory(CategoryDTO categoryDTO){
        return saveOrUpdateCategory(new Category(), categoryDTO);
    }
    public Category saveOrUpdateCategory(Category category, CategoryDTO categoryDTO){
    category.setName(categoryDTO.getName());
    System.out.println("name is "+categoryDTO.getName());
        category.setUser(getUser());
        category.setBudget(getBudget());
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
  public Budget getBudget() {
		Optional<Budget> optional = budgetRepository.findById(36L);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			 throw new EntityNotFoundException("User not found for the given ID");
		}
	}
  public void deleteCategory(Long id){
    categoryRepository.deleteById(id);
    System.out.println("deleted successfully");
  }
  public List<Category> allCategories(){
    return categoryRepository.findAll().stream()
    .collect(Collectors.toList());
  }
    
  public Category updateCategory(Long id, CategoryDTO categoryDTO){
  Optional <Category> optional=  categoryRepository.findById(id);
  if(optional.isPresent()){
    return saveOrUpdateCategory(optional.get(), categoryDTO);


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
}
