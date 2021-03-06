package com.javabeans.test.server.bla;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gwt.thirdparty.guava.common.base.Splitter;
import com.javabeans.test.shared.Movie;

public class TsvReader {

	public List<Movie> parse(InputStream inputStream) {
		List<Movie> movies = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(
				inputStream))) {
			String line;
			while ((line = reader.readLine()) != null) {
				movies.add(this.parseMovie(line));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return movies;
	}
	
	private Movie parseMovie(String line) {

		List<String> columns = Splitter.on('\t').trimResults()
				.splitToList(line);
		// Movie movie = new Movie();
		// movie.setWikiMovieID(Long.valueOf(columns.get(0)));
		// movie.setFreebaseMovieID(columns.get(1));
		// etc.
		Long wikiMovieID = Long.valueOf(columns.get(0));
		String freebaseMovieID = columns.get(1);
		String title = columns.get(2);
		String year = columns.get(3);
		String boxOfficeRevenue = columns.get(4);
		String length = columns.get(5);
		List<String> languages = parseList(columns.get(6));
		List<String> countries = parseList(columns.get(7));
		List<String> genres = parseList(columns.get(8));

		return new Movie(wikiMovieID, freebaseMovieID, title, year,
				boxOfficeRevenue, length, languages, countries, genres);
	}

	/**
	 * Parse list with format "{"key1":"value1", "key2":"value2", ...}
	 */
	private List<String> parseList(String list) {
		String cleanList = list.trim().replace("{", "").replace("}", "");
		Map<String, String> keyValues = Splitter.on("\", \"")
				.omitEmptyStrings()
				.trimResults()
				.withKeyValueSeparator(':')
				.split(cleanList);
		List<String> values = new ArrayList<>();
		for (String value : keyValues.values()) {
			values.add(value.replace("\"", ""));
		}
		return values;
	}
	
	
	

}
