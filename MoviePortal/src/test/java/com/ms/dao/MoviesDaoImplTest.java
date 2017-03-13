package com.ms.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.ms.model.Movie;

@RunWith(MockitoJUnitRunner.class)
public class MoviesDaoImplTest {

	// object under test
	private MoviesDaoImpl dao;

	// mock objects
	@Mock
	SessionFactory mockSessionFactory;
	@Mock
	Session mockSession;
	@Mock
	Transaction tx;
	@Mock
	Criteria mockCriteria;

	@Before
	public void setup() {

		dao = new MoviesDaoImpl();
		dao.sessionFactory = mockSessionFactory;
	}

	@Test
	public void testGetMoviesByGenre() throws Exception {
		final String genre = "lijin";
		List<Movie> movieslist = new ArrayList<Movie>();
		Movie movie = new Movie();
		movie.setGenre(genre);
		movie.setPrice("100");
		movie.setTitle("test");
		movie.setYear("2016");
		movie.setId(1000);
		movieslist.add(movie);
		Mockito.when(mockSessionFactory.openSession()).thenReturn(mockSession);
		Mockito.when(mockSession.beginTransaction()).thenReturn(tx);
		Mockito.when(mockSession.createCriteria(Movie.class)).thenReturn(mockCriteria);
		Mockito.when(mockCriteria.add((Criterion) Mockito.any(Criterion.class))).thenReturn(mockCriteria);
		Mockito.when(mockCriteria.list()).thenReturn(movieslist);
		List<Movie> actualMoviesList = dao.getMoviesByGenre(genre);

		Assert.assertNotNull(actualMoviesList);
		Assert.assertFalse(actualMoviesList.isEmpty());
		Assert.assertEquals(1, actualMoviesList.size());
		Assert.assertEquals(movie, actualMoviesList.get(0));
	}

	@Test
	public void testGetMoviesByYear() throws Exception {
		final String year = "2016";
		List<Movie> movieslist = new ArrayList<Movie>();
		Movie movie = new Movie();
		movie.setGenre("lijin");
		movie.setPrice("100");
		movie.setTitle("test");
		movie.setYear(year);
		movie.setId(1000);
		movieslist.add(movie);
		Mockito.when(mockSessionFactory.openSession()).thenReturn(mockSession);
		Mockito.when(mockSession.beginTransaction()).thenReturn(tx);
		Mockito.when(mockSession.createCriteria(Movie.class)).thenReturn(mockCriteria);
		Mockito.when(mockCriteria.add((Criterion) Mockito.any(Criterion.class))).thenReturn(mockCriteria);
		Mockito.when(mockCriteria.list()).thenReturn(movieslist);
		List<Movie> actualMoviesList = dao.getMoviesByYear(year);
		;

		Assert.assertNotNull(actualMoviesList);
		Assert.assertFalse(actualMoviesList.isEmpty());
		Assert.assertEquals(1, actualMoviesList.size());
		Assert.assertEquals(movie, actualMoviesList.get(0));
	}

}
