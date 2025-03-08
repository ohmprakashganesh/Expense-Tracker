package internship.intern.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import internship.intern.dto.UserDTO;
import internship.intern.entity.User;
import internship.intern.service.UserServices;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;







@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserServices userServices;

    // @PostMapping
    // public ResponseEntity<?> postUserMethod(@RequestBody UserDTO userDTO) {
    // System.out.println( "this is obj"+userDTO + "data recived are ");
    //     User user= userServices.postUser(userDTO);
    //     if(user !=null){
    //         return ResponseEntity.status(HttpStatus.OK).body(user);
    //     }else{
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    //     }
    // }

    @GetMapping
    public ResponseEntity<?>  getAllUsers() {
        return ResponseEntity.ok( userServices.getAllUsers());
    }

    @GetMapping("/singleUser/{uid}")
    public ResponseEntity<?> getUser(@PathVariable Long uid) {
        return ResponseEntity.ok(userServices.getUser(uid));
    }

    @DeleteMapping("/deleteUser/{uid}")
    public  ResponseEntity<?> deleteUser(@PathVariable Long uid){
        userServices.deleteUser(uid);
        return ResponseEntity.ok("successfully deleted");
    }

    @PostMapping("updateUser/{id}")
    public ResponseEntity<?> updateUserMethod(@PathVariable Long id, @RequestBody UserDTO userDTO) {
      try{
        return ResponseEntity.ok(userServices.updateUser(id, userDTO));
      }catch(EntityNotFoundException ex){
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found with that name ");
      }catch(Exception e){
       return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("fault request"+e.getLocalizedMessage());
      }
    }


}
