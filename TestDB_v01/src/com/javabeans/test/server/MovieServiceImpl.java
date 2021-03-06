package com.javabeans.test.server;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.javabeans.test.client.MovieService;
import com.javabeans.test.server.bla.Database;
import com.javabeans.test.server.bla.StaticFileBasedDatabase;
import com.javabeans.test.shared.Movie;
import com.javabeans.test.shared.MovieQuery;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class MovieServiceImpl extends RemoteServiceServlet implements
		MovieService {

	// use interface in declaration so that you can change the implementation 
	private Database database = new StaticFileBasedDatabase();
	
	public List<Movie> getMoviesFromServer(MovieQuery query) {
		// Escape data from the client to avoid cross-site script
		// vulnerabilities.
//		query = escapeHtml(query);
		
		return database.query(query);
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html
	 *            the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
}
