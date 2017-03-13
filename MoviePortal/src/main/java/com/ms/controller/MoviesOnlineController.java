package com.ms.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ms.exception.MovieException;
import com.ms.model.ErrorStatus;
import com.ms.model.Movie;
import com.ms.model.Rent;
import com.ms.model.Status;
import com.ms.model.User;
import com.ms.services.MoviesServices;
import com.ms.services.RentsServices;
import com.ms.services.UsersServices;

@Controller
@RequestMapping("/moviesonline")
public class MoviesOnlineController {

	MoviesServices moviesServices;

	RentsServices rentsServices;

	UsersServices usersServices;

	static final Logger logger = Logger.getLogger(MoviesOnlineController.class);

	// invoke this method to browse movie by genre
	@RequestMapping(value = "genre/{genre}", method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> getMoviesByGenre(@PathVariable("genre") String genre)
			throws MovieException, Exception {
		List<Movie> moviesList = null;

		if ("".equals(genre) || null == genre) {
			throw new MovieException("Genre not found in the request");
		}
		moviesList = moviesServices.getMoviesByGenre(genre);
		return new ResponseEntity<List<Movie>>(moviesList, HttpStatus.OK);
	}

	// invoke this method to browse movie by year
	@RequestMapping(value = "year/{year}", method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> getMoviesByYear(@PathVariable("year") String year)
			throws MovieException, Exception {
		List<Movie> moviesList = null;

		if ("".equals(year) || null == year) {
			throw new MovieException("Year not found in the request");
		}
		moviesList = moviesServices.getMoviesByYear(year);
		return new ResponseEntity<List<Movie>>(moviesList, HttpStatus.OK);
	}

	// here user need to pass the details of the movie to be rented as JSON
	// assumes that the user is already present in user table
	@RequestMapping(value = "/rent", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status rentMovie(@RequestBody Rent rents) throws MovieException, Exception {
		User user = usersServices.getUserById(rents.getEmail());
		if ("".equals(rents.getEmail()) || null == rents.getEmail()) {
			throw new MovieException("email not set to Rent object");
		}
		rentsServices.rentMovie(rents, user);
		return new Status(1, "rented Successfully !");
	}

	// invoke this method to show the rented movies
	@RequestMapping(value = "myrent/{email}", method = RequestMethod.GET)
	public ResponseEntity<List<Rent>> getRentedMovies(@PathVariable("email") String email)
			throws MovieException, Exception {
		List<Rent> rentsList = null;
		if ("".equals(email) || null == email) {
			throw new MovieException("Year not found in the request");
		}
		rentsList = rentsServices.getRentsByEmail(email);

		return new ResponseEntity<List<Rent>>(rentsList, HttpStatus.OK);
	}

	@ExceptionHandler(MovieException.class)
	public ResponseEntity<ErrorStatus> exceptionHandler(Exception ex) {
		ErrorStatus errorStatus = new ErrorStatus();
		errorStatus.setCode(HttpStatus.PRECONDITION_FAILED.value());
		errorStatus.setMessage(ex.getMessage());
		logger.error(ex.getMessage(), ex);
		return new ResponseEntity<ErrorStatus>(errorStatus, HttpStatus.OK);
	}

	@Autowired
	public void setMoviesServices(MoviesServices moviesServices) {
		this.moviesServices = moviesServices;
	}

	@Autowired
	public void setRentsServices(RentsServices rentsServices) {
		this.rentsServices = rentsServices;
	}

	@Autowired
	public void setUsersServices(UsersServices usersServices) {
		this.usersServices = usersServices;
	}

}
