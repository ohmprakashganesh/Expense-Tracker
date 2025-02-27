package internship.intern.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internship.intern.DTO2.CompareDTO;
import internship.intern.DTO2.ExpanseSummeryDTO;
import internship.intern.dto.ExpanseDTO;
import internship.intern.entity.Expanse;
import internship.intern.service.CategoryService;
import internship.intern.service.ExpanseService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ReportController {

    private final ExpanseService expanseService;
    private final CategoryService categoryService;



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

// @GetMapping("vs")
// public CompareDTO compateBudgetAndExpense() {
     
//     return  ResponseEntity.ok(expanseService.compate());
// }

@GetMapping("/expSummery")
public ResponseEntity< ExpanseSummeryDTO> summeryMethod() {
    ExpanseSummeryDTO expanseSummeryDTO=new ExpanseSummeryDTO();

    List<String> obj=  new  ArrayList<>();

    List<Expanse> expanses=expanseService.expByCategory("hello");
    for(Expanse expanse: expanses){
        obj.add(expanse.getTitle());
    }

    
     
    

    return  expanseService.
}




}
