package com.example.cinema_rgr.service;

import com.example.cinema_rgr.entity.User;
import com.example.cinema_rgr.entity.statuses.Role;
import com.example.cinema_rgr.forms.UserForm;
import com.example.cinema_rgr.repository.UsersRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log
public class RegistrationService {

    private final UsersService usersService;

    private  final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;


    public void reg(UserForm userForm)  {
        String hashPassword = passwordEncoder.encode(userForm.getPassword());
            User user = User.builder()
                    .password(hashPassword)
                    .email(userForm.getEmail())
                    .phone(userForm.getPhone())
                    .role(Role.USER)
                    .build();

        usersRepository.save(user);
    }
}
