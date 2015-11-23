package com.javabeans.test.server.bla;

import java.io.InputStream;
import java.util.List;

import com.javabeans.test.shared.Movie;

public class TsvFileDataProvider implements FileDataProvider {

	private final InputStream moviesTsvFile;

	public TsvFileDataProvider(InputStream moviesTsvFile) {
		this.moviesTsvFile = moviesTsvFile;
	}
	
	@Override
	public List<Movie> getMovies() {
		return new TsvParser().parse(this.moviesTsvFile);
	}
}
