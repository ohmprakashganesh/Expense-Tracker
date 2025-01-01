package internship.intern.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import internship.intern.entity.User;


@Repository
public interface UserRepository  extends JpaRepository<User, Long>{
    
}
