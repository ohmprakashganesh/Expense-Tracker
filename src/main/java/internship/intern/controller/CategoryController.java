package internship.intern.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internship.intern.dto.CategoryDTO;
import internship.intern.entity.Category;
import internship.intern.repository.CategoryRepository;
import internship.intern.service.CategoryService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    public ResponseEntity<?> postCategory(@RequestBody CategoryDTO categoryDTO){
     Category category= categoryService.postCategory(categoryDTO);
     if(category != null){
        return ResponseEntity.ok(category);
     }else{
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("unable to post ");

     }


    }

    
}
