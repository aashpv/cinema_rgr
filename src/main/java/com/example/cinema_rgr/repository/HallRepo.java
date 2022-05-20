package com.example.cinema_rgr.repository;

import com.example.cinema_rgr.entity.Hall;
import com.example.cinema_rgr.entity.HallRow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallRepo extends JpaRepository<Hall, HallRow> {
    //public void deleteHallById(Short id);
}
