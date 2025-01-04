package internship.intern.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


import internship.intern.dto.UserDTO;
import internship.intern.entity.User;
import internship.intern.service.UserServices;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;






@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserServices userServices;

    @PostMapping
    public ResponseEntity<?> postUserMethod(@RequestBody UserDTO userDTO) {
    System.out.println( "this is obj"+userDTO + "data recived are ");
        User user= userServices.postUser(userDTO);
        if(user !=null){
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    public ResponseEntity<?>  getAllUsers() {
        return ResponseEntity.ok( userServices.getAllUsers());
    }

    @GetMapping("/singleUser/{uid}")
    public User getMethodName(@PathVariable Long uid) {
        return userServices.getUser(uid);
    }
    


}
