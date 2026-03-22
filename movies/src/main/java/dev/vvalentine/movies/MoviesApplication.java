package dev.vvalentine.movies;

import dev.vvalentine.movies.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MoviesApplication {

	/*
	@Bean
	CommandLineRunner checkDatabase(MovieRepository repository) {
		return args -> {
			System.out.println("--- DATABASE CHECK ---");
			System.out.println("Total rows in DB: " + repository.count());
			System.out.println("-----------------------");
		};
	}
	*/

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**")
						.allowedOrigins("http://localhost:4200")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(MoviesApplication.class, args);
	}

}

