package dev.vvalentine.movies.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "genres")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(nullable = false)
    private String genre;

    @ManyToMany(mappedBy = "genres")
    @ToString.Exclude
    private Set<Movie> movies = new HashSet<>();
}
