package internship.intern.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import internship.intern.dto.BudgetDTO;
import internship.intern.entity.Budget;
import internship.intern.entity.User;
import internship.intern.repository.BudgetRepository;
import internship.intern.repository.UserRepository;
import internship.intern.service.BudgetService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;

     public Budget postBudget(BudgetDTO budgetDTO){
      return postOrUpdateBudget(new Budget(), budgetDTO);
      

     }
    
   
     public Budget postOrUpdateBudget(Budget budget, BudgetDTO budgetDTO){
        budget.setAmount(budgetDTO.getAmount());
        budget.setEndDate(budgetDTO.getEndDate());
        budget.setStartDate(budgetDTO.getStartDate());
        budget.setUser(getUser());
        return  budgetRepository.save(budget);
     }

     public User getUser(){
        Optional <User> optional= userRepository.findById(5L);
        if(optional.isPresent()){
            return optional.get();
        }else{
            throw new  EntityNotFoundException("user is not found");
        }

     }

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
    Budget budget= new Budget();
    Optional <Budget> optional= budgetRepository.findById(id);
    if(optional.isPresent()){
       return postOrUpdateBudget(budget, budgetDTO);
    }else{
        throw new EntityNotFoundException("no such data lies in database ");
    }

    }











    
}
