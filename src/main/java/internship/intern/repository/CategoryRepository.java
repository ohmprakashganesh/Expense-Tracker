package internship.intern.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import internship.intern.entity.Budget;
import internship.intern.entity.Category;
import internship.intern.entity.User;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

     @Query("SELECT c.budget FROM Category c WHERE c.user = :user")
    List<Budget> findBudgetsByUser(@Param("user") User user);
     
     Optional< Category>findByName(String name);

      List<Category> findByUser(User user);


     @Query("SELECT SUM(b.amount) FROM Category c JOIN c.budget b WHERE c.user = :user")
     Double findTotalBudgetByUser(@Param("user") User user);

     @Query("SELECT c.budget FROM Category c WHERE c.user = :user")
      List<Budget> findBudgetsByUserId(@Param("user") User user);

     
     
    
}
