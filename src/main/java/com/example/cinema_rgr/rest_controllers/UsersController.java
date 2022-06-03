package com.example.cinema_rgr.rest_controllers;

import com.example.cinema_rgr.entity.User;
import com.example.cinema_rgr.service.UsersService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Log
public class UsersController {

    @Autowired
    private final UsersService usersService;

    @PostMapping("/save")
    public User saveUsers(@RequestBody User user) {
        log.info("Handling save users: " + user);
        return usersService.saveUser(user);
    }

    @PostMapping("/update")
    public ModelAndView updateUser(User user) {
        usersService.updateUser(user);
        log.info("Handling update users: " + user);
        return new ModelAndView("redirect:/myProfile");
    }

    @GetMapping("/findAll")
    public List<User> findAllUsers() {
        log.info("Handling find all users request");
        return usersService.findAll();
    }

    @GetMapping("/findByEmail")
    public Optional<User> findByLogin(@RequestParam String email) {
        log.info("Handling find by email request: " + email);
        return usersService.findUserByEmail(email);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable Integer id) {
        log.info("Handling delete user request: " + id);
        usersService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findByPhone")
    public User findUserByPhone(@RequestParam String phone) {
        log.info("Handler find by phone user request: " + phone);
        return usersService.findUserByPhone(phone);
    }

    @GetMapping("/findByNameAndSurname")
    public List<User> findUserByNameAndSurname(@RequestParam String name, @RequestParam String surname) {
        log.info("Handler find by name and surname user request: " + name + " " + surname);
        return usersService.findUsersByNameAndSurname(name, surname);
    }
}
