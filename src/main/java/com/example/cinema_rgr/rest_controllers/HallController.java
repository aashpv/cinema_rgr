package com.example.cinema_rgr.rest_controllers;

import com.example.cinema_rgr.entity.Hall;
import com.example.cinema_rgr.service.HallService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/halls")
@AllArgsConstructor
@Log
public class HallController {

    @Autowired
    private HallService hallService;

    @PostMapping("/save")
    public Hall saveHall(@RequestBody Hall hall) {
        log.info("Handling save hall: " + hall);
        return hallService.saveHall(hall);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        log.info("Handling delete hall request: " + id);
        hallService.deleteHallById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findById")
    public Hall findHallById(@RequestParam Short id) {
        log.info("Handling find by id hall request: " + id);
        return hallService.findHallById(id);
    }

    @GetMapping("/findByName")
    public Hall findHallByName(@RequestParam String name) {
        log.info("Handling find by name hall request: " + name);
        return hallService.findHallByName(name);
    }

    @GetMapping("/findAll")
    public List<Hall> findAllHalls() {
        log.info("Handling find all halls request");
        return hallService.findHallsAll();
    }
}
