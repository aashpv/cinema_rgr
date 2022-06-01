package com.example.cinema_rgr.view;

import com.example.cinema_rgr.entity.Movie;
import com.example.cinema_rgr.entity.Screening;
import com.example.cinema_rgr.entity.User;
import com.example.cinema_rgr.service.MoviesService;
import com.example.cinema_rgr.service.ScreeningService;
import com.example.cinema_rgr.service.UsersService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/movies")
@AllArgsConstructor
@Log
public class MovieViewController {

    @Autowired
    private MoviesService moviesService;

    @Autowired
    private ScreeningService screeningService;

    @Autowired
    private UsersService usersService;

    @GetMapping("/{id}")
    public String movieCard(@PathVariable Integer id, Model model) {
        Optional<Movie> movie = moviesService.findMovieById(id);
        List<Screening> listScreeningToday = screeningService.findScreeningsByMovieIdToday(id);
        List<Screening> listScreeningTomorrow = screeningService.findScreeningsByMovieIdTomorrow(id);
        List<Screening> listScreeningAfterTomorrow = screeningService.findScreeningsByMovieIdAfterTomorrow(id);
        if(movie.isPresent()) {
            model.addAttribute("user", getCurrentUsername());
            model.addAttribute("movieCard", movie);
            model.addAttribute("title", movie.get().getName());
            model.addAttribute("listScreeningToday", listScreeningToday);
            model.addAttribute("listScreeningTomorrow", listScreeningTomorrow);
            model.addAttribute("listScreeningAfterTomorrow", listScreeningAfterTomorrow);
            return "movieCard";
        }
        return "error";
    }

    private User getCurrentUsername() {
        return usersService
                .findUserByEmail(usersService.getCurrentUsername())
                .orElse(null);
    }

    @GetMapping
    public String movies(Model model) {
        model.addAttribute("title", "Фильмы");
        model.addAttribute("listMovies", moviesService.findMoviesAll());
        model.addAttribute("screeningsList", screeningService.findScreeningsAll());
        return "movies";
    }
}
