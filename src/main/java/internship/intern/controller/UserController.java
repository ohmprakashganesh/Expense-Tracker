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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




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
    
    // @DeleteMapping(""){

    // }

    @PutMapping("path/{id}")
    public String putMethodName(@PathVariable String id, @RequestBody String entity) {
        //TODO: process PUT request
        
        return entity;
    }
    

}
