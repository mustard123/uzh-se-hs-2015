import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.gwt.thirdparty.guava.common.base.Stopwatch;
import com.javabeans.test.shared.Movie;

public class Serializer {

	public static void main(String[] args) throws IOException,
			ClassNotFoundException {
		Serializer serializer = new Serializer();
		List<Movie> allMovies = new ArrayList<>();
		FileInputStream input = new FileInputStream(
				"resources/movies_80000.tsv");
		allMovies = serializer.generateMovieObjects(input);
		input.close();
		for (Movie movie : allMovies) {
			// System.out.println(movie.getTitle());
		}
		try {
			FileOutputStream fout = new FileOutputStream(
					"resources/movies_80000.serialized");
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			for (Movie movie : allMovies) {
				oos.writeObject(movie);
			}
			oos.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		List<Movie> readMovies = new ArrayList<>();
		ObjectInputStream readAll = new ObjectInputStream(new FileInputStream(
				"resources/movies_80000.serialized"));
		boolean stop = false;
		try {
			while (!stop) {
				Movie movie = (Movie) readAll.readObject();
				readMovies.add(movie);
				
			}
		} catch(EOFException e) {
			// ignore
		}
		readAll.close();
		System.out.format("Done %d", readMovies.size());

		for (Movie movie : readMovies) {
			// System.out.println(movie.getTitle());
		}
	}

	public List<Movie> generateMovieObjects(InputStream inputStream) {
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

		List<String> columns = com.google.appengine.labs.repackaged.com.google.common.base.Splitter
				.on('\t').trimResults().splitToList(line);
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
		Movie movie = new Movie(wikiMovieID, freebaseMovieID, title, year,
				boxOfficeRevenue, length, languages, countries, genres);

		return movie;
	}

	/**
	 * Parse list with format "{"key1":"value1", "key2":"value2", ...}
	 */
	private List<String> parseList(String list) {
		String cleanList = list.trim().replace("{", "").replace("}", "");
		Map<String, String> keyValues = com.google.appengine.labs.repackaged.com.google.common.base.Splitter
				.on("\", \"").omitEmptyStrings().trimResults()
				.withKeyValueSeparator(':').split(cleanList);
		List<String> values = new ArrayList<>();
		for (String value : keyValues.values()) {
			values.add(value.replace("\"", "").trim());
		}
		return values;
	}

}
