package com.javabeans.test.server.bla;
import java.util.List;

import com.javabeans.test.shared.Movie;
import com.javabeans.test.shared.MovieQuery;


public interface Database {

	List<Movie> query(MovieQuery query);
}
