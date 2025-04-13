package internship.intern.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Lettuce.Cluster.Refresh;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode.Exclude;

@Data
@Entity
@NoArgsConstructor
public class User implements UserDetails {

     @GeneratedValue(strategy =  GenerationType.IDENTITY)
     @Id
    private Long uid;
    private  String name;
    private String email;
    private String password;

    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private ForgetPassword forgotPassword;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    @ToString.Exclude
    private List<Category> categories = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private RefreshToken refreshToken;

    
    // @OneToOne(mappedBy = "user")
    // private ForgetPassword forgotPassword;


    // @Enumerated(EnumType.STRING)
    // private UserRole role;

    // @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    // @JsonIgnore
    // @ToString.Exclude
    // private List<Expanse> expanses=  new ArrayList<>();
    

    // @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    // @JsonIgnore
	// @ToString.Exclude
    // private List<Budget> budgets=new ArrayList<>();


    // @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    // @JsonIgnore
    // @ToString.Exclude
    // private List<Category> categories=new ArrayList<>();

    // @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    // @JsonIgnore
    // private RefreshToken refreshToken;


    
    //from UserDetails interface 
     @Override
    public	Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
	public String getPassword(){
        return password;
    }

    @Override
	public String getUsername(){
        return email;

    }
    
    @Override
	public boolean isAccountNonExpired() {
		return true;
	}

    @Override
	public boolean isAccountNonLocked() {
		return true;
	}


    @Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

    @Override
	public boolean isEnabled() {
		return true;
	}

}
