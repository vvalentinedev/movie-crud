package dev.vvalentine.movies.service;

import dev.vvalentine.movies.dto.MovieDTO;
import dev.vvalentine.movies.mapper.MovieMapper;
import dev.vvalentine.movies.model.Movie;
import dev.vvalentine.movies.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public MovieDTO getMovieById(Integer ID) {
        Movie movie = movieRepository.findById(ID)
                .orElseThrow(() -> new RuntimeException("Movie not found with ID " + ID));
        return MovieMapper.toDTO(movie);
    }

    public List<MovieDTO> getMovieByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(MovieMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<MovieDTO> getAllMovies() {
        return movieRepository.findAll()
                .stream()
                .map(MovieMapper::toDTO)
                .collect(Collectors.toList());
    }
}
