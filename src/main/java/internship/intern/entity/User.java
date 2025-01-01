package internship.intern.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class User {

     @GeneratedValue(strategy =  GenerationType.IDENTITY)
     @Id
    private Long uid;
    private  String name;
    private String email;
    private String  image;
    private int number;
    private String address;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Expanse> expanses;
    
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Budget> budgets;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Category> categories;

    
}
