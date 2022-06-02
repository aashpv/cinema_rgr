package com.example.cinema_rgr.rest_controllers;

import com.example.cinema_rgr.entity.Movie;
import com.example.cinema_rgr.service.MoviesService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/movies")
@AllArgsConstructor
@NoArgsConstructor
@Log
public class MoviesController {

    @Autowired
    private MoviesService moviesService;

    @Value("${uploadPath}")
    private String uploadPath;

    @PostMapping("/save")
    public String saveMovie(Movie movie,
                           @RequestParam("file") MultipartFile file) throws IOException {
        if(file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." +
                    FilenameUtils.removeExtension(file.getOriginalFilename()) + ".jpg";
            file.transferTo(new File(uploadPath + "/" + resultFileName));
            movie.setPath("img/" + resultFileName);
        }
        log.info("Handling save movie: " + movie);
        moviesService.saveMovie(movie);
        return "redirect:/today";
    }

    @PostMapping("/delete")
    public String deleteMovie(Integer id) {
        log.info("Handling delete movie request: " + id);
        moviesService.deleteMovieById(id);
        return "redirect:/myProfile";
    }

    @GetMapping("/findById")
    public Optional<Movie> findMovieById(@RequestParam Integer id) {
        log.info("Handling find by id movie request: " + id);
        return moviesService.findMovieById(id);
    }

    @GetMapping("/findByName")
    public Movie findMovieByName(@RequestParam String name) {
        log.info("Handling find by name movie request: " + name);
        return moviesService.findMovieByName(name);
    }

    @GetMapping("/findAll")
    public List<Movie> findAllMovies() {
        log.info("Handling find all movies request");
        return moviesService.findMoviesAll();
    }

}
