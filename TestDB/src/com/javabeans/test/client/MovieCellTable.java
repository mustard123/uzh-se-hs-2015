package com.javabeans.test.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.javabeans.test.shared.Movie;
import com.javabeans.test.shared.MovieQuery;
import com.javabeans.test.shared.SortColumn;

public class MovieCellTable extends Composite {

	CellTable<Movie> table = new CellTable<Movie>();
	VerticalPanel vPanel = new VerticalPanel();
	SimplePager pager;

	TextColumn<Movie> wikiMovieIDColumn = new TextColumn<Movie>() {
		@Override
		public String getValue(Movie object) {
			return String.valueOf(object.getWikiMovieID());
		}
	};

	TextColumn<Movie> freebaseMovieIDColumn = new TextColumn<Movie>() {
		@Override
		public String getValue(Movie object) {
			return object.getFreebaseMovieID();
		}
	};

	TextColumn<Movie> titleColumn = new TextColumn<Movie>() {
		@Override
		public String getValue(Movie object) {
			return object.getTitle();
		}
	};

	TextColumn<Movie> releaseDateColumn = new TextColumn<Movie>() {
		@Override
		public String getValue(Movie object) {
			return object.getYear();
		}
	};

	TextColumn<Movie> boxOfficeRevenueColumn = new TextColumn<Movie>() {
		@Override
		public String getValue(Movie object) {
			return object.getBoxOfficeRevenue();
		}
	};

	TextColumn<Movie> runtimeColumn = new TextColumn<Movie>() {
		@Override
		public String getValue(Movie object) {
			return object.getLength();
		}
	};

	TextColumn<Movie> languagesColumn = new TextColumn<Movie>() {
		@Override
		public String getValue(Movie object) {
			return object.getLangAsString();

		}
	};

	TextColumn<Movie> CountriesColumn = new TextColumn<Movie>() {
		@Override
		public String getValue(Movie object) {

			return object.getCountrAsString();

		}
	};

	TextColumn<Movie> genresColumn = new TextColumn<Movie>() {
		@Override
		public String getValue(Movie object) {
			return object.getGenreAsString();

		}
	};

	public MovieCellTable() {

		initWidget(this.vPanel);

		table.addColumn(wikiMovieIDColumn, "Wikipedia movie ID");
		table.addColumn(freebaseMovieIDColumn, "Freebase movie ID");
		table.addColumn(titleColumn, "Title");
		table.addColumn(releaseDateColumn, "Release date");
		table.addColumn(boxOfficeRevenueColumn, "Box office revenue");
		table.addColumn(runtimeColumn, "Runtime");
		table.addColumn(languagesColumn, "Languages");
		table.addColumn(CountriesColumn, "Countries");
		table.addColumn(genresColumn, "Genres");

		wikiMovieIDColumn.setSortable(true);
		wikiMovieIDColumn.setDataStoreName(SortColumn.WIKI_MOVIE_ID.name());
		freebaseMovieIDColumn.setSortable(true);
		freebaseMovieIDColumn.setDataStoreName(SortColumn.FREEBASE_MOVIE_ID.name());
		titleColumn.setSortable(true);
		titleColumn.setDataStoreName(SortColumn.TITLE.name());
		releaseDateColumn.setSortable(true);
		releaseDateColumn.setDataStoreName(SortColumn.RELEASE_DATE.name());
		boxOfficeRevenueColumn.setSortable(true);
		boxOfficeRevenueColumn.setDataStoreName(SortColumn.BOX_OFFICE_REVENUE.name());
		runtimeColumn.setSortable(true);
		runtimeColumn.setDataStoreName(SortColumn.RUNTIME.name());

		table.setVisibleRange(0, 20);

		table.getColumnSortList().push(titleColumn);

		SimplePager.Resources pagerResources = GWT
				.create(SimplePager.Resources.class);
		pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0,
				true);
		pager.setDisplay(table);

		vPanel.add(table);
		vPanel.add(pager);

	}
	
	public CellTable<Movie> getTable() {
		return table;
	}
}
