package com.javabeans.test.server.bla;
import com.javabeans.test.shared.MovieQuery;
import com.javabeans.test.shared.MovieQueryResult;


public class RealDatabase implements Database {

	@Override
	public MovieQueryResult query(MovieQuery query) {

		// TODO real database implementation
		
		return new MovieQueryResult();
	}
}
