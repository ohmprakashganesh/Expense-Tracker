package internship.intern.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import internship.intern.entity.Budget;
import internship.intern.entity.Category;
import internship.intern.entity.User;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
     Optional< Category>findByName(String name);
     List<Category> findByUser(User user);
     Budget  findByBudget(Budget budget);
    
}
