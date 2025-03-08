package internship.intern.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import internship.intern.dto.BudgetDTO;
import internship.intern.entity.Budget;
import internship.intern.service.BudgetService;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/budget")
public class BudgetController {

private final  BudgetService budgetService;

// @PostMapping
//    public  ResponseEntity<?> postTheBudget(@RequestBody BudgetDTO budgetDTO){
//     System.out.println(budgetDTO.toString());
//     Budget budget= budgetService.postBudget(budgetDTO);
//     if(budget != null){
//         return ResponseEntity.status(HttpStatus.ACCEPTED).body(budget);
//     }else{
//         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("unable to post the form data ");
//     }
//    }


   @GetMapping("/single/{bid}")
   public ResponseEntity<?> getBudget(@PathVariable Long bid){

    Budget budget= budgetService.findBudget(bid);

    return ResponseEntity.status(HttpStatus.ACCEPTED).body(budget);
   }

   @GetMapping("/all")
   public ResponseEntity<?> getAllBudgets() {
      try{
         return ResponseEntity.status(HttpStatus.ACCEPTED).body(budgetService.findBudgets());

      }catch(Exception ex){
          return ResponseEntity.ok("no data found");
      }
   }
   
   @PostMapping("/update/{bid}")
   public ResponseEntity<?> postMethodName(@PathVariable Long bid,  @RequestBody BudgetDTO entity) {
      try{
         return ResponseEntity.status(HttpStatus.OK).body(budgetService.updateBudget(bid, entity));

      }catch(Exception ex){
         return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.BAD_REQUEST);

      }
   }

   @DeleteMapping("/delete/{bid}")
   public  ResponseEntity<?> deleteBudgetHere(@PathVariable Long bid){ 

      try{
         budgetService.deleteBudget(bid);
         return ResponseEntity.ok("successfully deleted");
      }catch(Exception ex){
         return ResponseEntity.ok("no existing user woth "+bid);
      }
  
   }


}
