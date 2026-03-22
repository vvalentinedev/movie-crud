package dev.vvalentine.movies.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    private int ID;
    private String title;
    private int duration;
    private int releaseDate;
    private String poster_URL;
    private Set<String> genres;
    private Set<String> countries;
    private Set<String> directors;
}
