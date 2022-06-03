package com.example.cinema_rgr.rest_controllers;

import com.example.cinema_rgr.entity.Screening;
import com.example.cinema_rgr.service.HallService;
import com.example.cinema_rgr.service.MoviesService;
import com.example.cinema_rgr.service.ScreeningService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/screenings")
@AllArgsConstructor
@NoArgsConstructor
@Log
public class ScreeningController {

    @Autowired
    private ScreeningService screeningService;

    @Autowired
    private MoviesService moviesService;

    @Autowired
    private HallService hallService;

    @PostMapping("/save")
    public String saveScreening(int movieId, short hallId, Screening screening) {
        screening.setMovie(moviesService.findMovieById(movieId).orElse(null));
        screening.setHall(hallService.findHallById(hallId));
        log.info("Handling save screening: " + screening);
        screeningService.saveScreening(screening);
        return "redirect:/today";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteScreening(@PathVariable Integer id) {
        log.info("Handling delete screening request: " + id);
        screeningService.deleteScreeningById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findById")
    public Optional<Screening> findMovieById(@RequestParam Integer id) {
        log.info("Handling find by id screening request: " + id);
        return screeningService.findScreeningById(id);
    }

    @GetMapping("/findAll")
    public List<Screening> findAllScreenings() {
        log.info("Handling find all screenings request");
        return screeningService.findScreeningsAll();
    }

    @GetMapping("/findAllByMovieId")
    public List<Screening> findScreeningsAllByMovieId(@RequestParam Integer id) {
        log.info("Handling find all by movie_id screenings request");
        return screeningService.findAllByMovieId(id);
    }

    @GetMapping("/findAllByHallId")
    public List<Screening> findScreeningsAllByHallId(@RequestParam Short id) {
        log.info("Handling find all by hall_id screenings request");
        return screeningService.findAllByHallId(id);
    }

    @GetMapping("/findAllByDateTime")
    public List<Screening> findScreeningsAllByDateTime(@RequestParam LocalDateTime dateTime) {
        log.info("Handling find all by datetime screenings request");
        return screeningService.findAllByDateTime(dateTime);
    }

    @GetMapping("/findAllByDateTimeBefore")
    public List<Screening> findScreeningsAllByDateTimeBefore(@RequestParam LocalDateTime dateTime) {
        log.info("Handling find all by datetime before screenings request");
        return screeningService.findAllByDateTimeBefore(dateTime);
    }

    @GetMapping("/findAllByDateTimeAfter")
    public List<Screening> findScreeningsAllByDateTimeAfter(@RequestParam LocalDateTime dateTime) {
        log.info("Handling find all by datetime after screenings request");
        return screeningService.findAllByDateTimeAfter(dateTime);
    }
}
