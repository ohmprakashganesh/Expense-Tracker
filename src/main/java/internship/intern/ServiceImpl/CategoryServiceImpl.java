package internship.intern.ServiceImpl;

import java.lang.StackWalker.Option;
import java.util.Optional;

import org.springframework.stereotype.Service;

import internship.intern.dto.CategoryDTO;
import internship.intern.entity.Category;
import internship.intern.entity.User;
import internship.intern.repository.CategoryRepository;
import internship.intern.repository.UserRepository;
import internship.intern.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

     private final UserRepository userRepository;
     private final CategoryRepository categoryRepository;

    public Category postCategory(CategoryDTO categoryDTO){
        return saveOrUpdateCategory(new Category(), categoryDTO);
    }
    public Category saveOrUpdateCategory(Category category, CategoryDTO categoryDTO){
           
        category.setName(categoryDTO.getName());
        category.setUser(getUser());
      return  categoryRepository.save(category);

    }
 

    public User getUser() {
		Optional<User> optional = userRepository.findById(11L);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			 throw new EntityNotFoundException("User not found for the given ID");
		}
	}
    
}
