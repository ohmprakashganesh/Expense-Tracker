package internship.intern.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import internship.intern.entity.Category;
import internship.intern.entity.Expanse;


@Repository
public interface ExpanseRepository extends JpaRepository<Expanse, Long> {

     
    List<Expanse>  findByCategoryName(String name);

    @Query("SELECT SUM(e.amount) FROM Expanse e")
    Double getTotalExpenses();

    @Query("SELECT c FROM Category c WHERE c.name = :categoryName")
    Category getCategoryByName(@Param("categoryName") String categoryName);



    // @Query("SELECT new internship.intern.dto.CategoryExpenseDTO(c.name, SUM(e.amount)) " +
    //    "FROM Expanse e JOIN e.category c WHERE e.user.uid = :uid GROUP BY c.name")
    // List<CategoryExpenseDTO> getExpansesByCategory(@Param("uid") Long userId); // Use @Param("uid")

//    @Query("SELECT new internship.intern.dto.MonthlyExpenseDTO(MONTH(e.date), SUM(e.amount)) " +
//        "FROM Expanse e WHERE YEAR(e.date) = :year GROUP BY MONTH(e.date)")
// List<MonthlyExpenseDTO> getMonthlyExpenseReport(Integer year);

 
}




