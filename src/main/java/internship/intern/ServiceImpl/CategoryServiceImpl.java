package internship.intern.ServiceImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.slf4j.LoggerFactory;


import internship.intern.dto.CategoryDTO;
import internship.intern.dto.ExpanseDTO;
import internship.intern.entity.Budget;
import internship.intern.entity.Category;
import internship.intern.entity.Expanse;
import internship.intern.entity.User;
import internship.intern.repository.BudgetRepository;
import internship.intern.repository.CategoryRepository;
import internship.intern.repository.ExpanseRepository;
import internship.intern.repository.UserRepository;
import internship.intern.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final BudgetRepository budgetRepository;
  private final CategoryRepository categoryRepository;
  private final UserRepository userRepository;
    private final ExpanseRepository expanseRepository;
    
  private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

  public User getLoggedUser(){
    Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
    String email=authentication.getName();
    Optional<User>opt = userRepository.findByEmail(email);
    return opt.get();
}

  
    public Category postCategory(CategoryDTO categoryDTO){
        return save(new Category(), categoryDTO);
    }


    public Category save(Category category, CategoryDTO categoryDTO){
    category.setName(categoryDTO.getName());
    category.setBudget(budgetGet(categoryDTO,new Budget()));
    category.setUser(getLoggedUser());
     Category obj = categoryRepository.save(category);
    return obj;
    }

    public Budget budgetGet(CategoryDTO categoryDTO, Budget budget){
      budget.setAmount(categoryDTO.getAmount());
      budget.setStartDate(categoryDTO.getStartDate());
       return budgetRepository.save(budget);
    }

  // public Budget getBudget(BudgetDTO budgetDTO, Budget budget, Category category, User user) {
  //      budget.setAmount(budgetDTO.getAmount());
  //      budget.setStartDate(budgetDTO.getStartDate());
  //      budget.setUser(user);
  //      budget.setCategory(category);
  //   return budgetRepository.save(budget);
	// }


  @Transactional
  public void deleteCategory(Long id) {
      Optional<Category> categoryOpt = categoryRepository.findById(id);
  
      if (categoryOpt.isPresent()) {
          Category category = categoryOpt.get();
  
          // 1. Delete all related expanses
          expanseRepository.deleteByCategory(category);
  
          // 2. Delete the category (which has a cascade = ALL relation with budget)
          categoryRepository.deleteById(1L);; // this should also delete budget
  
          System.out.println("Category, related expanses, and budget deleted successfully");
      } else {
          System.out.println("Category not found with ID: " + id);
      }
  }
  
  




  public Category updateCategory(Long id, CategoryDTO categoryDTO){
  Optional <Category> optional=  categoryRepository.findById(id);
     

  if(optional.isPresent()){
    Category category= optional.get();
    category.setName(categoryDTO.getName());

    //update the amount of budget object with this category
    Budget budget=category.getBudget();
    if(budget != null){
      budget.setAmount(categoryDTO.getAmount());
    }
     
   
    return categoryRepository.save(category);
  }else{
    throw new  EntityNotFoundException("expanse is not present with ihe id "+ id);
  }
   
  }

  // public Budget updateBudget(CategoryDTO dto){
  //   Category.
  //   Budget budget= dto.getBudget()
  // budget.setAmount(dto.getAmount());
  // budget.setStartDate(dto.getStartDate());
  // return budgetRepository.save(budget);
  // }

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
    System.out.println("print test fo find by cateogry name ");
      return categoryRepository.findByName(name)
          .map(existing -> {
              Category cat = new Category();
              cat.setCid(existing.getCid());
              cat.setName(existing.getName());
              cat.setBudget(existing.getBudget());
              cat.setExpanses(existing.getExpanses());
              cat.setUser(getLoggedUser());
              return cat;
          })
          .orElseThrow(() -> new EntityNotFoundException("Category not found: " + name));
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


  @Override
  public Double getTotalBudgetByUser(User user) {
     return categoryRepository.findTotalBudgetByUser(user);
  
  }

}
 
