package com.javabeans.test.server.bla;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.javabeans.test.shared.Movie;
import com.javabeans.test.shared.MovieQuery;
import com.javabeans.test.shared.MovieQueryResult;

public class StaticFileBasedDatabaseTest {

	@Test
	public void testFilterByName() {
		String input = "1	a	Test movie	2001	1	1.0	{}	{}	{}\n"
				+ "2	a	Test film	2001	1	1.0	{}	{}	{}\n";
		InputStream inputStream = new ByteArrayInputStream(input.getBytes());
		MovieQuery query = new MovieQuery();
		query.setName("film");

		MovieQueryResult result = new StaticFileBasedDatabase(inputStream)
				.query(query);

		assertEquals(1, result.getMovies().size());
		assertEquals(Long.valueOf(2L), result.getMovies().get(0).getWikiMovieID());
	}

	@Test
	public void testFilterWithName() {
		String input = "1	a	Test movie	2001	1	1.0	{}	{}	{}\n"
				+ "2	a	Test film	2001	1	1.0	{}	{}	{}\n";
		InputStream inputStream = new ByteArrayInputStream(input.getBytes());
		MovieQuery query = new MovieQuery();
		query.setName(null);

		List<Movie> movies = new StaticFileBasedDatabase(inputStream)
				.query(query);

		assertEquals(2, movies.size());
	}

	@Test
	public void testFilterByYear() {
		String input = "1	a	Test movie	2002	1	1.0	{}	{}	{}\n"
				+ "2	a	Test film	2001	1	1.0	{}	{}	{}\n";
		InputStream inputStream = new ByteArrayInputStream(input.getBytes());
		MovieQuery query = new MovieQuery();
		query.setYear("2001");

		List<Movie> movies = new StaticFileBasedDatabase(inputStream)
				.query(query);

		assertEquals(1, movies.size());
		assertEquals("2001", movies.get(0).getYear());
	}

	@Test
	public void testFilterWithYear() {
		String input = "1	a	Test movie	2001	1	1.0	{}	{}	{}\n"
				+ "2	a	Test film	2002	1	1.0	{}	{}	{}\n";
		InputStream inputStream = new ByteArrayInputStream(input.getBytes());
		MovieQuery query = new MovieQuery();
		query.setYear(null);

		List<Movie> movies = new StaticFileBasedDatabase(inputStream)
				.query(query);

		assertEquals(2, movies.size());
	}

	@Test
	public void testFilterByLanguageCaseInsensitive() {
		String input = "1	a	Test movie	2002	1	1.0	{\"/m/02h40lc\": \"English Language\"}	{}	{}\n"
				+ "2	a	Test film	2001	1	1.0	{}	{}	{}\n";
		InputStream inputStream = new ByteArrayInputStream(input.getBytes());
		MovieQuery query = new MovieQuery();
		query.setLanguage("english");
		List<Movie> movies = new StaticFileBasedDatabase(inputStream)
				.query(query);

		assertEquals(1, movies.size());
		assertEquals(Arrays.asList("English Language"), movies.get(0)
				.getLanguages());

	}

	@Test
	public void testFilterWithLanguage() {
		String input = "1	a	Test movie	2002	1	1.0	{\"/m/02h40lc\": \"English Language\"}	{}	{}\n"
				+ "2	a	Test film	2001	1	1.0	{\"/m/02h40lc\": \"English Language\"}	{}	{}\n";
		InputStream inputStream = new ByteArrayInputStream(input.getBytes());
		MovieQuery query = new MovieQuery();
		query.setLanguage(null);

		List<Movie> movies = new StaticFileBasedDatabase(inputStream)
				.query(query);

		assertEquals(2, movies.size());
	}

	@Test
	public void testFilterByCountryCaseInsensitive() {
		String input = "975900	/m/03vyhn	Ghosts of Mars	2001-08-24	14	98.0	{\"/m/02h40lc\": \"English Language\"}	{\"/m/09c7w0\": \"United States of America\"}	{\"/m/01jfsb\": \"Thriller\"}"
				+ "975900	/m/03vyhn	Ghosts of Mars	2001-08-24	14	98.0	{\"/m/02h40lc\": \"Japanese Language\"}	{\"/m/09c7w0\": \"UK\"}	{\"/m/01jfsb\": \"Action\"}";

		InputStream inputStream = new ByteArrayInputStream(input.getBytes());
		MovieQuery query = new MovieQuery();
		query.setCountry("america");
		
		List<Movie> movies = new StaticFileBasedDatabase(inputStream)
		.query(query);
		
		assertEquals(Arrays.asList("United States of America"), movies.get(0).getCountries());
		
	}
	
	@Test 
	public void testFilterWithCountry() {
		String input = "1	a	Test movie	2002	1	1.0	{\"/m/02h40lc\": \"English Language\"}	{}	{}\n"
				+ "2	a	Test film	2001	1	1.0	{\"/m/02h40lc\": \"English Language\"}	{}	{}\n";

		InputStream inputStream = new ByteArrayInputStream(input.getBytes());
		MovieQuery query = new MovieQuery();
		query.setCountry(null);
		
		List<Movie> movies = new StaticFileBasedDatabase(inputStream)
		.query(query);
		
		assertEquals(2, movies.size());
		
	}
	@Test
	public void testFilterByGenreCaseInsensitive() {
		String input = "975900	/m/03vyhn	Ghosts of Mars	2001-08-24	14	98.0	{\"/m/02h40lc\": \"English Language\"}	{\"/m/09c7w0\": \"United States of America\"}	{\"/m/01jfsb\": \"Thriller\"}\n"
				+ "975900	/m/03vyhn	Ghosts of Mars	2001-08-24	14	98.0	{\"/m/02h40lc\": \"Japanese Language\"}	{\"/m/09c7w0\": \"UK\"}	{\"/m/01jfsb\": \"Action\"}";

		InputStream inputStream = new ByteArrayInputStream(input.getBytes());
		MovieQuery query = new MovieQuery();
		query.setGenre("action");
		
		List<Movie> movies = new StaticFileBasedDatabase(inputStream)
		.query(query);
		
		assertEquals(Arrays.asList("Action"), movies.get(0).getGenres());
		
	}
	
	@Test 
	public void testFilterWithGenre() {
		String input = "1	a	Test movie	2002	1	1.0	{\"/m/02h40lc\": \"English Language\"}	{}	{}\n"
				+ "2	a	Test film	2001	1	1.0	{\"/m/02h40lc\": \"English Language\"}	{}	{}\n";

		InputStream inputStream = new ByteArrayInputStream(input.getBytes());
		MovieQuery query = new MovieQuery();
		query.setGenre(null);
		
		List<Movie> movies = new StaticFileBasedDatabase(inputStream)
		.query(query);
		
		assertEquals(2, movies.size());
		
	}
	// TODO test all filter methods
}
