package com.example.cinema_rgr.service;

import com.example.cinema_rgr.entity.HallRow;
import com.example.cinema_rgr.repository.HallRowRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class HallRowService {

    private HallRowRepository hallRowRepository;

    public List<HallRow> findHallRowsByIdHall(Short idHall) {
        return hallRowRepository.findHallRowsByHallRowId_IdHall(idHall);
    }

    public List<HallRow> findHallRowsAll() {
        return hallRowRepository.findAll();
    }

}
