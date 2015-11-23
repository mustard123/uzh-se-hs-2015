package com.javabeans.test.server.bla;

import com.javabeans.test.shared.MovieQuery;
import com.javabeans.test.shared.MovieQueryResult;

/**
 * Abstraction of the movies database.
 */
public interface Database {

	/**
	 * Returns the movies that match the given query.
	 * 
	 * @param query
	 *            the query containing the search criteria
	 * @return the matching movies
	 */
	MovieQueryResult query(MovieQuery query);
}
