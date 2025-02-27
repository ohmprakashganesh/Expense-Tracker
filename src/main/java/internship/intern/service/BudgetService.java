package internship.intern.service;

import java.util.List;

import internship.intern.dto.BudgetDTO;
import internship.intern.entity.Budget;

public interface BudgetService {

     // Budget postBudget(BudgetDTO budget);
     Budget findBudget(Long id);
     List<Budget> findBudgets();
     void deleteBudget(Long id);
     Budget  updateBudget(Long id, BudgetDTO budgetDTO);
}
