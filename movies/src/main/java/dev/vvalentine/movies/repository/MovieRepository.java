package dev.vvalentine.movies.repository;

import dev.vvalentine.movies.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Optional<Movie> findById(Integer ID);

    Optional<Movie> findByTitle(String title);

    List<Movie> findByTitleContainingIgnoreCase(String title);
}
