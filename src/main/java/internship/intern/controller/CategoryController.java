package internship.intern.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import internship.intern.dto.CategoryDTO;
import internship.intern.entity.Category;
import internship.intern.service.CategoryService;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> postAllCategory(@RequestBody CategoryDTO categoryDTO) {
        System.out.println("Received CategoryDTO: " + categoryDTO);
        Category category = categoryService.postCategory(categoryDTO);
        if (category != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body( category);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getCategories(){
      return ResponseEntity.ok(categoryService.allCategories());
    }
    
    @GetMapping("/single/{id}")
    public ResponseEntity<?> getCategory(@PathVariable Long id){
    return ResponseEntity.ok(categoryService.getCategory(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
      categoryService.deleteCategory(id);
      return ResponseEntity.ok("successfully deleted ");
    }
    @PostMapping("update/{id}")
    public ResponseEntity<?>  updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO){
    return ResponseEntity.ok(categoryService.updateCategory(id , categoryDTO));
    }
}
