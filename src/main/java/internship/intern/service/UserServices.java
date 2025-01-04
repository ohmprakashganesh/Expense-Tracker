package internship.intern.service;

import java.util.List;

import internship.intern.dto.UserDTO;
import internship.intern.entity.User;

public interface UserServices {

    User postUser(UserDTO userDTO );

    User getUser(Long id);

    User updateUser(Long id, UserDTO userDTO);

    List<User> getAllUsers();

    void deleteUser(Long id);



    
}
