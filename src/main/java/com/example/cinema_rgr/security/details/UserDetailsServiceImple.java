package com.example.cinema_rgr.security.details;

import com.example.cinema_rgr.entity.User;
import com.example.cinema_rgr.service.UsersService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.rmi.AlreadyBoundException;
import java.util.Optional;

@Service
public class UserDetailsServiceImple implements UserDetailsService {

    @Autowired
    private UsersService usersService;

    @SneakyThrows
    @Override
    public UserDetailsImpl loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = usersService.findUserByEmail(email);
        if (user.isPresent()) {
            return new UserDetailsImpl(user.get());
        } else throw new AlreadyBoundException("User Not Found");
    }
}