package com.example.cinema_rgr.service;

import com.example.cinema_rgr.entity.Movie;
import com.example.cinema_rgr.entity.Screening;
import com.example.cinema_rgr.repository.ScreeningRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class ScreeningService {

    private ScreeningRepository screeningRepository;

    public void saveScreening(Screening screening) {
        screeningRepository.save(screening);
    }

    public void deleteScreeningById(Integer screeningId) {
        screeningRepository.deleteById(screeningId);
    }

    public Optional<Screening> findScreeningById(Integer screeningId) {
        return screeningRepository.findById(screeningId);
    }

    public List<Screening> findScreeningsAll() {
        return screeningRepository.findAll();
    }
    public List<Screening> findAllByMovieId(Integer movieId) {
        return screeningRepository.findAllByMovieId(movieId);
    }
    public List<Screening> findAllByHallId(Short hallId) {
        return screeningRepository.findAllByHall_Id(hallId);
    }

    public List<Screening> findAllByDateTime(LocalDateTime dateTime) {
        return screeningRepository.findAllByDateTime(dateTime);
    }

    public List<Screening> findAllByDateTimeBefore(LocalDateTime dateTime) {
        return screeningRepository.findAllByDateTimeBefore(dateTime);
    }
    public List<Screening> findAllByDateTimeAfter(LocalDateTime dateTime) {
        return screeningRepository.findAllByDateTimeAfter(dateTime);
    }


    public List<Screening> findScreeningsByMovieIdToday(Integer movieId) {
        return screeningRepository.findAllByMovieId(movieId)
                .stream()
                .filter(screening ->
                        screening.getDateTime().getDayOfMonth()
                                == LocalDateTime.now().getDayOfMonth()
                                && screening.getDateTime().isAfter(LocalDateTime.now()))
                .sorted()
                .toList();
    }

    public List<Screening> findScreeningsByMovieIdTomorrow(Integer movieId) {
        return screeningRepository.findAllByMovieId(movieId)
                .stream()
                .filter(screening ->
                    screening.getDateTime().getDayOfMonth() == LocalDateTime
                            .now()
                            .plusDays(1)
                            .getDayOfMonth())
                .sorted()
                .toList();
    }

    public List<Screening> findScreeningsByMovieIdAfterTomorrow(Integer movieId) {
        return screeningRepository.findAllByMovieId(movieId)
                .stream()
                .filter(screening ->
                        screening.getDateTime()
                                .isAfter(LocalDateTime
                                        .now()
                                        .plusDays(2)))
                .sorted()
                .toList();
    }

    public Map<Movie, List<Screening>> findAllScreeningsByDatetime(Integer day, Integer month) {
        Map<Movie, List<Screening>> movieListHashMap = new HashMap<>();
        List<Screening> screeningList = screeningRepository.findScreeningsByDayOfMonth(day, month)
                .stream()
                .filter(screening -> LocalDateTime.now().isBefore(screening.getEndDateTime())).toList();
        List<Movie> movieList = new ArrayList<>();
        List<Movie> finalMovieList = movieList;
        screeningList.forEach(screening -> finalMovieList.add(screening.getMovie()));
        movieList = movieList.stream().distinct().toList();
        ListIterator<Movie> movieListIterator = movieList.listIterator();

        while(movieListIterator.hasNext()) {
            movieListHashMap
                    .put(movieListIterator.next(),
                            screeningRepository
                                    .findAllByMovieId(movieListIterator.previous().getId()).stream()
                                    .filter(screening -> LocalDateTime.now().isBefore(screening.getDateTime()))
                                    .filter(screening -> day == screening.getDateTime().getDayOfMonth())
                                    .sorted()
                                    .toList());
            movieListIterator.next();
        }

        return movieListHashMap;
    }

}
