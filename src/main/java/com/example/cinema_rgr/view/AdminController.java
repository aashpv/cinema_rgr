package com.example.cinema_rgr.view;

import com.example.cinema_rgr.entity.Movie;
import com.example.cinema_rgr.entity.Screening;
import com.example.cinema_rgr.service.HallService;
import com.example.cinema_rgr.service.MoviesService;
import com.example.cinema_rgr.service.ScreeningService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
@Log
public class AdminController {

    @Autowired
    private MoviesService moviesService;

    @Autowired
    private ScreeningService screeningService;

    @Autowired
    private HallService hallService;

    @GetMapping("/addMovie")
    public String addMoviePage(Model model) {
        model.addAttribute("movie", new Movie());
        return "addMovie";
    }

    @GetMapping("/viewSchedule")
    public String schedulePage(Model model) {
        List<Screening> screeningList = screeningService.findScreeningsAll();
        model.addAttribute("screeningList", screeningList);
        return "movies";
    }

    @GetMapping("/addScreening/movie")
    public String addScreeningPage(Model model) {
        model.addAttribute("movies", moviesService.findMoviesAll());
        model.addAttribute("halls", hallService.findHallsAll());
        model.addAttribute("screening", new Screening());
        return "addScreening";
    }

    @GetMapping("/deleteMovie")
    public String deleteMoviePage(Model model) {
        model.addAttribute("movieObject", new Movie());
        model.addAttribute("movies", moviesService.findMoviesAll());
        return "deleteMovie";
    }
}
