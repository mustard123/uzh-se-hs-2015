package com.javabeans.test.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.javabeans.test.shared.Movie;
import com.javabeans.test.shared.MovieQuery;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TestDB implements EntryPoint {

	private final MovieServiceAsync movieService = GWT
			.create(MovieService.class);
	
	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel searchMenu = new HorizontalPanel();
	private TextBox nameField = new TextBox();
	private TextBox yearField = new TextBox();
	private TextBox countryField = new TextBox();
	private TextBox languageField = new TextBox();
	private TextBox genreField = new TextBox();
	private Button searchButton = new Button("Search");
	private MovieCellTable movietable = new MovieCellTable();
	private Label titleLabel = new Label("Title");
	private Label yearLabel= new Label("Year (e.g. '1990')");
	private Label CountryLabel= new Label("Country");
	private Label LanguageLabel= new Label("Language");
	private Label GenreLabel= new Label("Genre");

	public void onModuleLoad() {

		System.out.println("Hallo :-)");
		searchMenu.add(titleLabel);
		searchMenu.add(nameField);
		searchMenu.add(yearLabel);
		searchMenu.add(yearField);
		searchMenu.add(LanguageLabel);
		searchMenu.add(languageField);
		searchMenu.add(CountryLabel);
		searchMenu.add(countryField);
		searchMenu.add(GenreLabel);
		searchMenu.add(genreField);
		searchMenu.add(searchButton);
		vPanel.add(movietable);
		
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
		RootPanel.get().add(searchMenu);
		RootPanel.get().add(vPanel);
	}

	
	private void searchMovies() {
		MovieQuery query = new MovieQuery();
		query.setName(nameField.getText());
		query.setYear(yearField.getText());
		query.setCountry(countryField.getText());
		query.setLanguage(languageField.getText());
		query.setGenre(genreField.getText());
		// TODO set other search criteria on query
		// query.setWikiMovieId(wikiMovieId);
		// etc.
		movietable.getDataProvider().getList().clear();
		movieService.getMoviesFromServer(query, new AsyncCallback<List<Movie>>() {
			public void onFailure(Throwable caught) {
				System.out.println("Failed: " + caught.toString());
			}

			public void onSuccess(List<Movie> result) {
				System.out.println("Success: " + result.size()
						+ " movies returned");
				movietable.getDataProvider().getList().addAll(result);
			}
		});
	}

	private void searchAtEnter(TextBox filter){
		filter.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					searchMovies();
				}
			}
		});
	}
}
