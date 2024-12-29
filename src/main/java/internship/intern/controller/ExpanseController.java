package internship.intern.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internship.intern.dto.ExpanseDTO;
import internship.intern.entity.Expanse;
import internship.intern.service.ExpanseService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/expanse")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ExpanseController {
	
	private final ExpanseService expanseService;
	
	@PostMapping
	public ResponseEntity<?> postExpense (@RequestBody ExpanseDTO expanseDTO){
		Expanse createdExpanse= expanseService.postExpanse(expanseDTO);
		if(createdExpanse != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(createdExpanse);
			
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		
	}

}
