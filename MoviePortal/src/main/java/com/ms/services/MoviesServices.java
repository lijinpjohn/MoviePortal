package com.ms.services;

import java.util.List;

import com.ms.model.Movie;

public interface MoviesServices {

	List<Movie> getMoviesByGenre(String genre) throws Exception;

	List<Movie> getMoviesByYear(String year) throws Exception;

}
