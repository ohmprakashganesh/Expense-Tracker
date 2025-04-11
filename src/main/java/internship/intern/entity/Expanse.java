package internship.intern.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
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

	@Column(unique = true)
	private String title;

	private String description;

	private LocalDate date;

	private Double amount;

    @ManyToOne
	@JsonManagedReference // Add this annotation
    @JoinColumn(name = "user_id",nullable = false)
     private User user;

   @ManyToOne
//    @JsonIgnore
   @JoinColumn(name ="category_id",nullable = false)
   private Category category;
}
