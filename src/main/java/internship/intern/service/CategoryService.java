package internship.intern.service;

import java.util.List;

import internship.intern.dto.BudgetDTO;
import internship.intern.dto.CategoryDTO;
import internship.intern.entity.Category;

public interface CategoryService {

    Category postCategory(CategoryDTO categoryDTO,BudgetDTO budgetDTO);
    Category getCategory(Long id);
    List<Category> allCategories();
    void deleteCategory(Long id);
    Category getCategoryByName(String name);
    

    Category updateCategory(Long id, CategoryDTO categoryDTO,BudgetDTO budgetDTO);

    
} 