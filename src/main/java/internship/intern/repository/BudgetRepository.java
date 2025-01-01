

package internship.intern.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import internship.intern.entity.Expanse;

@Repository
public interface BudgetRepository extends JpaRepository<Expanse, Long> {
	
	
	

}