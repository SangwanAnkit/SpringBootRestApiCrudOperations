package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Movie;
import com.example.demo.repository.MovieRepository;

@RestController
@RequestMapping("/api")
public class MovieController {

	@Autowired
	private MovieRepository movieRepository;

	// get list of movies
	@GetMapping("/movies")
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	// get a particular movie By ID
	@GetMapping("/movies/{id}")
	public ResponseEntity<Movie> getMovieById(@PathVariable("id") Long id) {
		Optional<Movie> movie = movieRepository.findById(id);

		if (movie.isPresent()) {
			return new ResponseEntity<>(movie.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	// create a new movie in db
	@PostMapping("/addMovie")
	public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
		try {
			Movie _movie = movieRepository.save(movie);
			return new ResponseEntity<>(_movie, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// update a movie corresponding to the given ID
	@PutMapping("/movies/{id}")
	public ResponseEntity<Movie> updateMovie(@PathVariable("id") Long id, @RequestBody Movie movie) {
		Optional<Movie> movieData = movieRepository.findById(id);

		if (movieData.isPresent()) {
			Movie _movie = movieData.get();
			_movie.setTitle(movie.getTitle());
			_movie.setCategory(movie.getCategory());
			_movie.setStarRating(movie.getStarRating());

			return new ResponseEntity<>(movieRepository.save(_movie), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	// Delete a movie from db corresponding to particular ID
	@DeleteMapping("/movies/{id}")
	public ResponseEntity<Movie> deleteById(@PathVariable("id") Long id) {
		try {
			movieRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
