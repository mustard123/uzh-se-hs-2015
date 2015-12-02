package com.javabeans.test.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.javabeans.test.shared.CountryCounter;
import com.javabeans.test.shared.MapQuery;
import com.javabeans.test.shared.MapQueryResult;
import com.javabeans.test.shared.MovieQuery;
import com.javabeans.test.shared.MovieQueryResult;
import com.javabeans.test.shared.SearchFormData;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface MovieService extends RemoteService {

	/**
	 * Get movies that match the given search criteria.
	 * 
	 * @param query
	 *            The object containing the search criteria
	 * @return the list of matching movies
	 */
	MovieQueryResult getMoviesFromServer(MovieQuery query);

	/**
	 * Get the movie data for the map view
	 * @param query the query that contains the search criteria for the movies
	 * @return the list of {@link CountryCounter}s
	 */
	MapQueryResult getMapDataFromServer(MapQuery query);
	
	/**
	 * Get languages, genres, countries and years for the listboxes.
	 * 
	 * @return a container with languages, genres, countries and years for the listboxes.
	 */
	SearchFormData getSearchFormData();
	
	// add methods here to get data from the server (e.g., all available
	// languages)
	// and adapt MovieServiceAsync and MovieServiceImpl
}
