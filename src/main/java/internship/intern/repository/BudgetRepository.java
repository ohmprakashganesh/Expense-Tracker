

package internship.intern.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import internship.intern.entity.Budget;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
	
	
	

}