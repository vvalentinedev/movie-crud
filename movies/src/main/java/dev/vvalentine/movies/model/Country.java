package dev.vvalentine.movies.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "countries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(nullable = false)
    private String country;

    @ManyToMany(mappedBy = "countries")
    @ToString.Exclude
    private Set<Movie> movies = new HashSet<>();
}
