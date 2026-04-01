package dev.vvalentine.movies.repository;

import dev.vvalentine.movies.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Optional<Movie> findById(Integer ID);

    Optional<Movie> findByTitle(String title);

    @Query("SELECT DISTINCT m FROM Movie m " +
           "LEFT JOIN m.genres g " +
           "LEFT JOIN m.directors d " +
           "LEFT JOIN m.countries c " +
           "WHERE LOWER(m.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
           "OR LOWER(g.genre) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
           "OR LOWER(d.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
           "OR LOWER(c.country) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Movie> searchInclusive(@Param("searchTerm") String searchTerm);
}
