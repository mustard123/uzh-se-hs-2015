package com.javabeans.test.client;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.javabeans.test.shared.Movie;

public class MovieCellTable extends Composite {

	CellTable<Movie> table = new CellTable<Movie>();
	VerticalPanel vPanel = new VerticalPanel();
	List<Movie> list;
	SimplePager pager;
	List<Movie> mlist = new ArrayList<Movie>();
	private ListDataProvider<Movie> dataProvider;

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
		this.mlist = new ArrayList<>();

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
		freebaseMovieIDColumn.setSortable(true);
		titleColumn.setSortable(true);
		releaseDateColumn.setSortable(true);
		boxOfficeRevenueColumn.setSortable(true);
		runtimeColumn.setSortable(true);

		dataProvider = new ListDataProvider<Movie>();

		dataProvider.addDataDisplay(table);
		list = dataProvider.getList();
		for (Movie movie : mlist) {
			list.add(movie);
		}

		table.setRowCount(list.size(), true);

		table.setVisibleRange(0, 20);

		ListHandler<Movie> columnSortHandler = new ListHandler<Movie>(list);

		columnSortHandler.setComparator(titleColumn, new Comparator<Movie>() {
			public int compare(Movie o1, Movie o2) {
				if (o1 == o2) {
					return 0;
				}

				if (o1 != null) {
					return (o2 != null) ? o1.getTitle()
							.compareTo(o2.getTitle()) : 1;
				}
				return -1;
			}
		});
		table.addColumnSortHandler(columnSortHandler);

		ListHandler<Movie> columnSortHandler1 = new ListHandler<Movie>(list);
		columnSortHandler1.setComparator(wikiMovieIDColumn, new WikiMovieIdComparator());
		table.addColumnSortHandler(columnSortHandler1);

		ListHandler<Movie> columnSortHandler2 = new ListHandler<Movie>(list);
		columnSortHandler2.setComparator(freebaseMovieIDColumn,
				new AlphanumComparator2());
		table.addColumnSortHandler(columnSortHandler2);

		ListHandler<Movie> columnSortHandler3 = new ListHandler<Movie>(list);
		columnSortHandler3.setComparator(releaseDateColumn,
				new AlphanumComparator3());
		table.addColumnSortHandler(columnSortHandler3);

		ListHandler<Movie> columnSortHandler4 = new ListHandler<Movie>(list);
		columnSortHandler4.setComparator(boxOfficeRevenueColumn,
				new AlphanumComparator4());
		table.addColumnSortHandler(columnSortHandler4);

		ListHandler<Movie> columnSortHandler5 = new ListHandler<Movie>(list);
		columnSortHandler5.setComparator(runtimeColumn,
				new AlphanumComparator5());
		table.addColumnSortHandler(columnSortHandler5);

		SimplePager.Resources pagerResources = GWT
				.create(SimplePager.Resources.class);
		pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0,
				true);
		pager.setDisplay(table);

		vPanel.add(table);
		vPanel.add(pager);

	}

	public ListDataProvider<Movie> getDataProvider() {
		return dataProvider;
	}
}
