package com.ms.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.ms.model.Movie;

public class MoviesDaoImpl implements MoviesDao {

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;

	@Override
	public List<Movie> getMoviesByGenre(String genre) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Movie> moviesList = session.createCriteria(Movie.class).add(Restrictions.eq("genre", genre)).list();
		tx.commit();
		session.close();
		return moviesList;
	}

	@Override
	public List<Movie> getMoviesByYear(String year) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Movie> moviesList = session.createCriteria(Movie.class).add(Restrictions.eq("year", year)).list();
		tx.commit();
		session.close();
		return moviesList;
	}

}
