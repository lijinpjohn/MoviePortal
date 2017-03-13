package com.ms.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.ms.dao.MoviesDao;
import com.ms.model.Movie;

@RunWith(MockitoJUnitRunner.class)
public class MoviesServicesImplTest {

	// object under test
	private MoviesServicesImpl service;

	// mock objects
	@Mock
	private MoviesDao mockMoviesDao;

	@Before
	public void setup() {

		service = new MoviesServicesImpl();
		service.setMoviesDao(mockMoviesDao);
	}

	@Test
	public void testMoviesByGenre() throws Exception {

		final String genre = "test genre";
		List<Movie> movieList = new ArrayList<>();
		Movie movie = new Movie();
		movie.setGenre(genre);
		movie.setYear("2016");
		movie.setPrice("100");
		movie.setTitle("test title");
		movie.setId(1000);
		movieList.add(movie);
		Mockito.when(mockMoviesDao.getMoviesByGenre(genre)).thenReturn(movieList);
		List<Movie> actualMovies = service.getMoviesByGenre(genre);

		Assert.assertNotNull("rents must not be NULL", actualMovies);
		Assert.assertEquals(movieList, actualMovies);
	}

	@Test
	public void testMoviesByYear() throws Exception {

		final String year = "2016";
		List<Movie> movieList = new ArrayList<>();
		Movie movie = new Movie();
		movie.setGenre("test genre");
		movie.setYear("2016");
		movie.setPrice("100");
		movie.setTitle("test title");
		movie.setId(1000);
		movieList.add(movie);
		Mockito.when(mockMoviesDao.getMoviesByYear(year)).thenReturn(movieList);
		List<Movie> actualMovies = service.getMoviesByYear(year);

		Assert.assertNotNull("rents must not be NULL", actualMovies);
		Assert.assertEquals(movieList, actualMovies);
	}

}
