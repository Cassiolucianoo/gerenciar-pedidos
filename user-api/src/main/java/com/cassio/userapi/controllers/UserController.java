package com.cassio.userapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cassio.userapi.model.User;

import com.cassio.userapi.service.UserService;

import com.cassio.userapi.dto.CadastroResponse;
import com.cassio.userapi.dto.ErrorResponse;

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
    
    
//     @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
//     public @ResponseBody User novoProdutos(@Valid User user) {
//     userService.createUser(user); return user;
//     
//     }
     
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<?> novoProdutos(@Valid User user) {
        if (user.getCpfErrorMessage() != null) {
        	String mensagemErro = "CPF inválido! O CPF deve conter 11 dígitos válidos.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(mensagemErro));
            
        }

        try {
        	   userService.createUser(user);
               String mensagemSucesso = "Usuário criado com sucesso!";
               CadastroResponse response = new CadastroResponse(mensagemSucesso, user);
               return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Tratar outras exceções, como erros de banco de dados ou internos
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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
