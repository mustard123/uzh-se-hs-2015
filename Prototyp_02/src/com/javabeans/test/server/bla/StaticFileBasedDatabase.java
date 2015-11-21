package com.javabeans.test.server.bla;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.thirdparty.guava.common.base.Predicate;
import com.google.gwt.thirdparty.guava.common.collect.FluentIterable;
import com.javabeans.test.shared.Movie;
import com.javabeans.test.shared.MovieQuery;
import com.javabeans.test.shared.MovieQueryResult;


public class StaticFileBasedDatabase implements Database {

	private final InputStream moviesTsvFile;
	
	private List<Movie> movieListCache = new ArrayList<>();
//	TODO Get list of all languages, countries and genre
//	private List<String> allLanguages = new ArrayList<>();
//	private List <String> allCountries = new ArrayList<>();
//	private List<String> allGenres = new ArrayList<>();
	
	public StaticFileBasedDatabase(InputStream moviesTsvFile) {
		this.moviesTsvFile = moviesTsvFile;
	}
	
	@Override
	public MovieQueryResult query(MovieQuery query) {
		parseMovies();
		
		List<Movie> filteredMovies = FluentIterable.from(movieListCache)
				.filter(namePredicate(query.getName()))
				.filter(yearPredicate(query.getYear()))
				.filter(countryPredicate(query.getCountry()))
				.filter(languagePredicate(query.getLanguage()))
				.filter(genrePredicate(query.getGenre()))
				// TODO filter by other search criteria
				// .filter(yearPredicate(query.getYear()))
				// etc.
				.toList();
		ArrayList<Movie> movieListSlice = new ArrayList<>(FluentIterable.from(filteredMovies)
				.skip(query.getOffset())
				.limit(query.getLimit())
				.toList());
		
		MovieQueryResult result = new MovieQueryResult();
		result.setMovies(movieListSlice);
		result.setTotalMovieCount(filteredMovies.size());
		return result;
	}
	

	/**
	 * Parse the movie file (only once) and cache it in movieListCache
	 */
	private synchronized void parseMovies() {
		if(movieListCache.isEmpty()) {
			movieListCache.addAll(new TsvReader().parse(this.moviesTsvFile));
		}
	}


	private static Predicate<Movie> namePredicate(final String title) {
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
	private static Predicate<Movie> yearPredicate(final String year) {
		return new Predicate<Movie>() {
			@Override
			public boolean apply(Movie movie) {
				if(year == null) {
					return true;
				}
				if(movie.getYear() == null){
					return year == null;
				}
				return movie.getYear().contains(year);
			}
		};
	}

	private static Predicate<Movie> countryPredicate(final String country) {
		return new Predicate<Movie>() {
			@Override
			public boolean apply(Movie movie) {
				if(country == null) {
					return true;
				}
				if(movie.getCountrAsString() == null){
					return country == null;
				}
				return movie.getCountrAsString().toLowerCase().contains(country.toLowerCase());
			}
		};
	}
	
	private static Predicate<Movie> languagePredicate(final String language) {
		return new Predicate<Movie>() {
			@Override
			public boolean apply(Movie movie) {
				if(language == null) {
					return true;
				}
				if(movie.getLanguages() == null){
					return language == null;
				}
				return movie.getLangAsString().toLowerCase().contains(language.toLowerCase());
			}
		};
	}
	
	private static Predicate<Movie> genrePredicate(final String genre) {
		return new Predicate<Movie>() {
			@Override
			public boolean apply(Movie movie) {
				if(genre == null) {
					return true;
				}
				if(movie.getGenres() == null){
					return genre == null;
				}
				return movie.getGenreAsString().toLowerCase().contains(genre.toLowerCase());
			}
		};
	}
	

}
