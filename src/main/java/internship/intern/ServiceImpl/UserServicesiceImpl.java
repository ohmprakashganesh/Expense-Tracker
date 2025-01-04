package internship.intern.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import internship.intern.dto.BudgetDTO;
import internship.intern.dto.CategoryDTO;
import internship.intern.dto.ExpanseDTO;
import internship.intern.dto.UserDTO;
import internship.intern.entity.Budget;
import internship.intern.entity.Category;
import internship.intern.entity.Expanse;
import internship.intern.entity.User;
import internship.intern.repository.UserRepository;
import internship.intern.service.UserServices;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServicesiceImpl implements UserServices{

   private final UserRepository userRepository;



    public User postUser(UserDTO userDTO ){
        return  saveUpdateUser(new User(), userDTO);

    }

    // private User saveUpdateUser(User user, UserDTO userDTO) {
    //     if (userDTO.getName() != null) {
    //         user.setName(userDTO.getName());
    //     }
    //     if (userDTO.getEmail() != null) {
    //         user.setEmail(userDTO.getEmail());
    //     }
    //     if (userDTO.getImage() != null) {
    //         user.setImage(userDTO.getImage());
    //     }
    //     if (userDTO.getNumber() != 0) {
    //         user.setNumber(userDTO.getNumber());
    //     }
    //     if (userDTO.getAddress() != null) {
    //         user.setAddress(userDTO.getAddress());
    //     }
    //     return userRepository.save(user);
    // }

    // public List<User> getAllUsers(){
    //     return userRepository.findAll().stream().collect(Collectors.toList());


    // }

   

    public User saveUpdateUser(User user, UserDTO userDTO){
        user.setName(userDTO.getName());
        user.setAddress(userDTO.getAddress());
        user.setEmail(userDTO.getEmail());
        user.setImage(userDTO.getImage());
        // user.setNumber(userDTO.getNumber());
        user.setNumber(938447547);

        user.setExpanses(convertToExpanse(userDTO.getExpansesDtos(),user));
        user.setCategories(convertToCategories(userDTO.getCategoriesDtos(),user));
        user.setBudgets(convertToBudgets(userDTO.getBudgetsDtos(),user));
        return userRepository.save(user);
    }

     private List<Expanse> convertToExpanse(List<ExpanseDTO> expanseDTOs,User user) {
        if(expanseDTOs==null || expanseDTOs.isEmpty()){
            return new  ArrayList<>();
        }
        
        return  expanseDTOs.stream().map(dto->{
            Expanse expanse = new Expanse();
            expanse.setAmount(dto.getAmount());
            expanse.setDescription(dto.getDescription());
            expanse.setDate(dto.getDate());
            expanse.setUser(user);
            return expanse;

        }).collect(Collectors.toList());
        // Implement conversion logic
    }

    private List<Budget> convertToBudgets(List<BudgetDTO> budgetDTOs, User user) {
        if(budgetDTOs==null || budgetDTOs.isEmpty()){
            return new ArrayList<>();

        }
        return budgetDTOs.stream().map(dto->{
            Budget budget=  new Budget();
            budget.setAmount(dto.getAmount());
            budget.setEndDate(dto.getEndDate());
            budget.setEndDate(dto.getEndDate());
            budget.setUser(user);
            return budget;
        }).collect(Collectors.toList());
        // Implement conversion logic
    }


    private List<Category> convertToCategories(List<CategoryDTO> categoryDTOs,User user) {
        if(categoryDTOs==null || categoryDTOs.isEmpty()){
            return new ArrayList<>();
        }
        return categoryDTOs.stream().map(dto->{

            Category category= new Category();

            category.setName(dto.getName());
            category.setUser(user);
          return  category;

        }).collect(Collectors.toList());
      
    }



   public User getUser(Long id){
     Optional<User> optional = userRepository.findById(id);
     if(optional.isPresent()){
        return  optional.get();
     }else{
        throw new EntityNotFoundException("please "+id);
     }

   }

   public  List<User> getAllUsers(){
    return userRepository.findAll().stream().collect(Collectors.toList());
    

   }

    
}