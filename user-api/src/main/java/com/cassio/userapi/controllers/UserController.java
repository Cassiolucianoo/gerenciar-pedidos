package com.cassio.userapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cassio.userapi.model.User;

import com.cassio.userapi.service.UserService;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;


@RestController
@RequestMapping("/users")
public class UserController {

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
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
     @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
     public @ResponseBody User novoProdutos(@Valid User user) {
     userService.createUser(user); return user;
     
     }

	/*
	 * @PostMapping public ResponseEntity<User> createUser(@RequestBody User user) {
	 * User createdUser = userService.createUser(user); return
	 * ResponseEntity.created(URI.create("/users/" +
	 * createdUser.getId())).body(createdUser); }
	 * 
	 * @PutMapping("/{id}") public ResponseEntity<User> updateUser(@PathVariable
	 * Long id, @RequestBody User user) { User updatedUser =
	 * userService.updateUser(id, user); if (updatedUser != null) { return
	 * ResponseEntity.ok(updatedUser); } else { return
	 * ResponseEntity.notFound().build(); } }
	 */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
