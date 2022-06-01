package com.example.cinema_rgr.view;

import com.example.cinema_rgr.entity.Movie;
import com.example.cinema_rgr.entity.Screening;
import com.example.cinema_rgr.entity.User;
import com.example.cinema_rgr.service.ScreeningService;
import com.example.cinema_rgr.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Controller
public class ScreeningViewController {

    @Autowired
    private ScreeningService screeningService;

    @Autowired
    private UsersService usersService;

    @GetMapping("/today")
    public String today(Model model) {
        model.addAttribute("title", "Синема парк. Сегодняшние показы");
        model.addAttribute("movieListHashMap", getScreeningsByDate(LocalDate.now()));
        model.addAttribute("user", getCurrentUsername());
        return "screenings";
    }

    @GetMapping("/tomorrow")
    public String tomorrow(Model model) {
        model.addAttribute("title", "Синема парк. Завтрашние показы");
        model.addAttribute("movieListHashMap", getScreeningsByDate(LocalDate.now().plusDays(1)));
        model.addAttribute("user", getCurrentUsername());
        return "screenings";
    }

    @GetMapping("/dayOfMonth")
    public String otherDays(Model model, @RequestParam(name = "dateTime") String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateTime, formatter);
        model.addAttribute("title", "Синема парк. Сегодняшние показы");
        model.addAttribute("movieListHashMap", getScreeningsByDate(date));
        model.addAttribute("user", getCurrentUsername());
        return "screenings";
    }

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("redirect:/today");
    }

    private User getCurrentUsername() {
        return usersService
                .findUserByEmail(usersService.getCurrentUsername())
                .orElse(null);
    }

    private Map<Movie, List<Screening>> getScreeningsByDate(LocalDate date) {
        return screeningService
                .findAllScreeningsByDatetime(date.getDayOfMonth(), date.getMonthValue());
    }
}
