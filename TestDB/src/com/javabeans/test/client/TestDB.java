package com.javabeans.test.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.javabeans.test.shared.Movie;
import com.javabeans.test.shared.MovieQuery;
import com.javabeans.test.shared.MovieQueryResult;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TestDB implements EntryPoint {

	private final MovieServiceAsync movieService = GWT
			.create(MovieService.class);

	private VerticalPanel vPanel = new VerticalPanel();
	private ScrollPanel scrollPanelTable = new ScrollPanel();
	private VerticalPanel map = new VerticalPanel();
	private FlowPanel searchMenu = new FlowPanel();

	private HorizontalPanel name = new HorizontalPanel();
	private HorizontalPanel year = new HorizontalPanel();
	private HorizontalPanel lang = new HorizontalPanel();
	private HorizontalPanel country = new HorizontalPanel();
	private HorizontalPanel genre = new HorizontalPanel();
	private HorizontalPanel search = new HorizontalPanel();

	private TextBox nameField = new TextBox();
	private TextBox yearField = new TextBox();
	private TextBox countryField = new TextBox();
	private TextBox languageField = new TextBox();
	private TextBox genreField = new TextBox();
	private Button searchButton = new Button("Search");
	private Button exportButton = new Button("Export");

	private MovieCellTable movietable = new MovieCellTable();
	private MovieQuery currentQuery = new MovieQuery();
	private AsyncDataProvider<Movie> movieTableDataProvider = new AsyncDataProvider<Movie>() {
		@Override
		protected void onRangeChanged(HasData<Movie> display) {
			updateMovies(display.getVisibleRange().getStart(), display
					.getVisibleRange().getLength());
		}
	};

	private Label titleLabel = new Label("Title");
	private Label yearLabel = new Label("Year (e.g. '1990')");
	private Label CountryLabel = new Label("Country");
	private Label LanguageLabel = new Label("Language");
	private Label GenreLabel = new Label("Genre");

	private TabLayoutPanel tabPanel = new TabLayoutPanel(2.5, Unit.EM);

	private Label addPlaceholder = new Label("Placeholder for ADD");

	// private SliderBarSimpleHorizontal mapSliderBarSimpleHorizontal = new
	// SliderBarSimpleHorizontal(8, "80", true);

	// private SliderBarSimpleHorizontal mapSliderBarSimpleHorizontal = new
	// SliderBarSimpleHorizontal(8, "80", true);

	public void onModuleLoad() {
		System.out.println("Module starts loading... ");
		vPanel.add(addPlaceholder);

		// Search Menu
		searchMenu.setWidth("100%");
		name.setStyleName("flowPanel_inline");
		year.setStyleName("flowPanel_inline");
		lang.setStyleName("flowPanel_inline");
		country.setStyleName("flowPanel_inline");
		genre.setStyleName("flowPanel_inline");
		search.setStyleName("flowPanel_inline");
		searchButton.setStyleName("flowPanel_inline");
		exportButton.setStyleName("flowPanel_inline");

		name.add(titleLabel);
		name.add(nameField);
		year.add(yearLabel);
		year.add(yearField);
		lang.add(LanguageLabel);
		lang.add(languageField);
		country.add(CountryLabel);
		country.add(countryField);
		genre.add(GenreLabel);
		genre.add(genreField);
		search.add(searchButton);

		searchMenu.add(name);
		searchMenu.add(year);
		searchMenu.add(lang);
		searchMenu.add(country);
		searchMenu.add(genre);
		searchMenu.add(search);
		searchMenu.add(exportButton);

		scrollPanelTable.add(movietable);

		// mapSliderBarSimpleHorizontal.setHeight("100px");
		// map.add(mapSliderBarSimpleHorizontal);

		tabPanel.setHeight("800px");
		tabPanel.setAnimationDuration(1000);
		tabPanel.getElement().getStyle().setMarginBottom(10.0, Unit.PX);

		tabPanel.add(scrollPanelTable, "table");
		tabPanel.add(new ScrollPanel(map), "map");

		vPanel.add(searchMenu);
		vPanel.add(tabPanel);
		// vPanel.add(mapSliderBarSimpleHorizontal);

		// tabPanel.addSelectionHandler(new SelectionHandler<Integer>() {
		// public void onSelection(SelectionEvent<Integer> event) {
		// // Let the user know what they just did.
		//
		// if (event.getSelectedItem() == 1) {
		//
		// Window.alert("You clicked tab " + event.getSelectedItem());
		// }
		//
		// }
		// });

		// mapSliderBarSimpleHorizontal.addBarValueChangedHandler(new
		// BarValueChangedHandler() {
		// public void onBarValueChanged(BarValueChangedEvent event) {
		// //valueBox.setValue("" + event.getValue());
		// }
		// });

		searchButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				searchMovies();
			}
		});
		searchAtEnter(nameField);
		searchAtEnter(yearField);
		searchAtEnter(countryField);
		searchAtEnter(languageField);
		searchAtEnter(genreField);
		movieTableDataProvider.addDataDisplay(movietable.getTable());

		WorldMap worldMap = new WorldMap();
		map.add(worldMap);

		RootPanel.get().add(vPanel);

		exportButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				String exportUrl = Window.Location.createUrlBuilder()
					.setPath("exportcsv")
					.setParameter("name", currentQuery.getName())
					.setParameter("year", currentQuery.getYear())
					.setParameter("country", currentQuery.getCountry())
					.setParameter("language", currentQuery.getLanguage())
					.setParameter("genre", currentQuery.getGenre())
					.buildString();
				Window.Location.replace(exportUrl);
			}
		});

	}

	private void searchMovies() {
		currentQuery.setName(nameField.getText());
		currentQuery.setYear(yearField.getText());
		currentQuery.setCountry(countryField.getText());
		currentQuery.setLanguage(languageField.getText());
		currentQuery.setGenre(genreField.getText());
		updateMovies(0, movietable.getTable().getVisibleRange().getLength());
	}

	private void updateMovies(int start, int length) {
		currentQuery.setOffset(start);
		currentQuery.setLimit(length);
		movieService.getMoviesFromServer(currentQuery,
				new AsyncCallback<MovieQueryResult>() {
					public void onFailure(Throwable caught) {
						System.out.println("Failed: " + caught.toString());
					}

					public void onSuccess(MovieQueryResult result) {
						System.out.println("Success: return "
								+ result.getMovies().size() + " of "
								+ result.getTotalMovieCount() + " movies.");

						if (result.getMovies().size() == 0) {
							Window.alert("No movies found that match selected criteria");
						}
						movieTableDataProvider.updateRowData(
								currentQuery.getOffset(), result.getMovies());
						movieTableDataProvider.updateRowCount(
								result.getTotalMovieCount(), true);
					}
				});
	}

	private void searchAtEnter(TextBox filter) {
		filter.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					searchMovies();
				}
			}
		});
	}
}
