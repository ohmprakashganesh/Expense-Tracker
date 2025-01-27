package internship.intern.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

    // @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    // @JsonIgnore
    // private List<Expanse> expanses=  new ArrayList<>();
    
    // @JsonIgnore
    // @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    // private List<Budget> budgets=new ArrayList<>();

    // @JsonIgnore
    // @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    // private List<Category> categories=new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Expanse> expanses=  new ArrayList<>();
    

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Budget> budgets=new ArrayList<>();


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Category> categories=new ArrayList<>();
    
}
