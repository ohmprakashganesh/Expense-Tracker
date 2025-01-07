package internship.intern.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import internship.intern.dto.BudgetDTO;
import internship.intern.entity.Budget;
import internship.intern.service.BudgetService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequiredArgsConstructor
public class BudgetController {

    private final  BudgetService budgetService;

@PostMapping
   public  ResponseEntity<?> postBudget(@RequestBody BudgetDTO budgetDTO){
    Budget budget= budgetService.postBudget(budgetDTO);
    if(budget != null){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(budget);
    }else{
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("unable to post the form data ");
    }

   }
    
   @GetMapping("/single")
   public ResponseEntity<?> getBudget(@PathVariable Long id){
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(budgetService.findBudget(id));
   }

   @GetMapping("/all")
   public ResponseEntity<?> getAllBudgets() {
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(budgetService.findBudgets());
      
   }
   @PostMapping("/update")
   public ResponseEntity<?> postMethodName(@PathVariable Long id,  @RequestBody BudgetDTO entity) {
    return ResponseEntity.status(HttpStatus.OK).body(budgetService.updateBudget(id, entity));
   }
 
   @DeleteMapping("/delete/{bid}")
   public void deleteBudget (@PathVariable Long bid){
    budgetService.deleteBudget(bid);
    System.out.println("successfully deleted");

   }
   
   
   

}
