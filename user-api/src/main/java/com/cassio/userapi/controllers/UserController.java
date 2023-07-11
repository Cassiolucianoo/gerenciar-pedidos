package com.cassio.userapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cassio.userapi.model.User;

import com.cassio.userapi.service.UserService;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }
    
//metodo 
  
//    @PostMapping
//    public @ResponseBody ResponseEntity<User> createUser(@Valid  User user) {
//        User createdUser = userService.createUser(user);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
//    }
   
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
	public @ResponseBody User novoProdutos(@Valid User user) {
    	userService.createUser(user);
		return user;

	}
    
//    @PutMapping("/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable Long id,  @Valid User updatedUser) {
//        User updated = userService.updateUser(id, updatedUser);
//        if (updated != null) {
//            return ResponseEntity.ok(updated);
//        }
//        return ResponseEntity.notFound().build();
//    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

