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
	private TextBox nameField = new TextBox();
	private Button searchButton = new Button("Search");
	private MovieCellTable movietable = new MovieCellTable();

	public void onModuleLoad() {

		System.out.println("Hallo :-)");

		vPanel.add(nameField);
		vPanel.add(searchButton);
		vPanel.add(movietable);
		searchButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				searchMovies();
			}
		});
		nameField.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					searchMovies();
				}
			}
		});
		RootPanel.get().add(vPanel);
	}

	private void searchMovies() {
		MovieQuery query = new MovieQuery();
		query.setName(nameField.getText());
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
}
