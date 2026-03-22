package dev.vvalentine.movies.mapper;

import dev.vvalentine.movies.dto.MovieDTO;
import dev.vvalentine.movies.model.Country;
import dev.vvalentine.movies.model.Director;
import dev.vvalentine.movies.model.Genre;
import dev.vvalentine.movies.model.Movie;

import java.util.stream.Collectors;

public class MovieMapper {
    public static MovieDTO toDTO(Movie movie) {
        return MovieDTO.builder()
                .ID(movie.getID())
                .title(movie.getTitle())
                .duration(movie.getDuration())
                .releaseDate(movie.getReleaseDate())
                .poster_URL(movie.getPoster_URL())
                .genres(movie.getGenres().stream()
                        .map(Genre::getGenre)
                        .collect(Collectors.toSet()))
                .countries(movie.getCountries().stream()
                        .map(Country::getCountry)
                        .collect(Collectors.toSet()))
                .directors(movie.getDirectors().stream()
                        .map(Director::getName)
                        .collect(Collectors.toSet()))
                .build();
    }
}
