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
import lombok.ToString;

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
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    @JsonManagedReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @ToString.Exclude
    @JsonManagedReference
    private Category category;

//     @ManyToOne
// 	@ToString.Exclude
// 	@JsonManagedReference // Add this annotation
//     @JoinColumn(name = "user_id",nullable = false)
//      private User user;

//    @ManyToOne
//    @ToString.Exclude
//    @JsonManagedReference
//    @JoinColumn(name ="category_id",nullable = false)
//    private Category category;
}
