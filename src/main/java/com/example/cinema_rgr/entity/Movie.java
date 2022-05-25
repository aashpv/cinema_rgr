package com.example.cinema_rgr.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "movies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "genre")
    private String genre;

    @Column(name = "producer")
    private String producer;

    @Column(name = "starring")
    private String starring;

    @Column(name = "path")
    private String path;
}