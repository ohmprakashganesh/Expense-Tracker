package internship.intern.ServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import internship.intern.dto.BudgetDTO;
import internship.intern.entity.Budget;
import internship.intern.entity.Category;
import internship.intern.entity.User;
import internship.intern.repository.BudgetRepository;
import internship.intern.repository.CategoryRepository;
import internship.intern.repository.UserRepository;
import internship.intern.service.BudgetService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;

    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;


    //  public Budget postBudget(BudgetDTO budgetDTO){
    //     log.info("Received BudgetDTO: {}", budgetDTO);
    //   return postOrUpdateBudget(new Budget(), budgetDTO);
      
    //  }
    
   
    //  public Budget postOrUpdateBudget(Budget budget, BudgetDTO budgetDTO){
        
    //    Optional<Category> category = categoryRepository.findById(2L);
        

    //     System.out.println("BudgetDTO amount: " + budgetDTO.getAmount());
    //     System.out.println("BudgetDTO startDate: " + budgetDTO.getStartDate());
    //     System.out.println("BudgetDTO endDate: " + budgetDTO.getEndDate());
    
    //     // Map fields
    //     budget.setAmount(budgetDTO.getAmount());
    //     budget.setEndDate(budgetDTO.getEndDate());
    //     budget.setStartDate(budgetDTO.getStartDate());
    //     return  budgetRepository.save(budget);  
    //  }
    //  public User getUser(){
    //     Optional <User> optional= userRepository.findById(2L);
    //     if(optional.isPresent()){
    //         return optional.get();
    //     }else{
    //         throw new  EntityNotFoundException("user is not found");
    //     }
    //  }

    
     public Budget findBudget(Long id){
        Optional <Budget> optional= budgetRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }else{
            throw new EntityNotFoundException("not such data is found");
        }
     }

  public  List<Budget> findBudgets(){
    List<Budget> list= new ArrayList<>();
     list=budgetRepository.findAll();
     return list.stream().collect(Collectors.toList());

  }
   public  void deleteBudget(Long id){
     budgetRepository.deleteById(id);
     System.out.println("successfully deleted  Budget of id "+id);
     }


    public Budget  updateBudget(Long id, BudgetDTO budgetDTO){
   
    Optional <Budget> optional= budgetRepository.findById(id);
    if(optional.isPresent()){
        Budget budget= optional.get();
        budget.setAmount(budgetDTO.getAmount());
       return  budgetRepository.save(budget);
    }else{
        throw new EntityNotFoundException("no such data lies in database ");
    }

    }











    
}
