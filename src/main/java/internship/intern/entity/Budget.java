package internship.intern.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
public class Budget {

    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Id
    private Long bid;
    @NotEmpty
    private  Double amount;
    @NotEmpty
     @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @NotEmpty
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
     private User user;

     @OneToMany(mappedBy = "budget",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
     @JsonIgnore
     private List< Category> categories;

     @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
     @JsonIgnore
     private List<Expanse> expanses;




}
