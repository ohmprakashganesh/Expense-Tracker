package internship.intern.service;

import java.util.List;

import internship.intern.dto.BudgetDTO;
import internship.intern.entity.Budget;
import internship.intern.entity.User;

public interface BudgetService {

     Budget postBudget(BudgetDTO budget);
     Budget findBudget(Long id);
     // List<Budget> findBudgets(User user);
     void deleteBudget(long id);
//     Double getTotalBudgetByUser(User user);
     Budget  updateBudget(Long id, BudgetDTO budgetDTO);
}
