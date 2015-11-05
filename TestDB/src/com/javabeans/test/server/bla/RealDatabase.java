package com.javabeans.test.server.bla;
import java.util.ArrayList;
import java.util.List;

import com.javabeans.test.shared.Movie;
import com.javabeans.test.shared.MovieQuery;


public class RealDatabase implements Database {

	@Override
	public List<Movie> query(MovieQuery query) {

		// TODO real database implementation
		
		return new ArrayList<>();
	}
}
