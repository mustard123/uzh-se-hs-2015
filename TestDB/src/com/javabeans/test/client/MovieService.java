package com.javabeans.test.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.javabeans.test.shared.Movie;
import com.javabeans.test.shared.MovieQuery;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface MovieService extends RemoteService {
	
	/**
	 * Get movies that match the given search criteria.
	 * @param query The object containing the search criteria
	 * @return the list of matching movies
	 */
	List<Movie> getMoviesFromServer(MovieQuery query);
	
	// add methods here to get data from the server (e.g., all available languages)
	// and adapt MovieServiceAsync and MovieServiceImpl
}
