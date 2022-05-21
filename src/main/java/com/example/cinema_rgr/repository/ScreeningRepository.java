package com.example.cinema_rgr.repository;

import com.example.cinema_rgr.entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;
import java.util.List;

public interface ScreeningRepository extends JpaRepository<Screening, Integer> {

    List<Screening> findAllByMovieId(Integer movieId);
    List<Screening> findAllByHall_Id(Short hall_id);
    List<Screening> findAllByDateTime(LocalDateTime dateTime);
    List<Screening> findAllByDateTimeBefore(LocalDateTime dateTime);
    List<Screening> findAllByDateTimeAfter(LocalDateTime dateTime);

    @Query(nativeQuery = true, value = "SELECT * from screening " +
            "where EXTRACT(DAY FROM datetime) = ?1 and EXTRACT(MONTH FROM datetime) = ?2")
    List<Screening> findScreeningsByDayOfMonth(Integer day, Integer month);

}
