package com.ms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ms.dao.MoviesDao;
import com.ms.model.Movie;

public class MoviesServicesImpl implements MoviesServices {

	private MoviesDao moviesDao;

	@Override
	public List<Movie> getMoviesByGenre(String genre) throws Exception {
		return moviesDao.getMoviesByGenre(genre);
	}

	@Override
	public List<Movie> getMoviesByYear(String year) throws Exception {
		return moviesDao.getMoviesByYear(year);
	}

	@Autowired
	public void setMoviesDao(MoviesDao moviesDao) {
		this.moviesDao = moviesDao;
	}
}
