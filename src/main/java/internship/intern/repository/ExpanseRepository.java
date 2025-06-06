package internship.intern.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import internship.intern.entity.Category;
import internship.intern.entity.Expanse;
import internship.intern.entity.User;


@Repository
public interface ExpanseRepository extends JpaRepository<Expanse, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Expanse e WHERE e.category = :category")
    void deleteByCategory(@Param("category") Category category);
     
    List<Expanse>  findByCategoryName(String name);

    List<Expanse> findByUser(User user);

    @Query("SELECT SUM(e.amount) FROM Expanse e")
    Double getTotalExpenses();

    @Query("SELECT SUM(e.amount) FROM Expanse e WHERE e.user = :user")
    Double findAmountByUser(User user);


    // @Query("SELECT SUM(b.amount) FROM Expanse e WHERE e.category.user=:user")
    //  Double findTotalExpanseByUser(@Param("user") User user);



    @Query("SELECT c FROM Category c WHERE c.name = :categoryName")
    Category getCategoryByName(@Param("categoryName") String categoryName);



    // @Query("SELECT new internship.intern.dto.CategoryExpenseDTO(c.name, SUM(e.amount)) " +
    //    "FROM Expanse e JOIN e.category c WHERE e.user.uid = :uid GROUP BY c.name")
    // List<CategoryExpenseDTO> getExpansesByCategory(@Param("uid") Long userId); // Use @Param("uid")

//    @Query("SELECT new internship.intern.dto.MonthlyExpenseDTO(MONTH(e.date), SUM(e.amount)) " +
//        "FROM Expanse e WHERE YEAR(e.date) = :year GROUP BY MONTH(e.date)")
// List<MonthlyExpenseDTO> getMonthlyExpenseReport(Integer year);

 
}




