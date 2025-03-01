

package internship.intern.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import internship.intern.entity.Budget;
import internship.intern.entity.User;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
	
	 @Query("SELECT SUM(b.amount) FROM Budget b")
    Double getTotalBudget();

    @Query("SELECT SUM(b.amount) FROM Budget b WHERE b.user=:user")
    Double findAmountByUser(User user);
	

}