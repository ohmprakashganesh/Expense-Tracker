package internship.intern.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import internship.intern.entity.RefreshToken;

public interface RefreshRepository extends JpaRepository<RefreshToken, Long> {

   Optional< RefreshToken> findByRefreshToken(String token);

    
}
