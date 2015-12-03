package com.javabeans.test.server.bla;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.javabeans.test.shared.Movie;

public class TsvParserTest {
	
	
	@Test
	public void testParseWithValidInput() {
		// Arrange -> Set up test
		String input = "975900	/m/03vyhn	Ghosts of Mars	2001-08-24	14	98.0	{\"/m/02h40lc\": \"English Language\"}	{\"/m/09c7w0\": \"United States of America\"}	{\"/m/01jfsb\": \"Thriller\"}";
		InputStream inputStream = new ByteArrayInputStream(input.getBytes());

		// Act -> call method under test
		List<Movie> movies = new TsvParser().parse(inputStream);

		// Assert -> assert that the method does the right thing
		assertEquals(1, movies.size());
		assertEquals(Long.valueOf(975900L), movies.get(0).getWikiMovieID());
		assertEquals("/m/03vyhn", movies.get(0).getFreebaseMovieID());
		assertEquals("Ghosts of Mars", movies.get(0).getTitle());
		assertEquals(Integer.valueOf(2001), movies.get(0).getYear());
		assertEquals("14", movies.get(0).getBoxOfficeRevenue());
		assertEquals("98.0", movies.get(0).getLength());
		assertEquals(Arrays.asList("English Language"), movies.get(0).getLanguages());
		assertEquals(Arrays.asList("United States of America"), movies.get(0).getCountries());
		assertEquals(Arrays.asList("Thriller"), movies.get(0).getGenres());
	}
	
	@Test
	public void testEscapeUtf() {
		String input = "28382586	/m/0crffzd	Savage Play	1995		135.0	{\"/m/0dds9\": \"M\\u0101ori language\"}	{}	{}";
		InputStream inputStream = new ByteArrayInputStream(input.getBytes());
		
		List<Movie> movies = new TsvParser().parse(inputStream);

		assertEquals("M\u0101ori language", movies.get(0).getLanguages().get(0));
	}

	@Test
	public void testParseWithEmptyLists() {
		String input = "975900	/m/03vyhn	Ghosts of Mars	2001-08-24	14010832	98.0	{}	{}	{}";
		InputStream inputStream = new ByteArrayInputStream(input.getBytes());

		List<Movie> movies = new TsvParser().parse(inputStream);
		
		assertEquals(1, movies.size());
		assertTrue(movies.get(0).getLanguages().isEmpty());
		assertTrue(movies.get(0).getCountries().isEmpty());
		assertTrue(movies.get(0).getGenres().isEmpty());
	}
	
	// test that an exception is thrown
	@Test(expected=NumberFormatException.class)
	public void testParseWithInvalidWikiMovieId() {
		String input = "abc	/m/03vyhn	Ghosts of Mars	2001-08-24	14010832	98.0	{}	{}	{}";
		InputStream inputStream = new ByteArrayInputStream(input.getBytes());

		new TsvParser().parse(inputStream);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testParseWithMissingElements() {
		String input = "975900	/m/03vyhn	2001-08-24	14010832	98.0	{}	{}	{}";
		InputStream inputStream = new ByteArrayInputStream(input.getBytes());

		List<Movie> movies = new TsvParser().parse(inputStream);
	}
	
	@Test
	public void testParseWithMultipleMovies() {
		String input = "1	/m/03	Ghosts of Mars	2001	1401	98.0	{}	{}	{}\n"
		+ "2	/m/04	Ghosts of Earth	2002	1402	98.0	{}	{}	{}";
		InputStream inputStream = new ByteArrayInputStream(input.getBytes());
		
		List<Movie> movies = new TsvParser().parse(inputStream);

		assertEquals(2, movies.size());
	}
}
