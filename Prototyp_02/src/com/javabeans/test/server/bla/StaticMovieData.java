package com.javabeans.test.server.bla;

import java.util.Arrays;
import java.util.List;

import com.javabeans.test.shared.Movie;

public class StaticMovieData {

	private static final Movie[] movies = {
		
	};
	
	public static List<Movie> getMovies() {
		return Arrays.asList(movies);
	}
}
