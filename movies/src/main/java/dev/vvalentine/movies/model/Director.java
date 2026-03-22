package dev.vvalentine.movies.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "directors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "directors")
    @ToString.Exclude
    private Set<Movie> movies = new HashSet<>();
}
