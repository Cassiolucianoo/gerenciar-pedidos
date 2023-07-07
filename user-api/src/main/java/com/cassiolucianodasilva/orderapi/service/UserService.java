package com.cassiolucianodasilva.orderapi.service;

import org.springframework.stereotype.Service;

import com.cassiolucianodasilva.orderapi.model.User;
import com.cassiolucianodasilva.orderapi.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        user.setCreated_at(LocalDateTime.now());
        user.setUpdated_at(LocalDateTime.now());
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(id);
        
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            
            existingUser.setName(updatedUser.getName());
            existingUser.setCpf(updatedUser.getCpf());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPhone_number(updatedUser.getPhone_number());
            existingUser.setUpdated_at(LocalDateTime.now());
            
            return userRepository.save(existingUser);
        }
        
        return null; // Retorna null se o usuário não for encontrado
    }


    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
