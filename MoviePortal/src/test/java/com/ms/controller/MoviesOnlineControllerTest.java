package com.ms.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.ms.exception.MovieException;
import com.ms.model.Movie;
import com.ms.model.Rent;
import com.ms.model.User;
import com.ms.services.MoviesServices;
import com.ms.services.RentsServices;
import com.ms.services.UsersServices;

@RunWith(MockitoJUnitRunner.class)
public class MoviesOnlineControllerTest {

	private MoviesOnlineController controller;

	@Mock
	MoviesServices mockMoviesServices;

	@Mock
	RentsServices mockRentsServices;

	@Mock
	UsersServices mockUsersServices;

	@Before
	public void setup() {

		controller = new MoviesOnlineController();
		controller.setMoviesServices(mockMoviesServices);
		controller.setRentsServices(mockRentsServices);
		controller.setUsersServices(mockUsersServices);
	}

	@Test
	public void testGetMoviesByGenre() throws MovieException, Exception {

		final String genre = "lijin";
		List<Movie> movieslist = new ArrayList<Movie>();
		Movie movie = new Movie();
		movie.setGenre(genre);
		movie.setPrice("100");
		movie.setTitle("test");
		movie.setYear("2016");
		movie.setId(1000);
		movieslist.add(movie);

		Mockito.when(mockMoviesServices.getMoviesByGenre(genre)).thenReturn(movieslist);

		ResponseEntity<List<Movie>> actualRespEntity = controller.getMoviesByGenre(genre);

		Assert.assertNotNull(actualRespEntity);
		Assert.assertFalse(actualRespEntity.getBody().isEmpty());
		Assert.assertEquals(1, actualRespEntity.getBody().size());
		Assert.assertEquals(movie, actualRespEntity.getBody().get(0));

	}

	@Test
	public void testGetMoviesByYear() throws MovieException, Exception {
		final String year = "2016";
		List<Movie> movieslist = new ArrayList<Movie>();
		Movie movie = new Movie();
		movie.setGenre("lijin");
		movie.setPrice("100");
		movie.setTitle("test");
		movie.setYear(year);
		movie.setId(1000);
		movieslist.add(movie);

		Mockito.when(mockMoviesServices.getMoviesByYear(year)).thenReturn(movieslist);

		ResponseEntity<List<Movie>> actualRespEntity = controller.getMoviesByYear(year);

		Assert.assertNotNull(actualRespEntity);
		Assert.assertFalse(actualRespEntity.getBody().isEmpty());
		Assert.assertEquals(1, actualRespEntity.getBody().size());
		Assert.assertEquals(movie, actualRespEntity.getBody().get(0));
	}

	@Test
	public void testRentMovie() throws MovieException, Exception {
		final String email = "junituser@abc.com";
		Rent rent = new Rent();
		rent.setActualPrice("100");
		rent.setEmail(email);
		rent.setRentDate(new Date("01/01/2017"));
		rent.setRentId(12345);

		User user = new User();
		user.setEmail(email);
		user.setfName("name");
		user.setlName("john");
		user.setUsertype("REG");

		Mockito.when(mockUsersServices.getUserById(email)).thenReturn(user);
		controller.rentMovie(rent);

		User actualUser = mockUsersServices.getUserById(email);

		Assert.assertNotNull(actualUser);
		Assert.assertEquals(user, user);

		Mockito.when(mockUsersServices.getUserById("")).thenReturn(user);
		controller.rentMovie(rent);
		Mockito.when(mockUsersServices.getUserById(null)).thenReturn(user);
		controller.rentMovie(rent);

	}

	@Test
	public void testGetRentedMovies() throws Exception {
		final String email = "junituser@abc.com";
		List<Rent> rentsList = new ArrayList<Rent>();
		Rent rent = new Rent();
		rent.setActualPrice("100");
		rent.setEmail(email);
		rent.setRentDate(new Date("01/01/2017"));
		rent.setRentId(12345);
		rentsList.add(rent);

		Mockito.when(mockRentsServices.getRentsByEmail(email)).thenReturn(rentsList);

		ResponseEntity<List<Rent>> actualRespEntity = controller.getRentedMovies(email);

		Assert.assertNotNull(actualRespEntity);
		Assert.assertFalse(actualRespEntity.getBody().isEmpty());
		Assert.assertEquals(1, actualRespEntity.getBody().size());
		Assert.assertEquals(rent, actualRespEntity.getBody().get(0));
	}

}
