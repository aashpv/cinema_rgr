package com.example.cinema_rgr.repository;

import com.example.cinema_rgr.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallRepository extends JpaRepository<Hall, Long> {
    Hall findHallByName(String name);
    Hall findHallById(Short id);
}
