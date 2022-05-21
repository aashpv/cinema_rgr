package com.example.cinema_rgr.repository;

import com.example.cinema_rgr.entity.HallRow;
import com.example.cinema_rgr.entity.composite.HallRowId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HallRowRepository extends JpaRepository<HallRow, HallRowId> {
    List<HallRow> findHallRowsByHallRowId_IdHall(Short hallRowId_idHall);
}
