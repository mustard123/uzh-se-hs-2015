package com.javabeans.test.server.bla;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.thirdparty.guava.common.base.Predicate;
import com.google.gwt.thirdparty.guava.common.collect.FluentIterable;
import com.javabeans.test.shared.Movie;
import com.javabeans.test.shared.MovieQuery;
import com.javabeans.test.shared.MovieQueryResult;
import com.javabeans.test.shared.SortColumn;


public class FileBasedDatabase implements Database {

	private static final Logger LOGGER = Logger.getLogger(FileBasedDatabase.class.getSimpleName());
	
	private final FileDataProvider dataProvider;
	
	private List<Movie> movieListCache = new ArrayList<>();
//	TODO Get list of all languages, countries and genre
//	private List<String> allLanguages = new ArrayList<>();
//	private List <String> allCountries = new ArrayList<>();
//	private List<String> allGenres = new ArrayList<>();
	
	public FileBasedDatabase(FileDataProvider dataProvider) {
		this.dataProvider = dataProvider;
	}
	
	@Override
	public MovieQueryResult query(MovieQuery query) {
		LOGGER.info("Executing query...");
		parseMovies();
		LOGGER.info("Movies parsed");
		SortColumn sortColumn = query.getSortColumn() != null ? query.getSortColumn() : SortColumn.TITLE;
		
		List<Movie> filteredMovies = FluentIterable.from(movieListCache)
				.filter(namePredicate(query.getName()))
				.filter(yearPredicate(query.getYear()))
				.filter(countryPredicate(query.getCountry()))
				.filter(languagePredicate(query.getLanguage()))
				.filter(genrePredicate(query.getGenre()))
				.toSortedList(sortColumn.getComparator(query.isAscending()));
		
		int limit = query.getLimit() == null ? Integer.MAX_VALUE : query.getLimit();
		ArrayList<Movie> movieListSlice = new ArrayList<>(FluentIterable.from(filteredMovies)
				.skip(query.getOffset())
				.limit(limit)
				.toList());
		
		LOGGER.info("Movies filtered");
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
			this.movieListCache.addAll(dataProvider.getMovies());
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
