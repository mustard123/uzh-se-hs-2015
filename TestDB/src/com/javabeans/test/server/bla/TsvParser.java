package com.javabeans.test.server.bla;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.google.gwt.thirdparty.guava.common.base.Splitter;
import com.javabeans.test.shared.Movie;

public class TsvParser {

	/**
	 * 
	 * @param inputStream the input file for parsing.
	 * @return a list of Movie objects.
	 */
	public List<Movie> parse(InputStream inputStream) {
		List<Movie> movies = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(
				inputStream))) {
			String line;
			while ((line = reader.readLine()) != null) {
				movies.add(this.parseMovie(convertFromEscapedUtf(line)));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return movies;
	}
	

	private String convertFromEscapedUtf(String raw) throws IOException {
		Properties p = new Properties();
		p.load(new StringReader("key="+raw));
		return p.getProperty("key");
	}
	
	/**
	 * 
	 * @param line in a TSV file.
	 * @return Movie object with wikiMovieID, freebaseMovieID, title, year,
				boxOfficeRevenue, length, languages, countries, genres as attributes.
	 */
	private Movie parseMovie(String line) {
		
		List<String> columns = Splitter.on('\t').trimResults()
				.splitToList(line);
		Long wikiMovieID = Long.valueOf(columns.get(0));
		String freebaseMovieID = columns.get(1);
		String title = columns.get(2);
		Integer year = columns.get(3).isEmpty() ? null : Integer.valueOf(columns.get(3).substring(0,4));
		if(year != null && year < 1888) {
			System.out.println("Errör: year string '" + columns.get(3) + "' is " + year + " (wiki id: " + wikiMovieID + ")");
		}
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
			values.add(value.replace("\"", "").trim());
		}
		return values;
	}
	
	
	

}
