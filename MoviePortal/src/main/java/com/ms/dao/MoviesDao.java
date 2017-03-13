package com.ms.dao;

import java.util.List;

import com.ms.model.Movie;

public interface MoviesDao {

	List<Movie> getMoviesByGenre(String genre) throws Exception;

	List<Movie> getMoviesByYear(String year) throws Exception;
}
