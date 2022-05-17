package com.example.cinema_rgr.service;

import com.example.cinema_rgr.entity.User;
import com.example.cinema_rgr.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public User saveUser(User user) {
        return usersRepository.save(user);
    }

    public void deleteUser(Integer userId){
        usersRepository.deleteById(userId);
    }

    public Optional<User> findUserByEmail(String email) {
        return usersRepository.findUserByEmail(email);
    }

    public List<User> findUsersByNameAndSurname(String name, String surname) {
        return usersRepository.findByNameAndSurname(name, surname);
    }

    public User findUserByPhone(String phone) {
        return usersRepository.findByPhone(phone);
    }

    public List<User> findAll() {
        return usersRepository.findAll();
    }

    public void updateUser(User updatedUser) {
        User user = findUserByEmail(updatedUser.getEmail()).orElse(null);
        assert user != null;
        user.setName(updatedUser.getName());
        user.setGender(updatedUser.getGender());
        user.setSurname(updatedUser.getSurname());
        user.setBirthday(updatedUser.getBirthday());
        usersRepository.save(user);
    }
}
