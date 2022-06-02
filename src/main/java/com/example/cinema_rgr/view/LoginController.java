package com.example.cinema_rgr.view;

import com.example.cinema_rgr.service.UsersService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
@AllArgsConstructor
@Log
public class LoginController {

    @Autowired
    private UsersService usersService;

    @GetMapping()
    public String reservation(Model model) {
        return "login";
    }

}
