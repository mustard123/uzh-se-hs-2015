import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.javabeans.test.server.bla.TsvParser;
import com.javabeans.test.shared.Movie;

public class Serializer {

	private static final Logger LOGGER = Logger.getLogger(Serializer.class.getSimpleName());
	
	private static final Path MOVIE_DIRECTORY = Paths.get("resources", "movies");
	private static final Path OUTPUT_FILE = Paths.get("resources", "movies.serialized");
	
	private static final TsvParser PARSER = new TsvParser();
	
	public static void main(String[] args) throws Exception {
		List<Movie> allMovies = new ArrayList<>();
		
		// Parse all files in MOVIE_DIRECTORY
		for(Path moviesFile : Files.newDirectoryStream(MOVIE_DIRECTORY)) {
			try(FileInputStream input = new FileInputStream(moviesFile.toFile())) {
				LOGGER.info("Parsing file " + moviesFile.getFileName());
				allMovies.addAll(PARSER.parse(input));
			}
		}
		
		// Add all Movie objects to file
		try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(OUTPUT_FILE.toFile()))) {
			LOGGER.info("Writing output to file " + OUTPUT_FILE.getFileName());
			for (Movie movie : allMovies) {
				output.writeObject(movie);
			}
		}
		LOGGER.info(allMovies.size() + " movies written to file " + OUTPUT_FILE.getFileName());
	}
}
