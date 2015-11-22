package com.javabeans.test.server.bla;
import java.util.List;

import com.javabeans.test.shared.Movie;
import com.javabeans.test.shared.MovieQuery;
import com.javabeans.test.shared.MovieQueryResult;


public interface Database {

	MovieQueryResult query(MovieQuery query);
}
