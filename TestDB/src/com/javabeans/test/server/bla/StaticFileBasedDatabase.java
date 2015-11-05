package com.javabeans.test.server.bla;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.thirdparty.guava.common.base.Predicate;
import com.google.gwt.thirdparty.guava.common.collect.FluentIterable;
import com.javabeans.test.shared.Movie;
import com.javabeans.test.shared.MovieQuery;


public class StaticFileBasedDatabase implements Database {

	private List<Movie> movieListCache = new ArrayList<>();
	
	@Override
	public List<Movie> query(MovieQuery query) {
		parseMovies();
		
		return new ArrayList<>(FluentIterable.from(movieListCache)
				.filter(namePredicate(query.getName()))
				// TODO filter by other search criteria
				// .filter(yearPredicate(query.getYear()))
				// etc.
				.toList());
	}
	
	/**
	 * Parse the movie file (only once) and cache it in movieListCache
	 */
	private synchronized void parseMovies() {
		if(movieListCache.isEmpty()) {
			InputStream inputStream = Thread.currentThread()
					.getContextClassLoader()
					.getResourceAsStream("movies_80000.tsv");
			movieListCache.addAll(new TsvReader().parse(inputStream));
		}
	}

	private static Predicate<Movie> namePredicate(String title) {
		return new Predicate<Movie>() {
			/**
			 * @return true if the movie matches the given title
			 */
			@Override
			public boolean apply(Movie movie) {
				if(title == null) {
					return true;
				}
				if(movie.getTitle() == null) {
					return title == null;
				}
				return movie.getTitle().toLowerCase().contains(title.toLowerCase());
			}
		};
	}

	private static Predicate<Movie> yearPredicate(String year) {
		return new Predicate<Movie>() {
			@Override
			public boolean apply(Movie movie) {
				if(year == null) {
					return true;
				}
				return year.equals(movie.getYear());
			}
		};
	}
}
