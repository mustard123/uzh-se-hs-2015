package com.javabeans.test.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.javabeans.test.shared.MapQuery;
import com.javabeans.test.shared.MapQueryResult;
import com.javabeans.test.shared.MovieQuery;
import com.javabeans.test.shared.MovieQueryResult;
import com.javabeans.test.shared.SearchFormData;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface MovieServiceAsync {
	void getMoviesFromServer(MovieQuery query, AsyncCallback<MovieQueryResult> callback);

	void getMapDataFromServer(MapQuery query, AsyncCallback<MapQueryResult> callback);

	void getSearchFormData(AsyncCallback<SearchFormData> callback);
}
