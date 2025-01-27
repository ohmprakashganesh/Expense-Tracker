package internship.intern.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Expanse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String description;

	private LocalDate date;

	private Double amount;

	@ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
     private User user;

   @ManyToOne
   @JoinColumn(name = "budget_id", nullable = false)
   private  Budget budget;

   @ManyToOne
   @JoinColumn(name ="category_id", nullable = false)
   private Category category;
}
