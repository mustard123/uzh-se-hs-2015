package com.javabeans.test.server.bla;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.javabeans.test.shared.Movie;

public class SerializedFileDataProvider implements FileDataProvider {

	private static final Logger LOGGER = Logger.getLogger(SerializedFileDataProvider.class.getSimpleName());
	private final InputStream serializedMoviesFile;

	public SerializedFileDataProvider(InputStream serializedMoviesFile) {
		this.serializedMoviesFile = serializedMoviesFile;
	}

	@Override
	public List<Movie> getMovies() {
		List<Movie> movies = new ArrayList<>();
		LOGGER.info("Reading serialized movies.");
		try (ObjectInputStream objectStream = new ObjectInputStream(serializedMoviesFile)) {
			try {
				while (true) {
					Movie movie = (Movie) objectStream.readObject();
					movies.add(movie);

				}
			} catch (EOFException e) {
				// ignore
			}
			LOGGER.info("Read " + movies.size() + " movies.");
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return movies;
	}
}
