package com.javabeans.test.server.bla;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.javabeans.test.shared.Movie;
import com.javabeans.test.shared.MovieQuery;
import com.javabeans.test.shared.MovieQueryResult;
import com.javabeans.test.shared.SortColumn;

public class FileBasedDatabaseTest {

	@Test
	public void testWithEmptyString() {
		Database database = new FileBasedDatabase(new SerializedFileDataProvider(Thread.currentThread()
				.getContextClassLoader().getResourceAsStream("movies.serialized")));
		MovieQuery query = new MovieQuery();
		query.setName("");
		query.setSortColumn(SortColumn.TITLE);
		query.setLanguage("");
		query.setGenre("");
		query.setCountry("");

		MovieQueryResult result = database.query(query);
		
		assertNotEquals(0, result.getTotalMovieCount());
	}
	
	@Test
	public void testFilterByName() {
		Movie movie1 = new Movie();
		Movie movie2 = new Movie();
		movie1.setTitle("Test movie");
		movie2.setTitle("Test film");

		MovieQuery query = new MovieQuery();
		query.setName("film");

		MovieQueryResult result = new FileBasedDatabase(testDataProvider(movie1, movie2)).query(query);

		assertEquals(1, result.getMovies().size());
		assertEquals("Test film", result.getMovies().get(0).getTitle());
	}

	@Test
	public void testFilterWithoutName() {
		Movie movie1 = new Movie();
		Movie movie2 = new Movie();
		movie1.setTitle("Test movie");
		movie2.setTitle("Test film");

		MovieQuery query = new MovieQuery();
		query.setName(null);

		MovieQueryResult result = new FileBasedDatabase(testDataProvider(movie1, movie2)).query(query);

		assertEquals(2, result.getMovies().size());
	}

	@Test
	public void testFilterByYear() {
		Movie movie1 = new Movie();
		Movie movie2 = new Movie();
		movie1.setYear(2002);
		movie2.setYear(2001);

		MovieQuery query = new MovieQuery();
		query.setYear(2001);

		MovieQueryResult result = new FileBasedDatabase(testDataProvider(movie1, movie2)).query(query);

		assertEquals(1, result.getMovies().size());
		assertEquals(Integer.valueOf(2001), result.getMovies().get(0).getYear());
	}

	@Test
	public void testFilterWithYear() {
		Movie movie1 = new Movie();
		Movie movie2 = new Movie();
		movie1.setYear(2001);
		movie2.setYear(2004);

		MovieQuery query = new MovieQuery();
		query.setName(null);

		MovieQueryResult result = new FileBasedDatabase(testDataProvider(movie1, movie2)).query(query);

		assertEquals(2, result.getMovies().size());
	}

	@Test
	public void testFilterByLanguageCaseInsensitive() {
		Movie movie1 = new Movie();
		Movie movie2 = new Movie();
		movie1.setLanguages(Arrays.asList("English Language"));
		movie2.setLanguages(Arrays.asList("Japanese Language"));

		MovieQuery query = new MovieQuery();
		query.setLanguage("english");

		MovieQueryResult result = new FileBasedDatabase(testDataProvider(movie1, movie2)).query(query);

		assertEquals(1, result.getMovies().size());
		assertEquals(Arrays.asList("English Language"), result.getMovies().get(0).getLanguages());
	}

	@Test
	public void testFilterWithLanguage() {
		Movie movie1 = new Movie();
		Movie movie2 = new Movie();
		movie1.setLanguages(Arrays.asList("English Language"));
		movie2.setLanguages(Arrays.asList("Japanese Language"));

		MovieQuery query = new MovieQuery();
		query.setLanguage(null);

		MovieQueryResult result = new FileBasedDatabase(testDataProvider(movie1, movie2)).query(query);

		assertEquals(2, result.getMovies().size());
	}

	@Test
	public void testFilterByCountryCaseInsensitive() {
		Movie movie1 = new Movie();
		Movie movie2 = new Movie();
		movie1.setCountries(Arrays.asList("Germany"));
		movie2.setCountries(Arrays.asList("Italy"));

		MovieQuery query = new MovieQuery();
		query.setCountry("Germany");

		MovieQueryResult result = new FileBasedDatabase(testDataProvider(movie1, movie2)).query(query);

		assertEquals(1, result.getMovies().size());
		assertEquals(Arrays.asList("Germany"), result.getMovies().get(0).getCountries());
	}

	@Test
	public void testFilterWithCountry() {
		Movie movie1 = new Movie();
		Movie movie2 = new Movie();
		movie1.setCountries(Arrays.asList("Germany"));
		movie2.setCountries(Arrays.asList("Italy"));

		MovieQuery query = new MovieQuery();
		query.setCountry(null);
		
		MovieQueryResult result = new FileBasedDatabase(testDataProvider(movie1, movie2)).query(query);

		assertEquals(2, result.getMovies().size());
	}

	@Test
	public void testFilterByGenreCaseInsensitive() {
		Movie movie1 = new Movie();
		Movie movie2 = new Movie();
		movie1.setGenres(Arrays.asList("Thriller", "Action"));
		movie2.setGenres(Arrays.asList("Romance"));

		MovieQuery query = new MovieQuery();
		query.setGenre("thriller");

		MovieQueryResult result = new FileBasedDatabase(testDataProvider(movie1, movie2)).query(query);

		assertEquals(1, result.getMovies().size());
		assertEquals(Arrays.asList("Thriller", "Action"), result.getMovies().get(0).getGenres());
	}

	@Test
	public void testFilterWithGenre() {
		Movie movie1 = new Movie();
		Movie movie2 = new Movie();
		movie1.setGenres(Arrays.asList("Thriller", "Action"));
		movie2.setGenres(Arrays.asList("Romance"));

		MovieQuery query = new MovieQuery();
		query.setGenre(null);
		
		MovieQueryResult result = new FileBasedDatabase(testDataProvider(movie1, movie2)).query(query);

		assertEquals(2, result.getMovies().size());
	}

	@Test
	public void testWithOffsetWithoutLimit() {
		MovieQuery query = new MovieQuery();
		query.setOffset(1);
		query.setLimit(null);

		MovieQueryResult result = new FileBasedDatabase(testDataProvider(new Movie(), new Movie(), new Movie()))
				.query(query);

		assertEquals(2, result.getMovies().size());
		assertEquals(3, result.getTotalMovieCount());
	}
	
	@Test
	public void testWithOffsetWithLimit() {
		MovieQuery query = new MovieQuery();
		query.setOffset(1);
		query.setLimit(1);

		MovieQueryResult result = new FileBasedDatabase(testDataProvider(new Movie(), new Movie(), new Movie()))
				.query(query);

		assertEquals(1, result.getMovies().size());
		assertEquals(3, result.getTotalMovieCount());
	}

	private static FileDataProvider testDataProvider(final Movie... movies) {
		return new FileDataProvider() {

			@Override
			public List<Movie> getMovies() {
				return Arrays.asList(movies);
			}
		};
	}
}
