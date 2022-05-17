package com.example.cinema_rgr.repository;

import com.example.cinema_rgr.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByEmail(String email);
    User findByPhone(String phone);
    List<User> findByNameAndSurname(String name, String surname);
}
