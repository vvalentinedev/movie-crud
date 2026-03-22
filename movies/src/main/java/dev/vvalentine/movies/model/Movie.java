package dev.vvalentine.movies.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Movies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "title")
    private String title;

    @Column(name = "duration")
    private int duration;

    @Column(name = "release_date")
    private int releaseDate;

    @Column(name = "poster_URL")
    private String poster_URL;

    @ManyToMany
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_ID"),
            inverseJoinColumns = @JoinColumn(name = "genre_ID")
    )
    private Set<Genre> genres = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "movie_country",
            joinColumns = @JoinColumn(name = "movie_ID"),
            inverseJoinColumns = @JoinColumn(name = "country_ID")
    )
    private Set<Country> countries = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "movie_director",
            joinColumns = @JoinColumn(name = "movie_ID"),
            inverseJoinColumns = @JoinColumn(name = "director_ID")
    )
    private Set<Director> directors = new HashSet<>();
}
