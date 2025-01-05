package internship.intern.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internship.intern.dto.ExpanseDTO;
import internship.intern.entity.Expanse;
import internship.intern.service.ExpanseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/expense")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ExpanseController {
	
	private final ExpanseService expanseService;
	

	@DeleteMapping("/deleteExpense/{id}")
	public ResponseEntity<?> deleteOperation(@PathVariable Long id) {
		expanseService.deleteById(id);
		return ResponseEntity.ok("Successfully deleted");
	}
	




	
	@PostMapping
	public ResponseEntity<?> postExpense (@RequestBody ExpanseDTO expanseDTO){
		Expanse createdExpanse= expanseService.postExpanse(expanseDTO);
		if(createdExpanse != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(createdExpanse);
			
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		
	}


	@GetMapping("/all")
	public ResponseEntity<?> getAllExpenses(){
		return ResponseEntity.ok(expanseService.getAllExpenses());

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getSingleExpanse(@PathVariable Long id){
		try{
			return ResponseEntity.ok(expanseService.getExpenseById(id));

		}catch(EntityNotFoundException ex){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("somtehing went wrong in code ");
		}
 


	}

	

	
	
	@PostMapping("update/{id}")
public ResponseEntity<?> updateExpense(@PathVariable Long id,@RequestBody ExpanseDTO expanseDTO){
	try{
		return ResponseEntity.ok(expanseService.updateExpanse(id,expanseDTO));
	}catch(EntityNotFoundException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("that id doesnot exist"+id);
	}catch( Exception e){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong ");
	}


}
	


	


}
