package com.javabeans.test.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gwt.thirdparty.guava.common.base.Joiner;
import com.javabeans.test.server.bla.Database;
import com.javabeans.test.server.bla.DatabaseFactory;
import com.javabeans.test.shared.Movie;
import com.javabeans.test.shared.MovieQuery;
import com.javabeans.test.shared.SortColumn;

public class ExportCsvServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final Database database = DatabaseFactory.getInstance();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		MovieQuery query = new MovieQuery();
		query.setName(request.getParameter("name"));
		query.setYear(request.getParameter("year"));
		query.setCountry(request.getParameter("country"));
		query.setLanguage(request.getParameter("language"));
		query.setGenre(request.getParameter("genre"));
		query.setAscending(true);
		query.setSortColumn(SortColumn.TITLE);

		List<Movie> exportList = database.query(query).getMovies();

		if (exportList.size() == 0) {
			response.setContentType("text/html");
			out.println("<script type=\"text/javascript\">");
			out.println("alert('No movies to export');");
			out.println("</script>");

			return;

		}
		response.setContentType("text/csv; charset=utf-8");

		response.setHeader("Content-Disposition", "attachment; fileName="
				+ "MovieExportList" + ".csv");

		Joiner listJoiner = Joiner.on('/');

		for (Movie movie : exportList) {
			out.println(String.valueOf(movie.getWikiMovieID()) + ","
					+ movie.getFreebaseMovieID() + ","
					+ movie.getTitle() + ","
					+ movie.getYear() + ","
					+ movie.getBoxOfficeRevenue() + ","
					+ movie.getLength() + ","
					+ listJoiner.join(movie.getLanguages()) + ","
					+ listJoiner.join(movie.getCountries()) + ","
					+ listJoiner.join(movie.getGenres()));
		}

		out.close();
	}
}