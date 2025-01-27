package com.Cybertrons.springboot_restful_webservices.controller;

import com.Cybertrons.springboot_restful_webservices.dto.UserDto;
import com.Cybertrons.springboot_restful_webservices.entity.User;
import com.Cybertrons.springboot_restful_webservices.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // its a combination of @ResponseBody & @Controller
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    // Build create User REST API
    // http://localhost:8080/api/users/create
    @PostMapping("create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
        UserDto userSaved = userService.createUser(user);
        return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
    }

    // Build get user by id REST API
    // http://localhost:8080/api/users/2
    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long userId){
        User getUser=userService.getUserById(userId);
       // return new ResponseEntity<>(getUser,HttpStatus.OK);
        return ResponseEntity.ok(getUser);
    }

    // Build to get All users by REST API
    // http://localhost:8080/api/users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users=userService.getAllUsers();
        // return new ResponseEntity<>(users,HttpStatus.OK);
        return ResponseEntity.ok(users);
    }

    // Build to update the user details for one user by REST API
    // http://localhost:8080/api/users/1
    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId,
                                           @RequestBody User user){
        user.setId(userId);
        User userUpdated=userService.updateUser(user);
        return new ResponseEntity<>(userUpdated,HttpStatus.OK);
    }

    /*// Build to update the user details for All users by REST API
    // http://localhost:8080/api/users
    @PutMapping("updateAllUsers")
    public ResponseEntity<List<User>> updateUsers(@RequestBody List<User> users){
       List<User> updatedUsers= userService.updateUsers(users);
       return new ResponseEntity<>(updatedUsers,HttpStatus.OK);
    }*/

    // Bild to deleteUserById REST API
    //http://localhost:8080/api/users/{id}
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long userId){
        userService.deleteUserById(userId);
        return ResponseEntity.ok("User Successfully Deleated from DB");
    }

}

