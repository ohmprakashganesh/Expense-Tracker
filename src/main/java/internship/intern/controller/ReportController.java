package internship.intern.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internship.intern.DTO2.CompareDTO;
import internship.intern.DTO2.ExpanseSummeryDTO;
import internship.intern.dto.CategoryDTO;
import internship.intern.entity.Expanse;
import internship.intern.entity.User;
import internship.intern.repository.UserRepository;
import internship.intern.service.BudgetService;
import internship.intern.service.CategoryService;
import internship.intern.service.ExpanseService;
import internship.intern.service.UserServices;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ReportController {

    private final ExpanseService expanseService;
    private final CategoryService categoryService;
    private final BudgetService budgetService;
    private final UserRepository userRepository;





//     GET /api/reports/total-expenses/{userId}
// GET /api/reports/expenses-by-category/{userId}
// GET /api/reports/budget-vs-expenses/{userId}
// GET /api/reports/expense-summary/{userId}


@GetMapping("/totalExp")
public ResponseEntity<?> SumOfExpenses(){
    return ResponseEntity.ok(expanseService.getTotalExpenses());

}

@GetMapping("/expByCategory/{name}")
public ResponseEntity<List<Expanse>> expensesByCategory(@PathVariable String name) {
    return  ResponseEntity.ok(expanseService.expByCategory(name));
}

@GetMapping("vs")
public ResponseEntity<CompareDTO> compateBudgetAndExpense() {
    CompareDTO obj= new CompareDTO();
    Optional<User> optional = userRepository.findById(2L);

    Double totalExp= expanseService.getTotalExpensesByUser(optional.get());
    Double totalBudget= budgetService.getTotalBudgetByUser(optional.get());
    if(totalBudget > totalExp){
        Double remain= totalBudget-totalExp;
         obj.setRemaining(remain);
        
    }else if(totalBudget.equals(totalExp)){
        obj.setEquall("Optimum used");
        
    }else{
        Double loss= totalExp-totalBudget;
        obj.setOverUsed(loss);
       }
    obj.setBudget(totalExp);
    obj.setExpense(totalBudget);



       
    return  ResponseEntity.ok(obj);
    
}

@GetMapping("/expSummery")
public ResponseEntity<ExpanseSummeryDTO> summeryMethod() {
    //find category by use and then fetch expanses based of each category 
    ExpanseSummeryDTO expanseSummeryDTO=new ExpanseSummeryDTO();
    // List<String> obj=  new  ArrayList<>();



    Optional<User> optional = userRepository.findById(2L);
		if (optional.isPresent()) {
			 
            List <CategoryDTO> categoryDTOs= categoryService.categoryByUser();
           Double amount= expanseService.getTotalExpenses();
            expanseSummeryDTO.setCategoryDTOs(categoryDTOs); 
            expanseSummeryDTO.setTotalExpenses(amount);
            return ResponseEntity.ok().body(expanseSummeryDTO);
            
		} else {
			throw new EntityNotFoundException("User not found for the given ID expanse table");
		}

       
	}

   @GetMapping("/totalBudget")
   public Double budgetByUser() {
    Optional<User> optional = userRepository.findById(2L);
    Double totalBudget= budgetService.getTotalBudgetByUser(optional.get());
    return totalBudget;
   }
   



   
 

    
}





