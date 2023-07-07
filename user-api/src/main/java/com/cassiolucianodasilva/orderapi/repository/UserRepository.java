package com.cassiolucianodasilva.orderapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cassiolucianodasilva.orderapi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Métodos personalizados, se necessário
}
