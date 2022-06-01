package com.example.cinema_rgr.view;

import com.example.cinema_rgr.entity.User;
import com.example.cinema_rgr.service.UsersService;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myProfile")
@NoArgsConstructor
@Log
public class ProfileController {

    @Autowired
    private UsersService usersService;

    @GetMapping()
    public String profilePage(Model model) {
        User user = usersService.findUserByEmail(usersService.getCurrentUsername()).orElse(null);
        model.addAttribute("user", user);
        return "profile";
    }



}
