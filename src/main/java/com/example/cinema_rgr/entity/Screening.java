package com.example.cinema_rgr.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "screening")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Screening implements Comparable<Screening> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "datetime")
    private LocalDateTime dateTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "end_screening")
    private LocalDateTime endDateTime;

    @Column(name = "cost")
    private Short cost;

    @Override
    public int compareTo(Screening o) {
        return getDateTime().compareTo(o.getDateTime());
    }
}
