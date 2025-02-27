package internship.intern.ReportsResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internship.intern.DTO2.ExpanseSummeryDTO;
import internship.intern.dto.ExpanseDTO;
import internship.intern.service.ExpanseService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/reports")
public class ReportsController {

    final private ExpanseService expanseService;
    

    @GetMapping("/total")
    public ResponseEntity<?> getTotal(){
        return new ResponseEntity<>(expanseService.getTotalExpenses(),HttpStatus.OK);
    }

@GetMapping("/category/{name}")
    public ResponseEntity<?> byNameCategory(@PathVariable String name){
        return new ResponseEntity<>(expanseService.getCategoryByName(name),HttpStatus.OK);
    }
    // expenses/summery
    @GetMapping("/summery")
    public ResponseEntity<?> ExpenseSummery() {
    return new ResponseEntity<>(expanseService.getSummery(), HttpStatus.OK);

        
    }
    


    //expenses reports ?type=Yearly
    //reports category wise 
    //budget vs actual expanse

    

    

}
