package com.javabeans.test.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MovieQueryResult implements IsSerializable {

	/**
	 * The slice of the list of movies that matched the given query. The slice
	 * is defined by {@link MovieQuery#getOffset()} and
	 * {@link MovieQuery#getLimit()}.
	 */
	private List<Movie> movies;
	/**
	 * The total amount of movies that matched the query.
	 */
	private int totalMovieCount;

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public int getTotalMovieCount() {
		return totalMovieCount;
	}

	public void setTotalMovieCount(int totalMovieCount) {
		this.totalMovieCount = totalMovieCount;
	}
}
