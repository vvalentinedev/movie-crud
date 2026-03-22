package dev.vvalentine.movies.controller;

import dev.vvalentine.movies.dto.MovieDTO;
import dev.vvalentine.movies.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/hello")
    public String Hello() {
        return "Hello, World!";
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/{ID}")
    public ResponseEntity<MovieDTO> getById(@PathVariable Long ID) {
        return ResponseEntity.ok(movieService.getMovieById(ID));
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieDTO>> getByTitle(@RequestParam String title) {
        return ResponseEntity.ok(movieService.getMovieByTitle(title));
    }
}
