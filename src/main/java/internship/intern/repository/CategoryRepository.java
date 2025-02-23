package internship.intern.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import internship.intern.entity.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
     Category findByName(String name);
    
}
