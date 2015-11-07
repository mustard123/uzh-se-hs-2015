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
//	TODO Get list of all languages, countries and genre
//	private List<String> allLanguages = new ArrayList<>();
//	private List <String> allCountries = new ArrayList<>();
//	private List<String> allGenres = new ArrayList<>();
	
	
	
	@Override
	public List<Movie> query(MovieQuery query) {
		parseMovies();
		
		return new ArrayList<>(FluentIterable.from(movieListCache)
				.filter(namePredicate(query.getName()))
				.filter(yearPredicate(query.getYear()))
				.filter(countryPredicate(query.getCountry()))
				.filter(languagePredicate(query.getLanguage()))
				.filter(genrePredicate(query.getGenre()))
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
//			for(Movie e : movieListCache){
//				filterLanguages(e, allLanguages);
//				filterCountries(e, allCountries);
//				filterGenres(e, allGenres);
//			}
		}
	}
	
// TODO Get list of all languages, countries, genres.
//	private void filterLanguages(Movie movie, List<String> allLanguages){
//		List<String> languages = movie.getLanguages();
//		for(String l : languages){
//			if(!(allLanguages.contains(l))){
//				allLanguages.add(l);
//			}
//		}
//	}
//	private void filterCountries(Movie movie, List<String> allCountries){
//		List<String> countries = movie.getCountries();
//		for(String c : countries){
//			if(!(allCountries.contains(c))){
//				allCountries.add(c);
//			}
//		}
//	}
//	private void filterGenres(Movie movie, List<String> allGenres){
//		List<String> genres = movie.getLanguages();
//		for(String g : genres){
//			if(!(allGenres.contains(g))){
//				allGenres.add(g);
//			}
//		}
//	}

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
				if(movie.getYear() == null){
					return year == null;
				}
				return movie.getYear().contains(year);
			}
		};
	}

	private static Predicate<Movie> countryPredicate(String country) {
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
	
	private static Predicate<Movie> languagePredicate(String language) {
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
	
	private static Predicate<Movie> genrePredicate(String genre) {
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
