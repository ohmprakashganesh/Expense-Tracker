package internship.intern.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internship.intern.dto.BudgetDTO;
import internship.intern.dto.CategoryDTO;
import internship.intern.entity.Category;
import internship.intern.repository.CategoryRepository;
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
    private final CategoryRepository categoryRepository;


    @GetMapping("/byName/{name}")
    public ResponseEntity< Category> getByName(@PathVariable String name) {
     try{
       return ResponseEntity.ok( categoryService.getCategoryByName(name));
     }catch(Exception ex){
       return ResponseEntity.notFound().build();
     }

}


    @PostMapping
    public ResponseEntity<?> postAllCategory(@RequestBody CategoryDTO categoryDTO ){
        System.out.println("Received CategoryDTO: " + categoryDTO);


        Category category = categoryService.postCategory(categoryDTO);
        if (category != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body( category);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // @GetMapping("/all")
    // public ResponseEntity<?> getCategories(){
    //   try{
    //     return ResponseEntity.ok(categoryService.allCategories());
    //   }catch(Exception ex){
    //     return ResponseEntity.ok("not data exist");
    //   }
    // }

    @GetMapping("/all")
    public ResponseEntity<?> categoryByUser(){
      try{
        return ResponseEntity.ok(categoryService.allCategories());
      }catch(Exception ex){
        return ResponseEntity.ok("not data exist");
      }
    }


    @GetMapping("/single/{id}")
    public ResponseEntity<?> getCategory(@PathVariable Long id){
      try{
        return ResponseEntity.ok(categoryService.getCategory(id));
      }catch(Exception ex){
        return ResponseEntity.ok("not data exist"+id);
      }

    }
    @DeleteMapping("/delete/{cid}")
    public ResponseEntity<String> deleteTheCategory(@PathVariable long cid){

      try{
          categoryService.deleteCategory(cid);
          
        return ResponseEntity.ok("successfully deleted ");

            }catch(Exception ex){
        return ResponseEntity.ok("not data exist"+cid);
      }
     
    }


    @PostMapping("update/{id}")
    public ResponseEntity<?>  updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO){
      try{
        return ResponseEntity.ok(categoryService.updateCategory(id , categoryDTO));
            }catch(Exception ex){
        return ResponseEntity.ok("not data exist"+id);
      }

    }
  
}
