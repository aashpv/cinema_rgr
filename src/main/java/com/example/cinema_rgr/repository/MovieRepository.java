package com.example.cinema_rgr.repository;


import com.example.cinema_rgr.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Movie findByName(String name);

}
