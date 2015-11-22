package com.javabeans.test.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MovieQueryResult implements IsSerializable {

	private List<Movie> movies;
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
