package com.javabeans.test.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.javabeans.test.shared.Movie;
import com.javabeans.test.shared.MovieQuery;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface MovieServiceAsync {
	void getMoviesFromServer(MovieQuery query, AsyncCallback<List<Movie>> callback);
}
