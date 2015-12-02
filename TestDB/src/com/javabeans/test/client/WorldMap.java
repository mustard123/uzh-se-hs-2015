package com.javabeans.test.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.visualizations.GeoMap;
import com.google.gwt.visualization.client.visualizations.GeoMap.Options;
import com.javabeans.test.shared.CountryCounter;
import com.javabeans.test.shared.MapQuery;
import com.javabeans.test.shared.MapQueryResult;

public class WorldMap extends VerticalPanel {

	private DataTable dataset;
	
	private MovieServiceAsync movieService;
	private GeoMap worldMap;
	private int mapHeight;
	private int mapWidth;
	private MapQuery currentQuery = new MapQuery();

	private boolean isInitialized = false;

	private Label totalMoviesFoundlbl;
	private Label totalMoviesVisualizedlbl;

	public WorldMap(int height, int width, MovieServiceAsync movieService) {
		this.movieService = movieService;
		this.mapHeight = height;
		this.mapWidth = width;
		worldMap = new GeoMap();
		createWorldMap();
	}

	// Called at Start
	private void createWorldMap() {
		updateMovieList();
	}

	// Draws the Map with given Data
	private void DrawMap() {
		if (!isInitialized) {
			worldMap = new GeoMap(dataset, getMapOptions());
			this.add(worldMap);
			isInitialized = true;
		} else {
			worldMap.draw(dataset, getMapOptions());
		}
	}

	// Creates the Dataset for the Chart, based on the List<CountryCounter
	private void createDataSet(MapQueryResult result) {
		if (!isInitialized) {
			dataset = DataTable.create();
			dataset.addColumn(ColumnType.STRING, "ADDRESS", "address");
			dataset.addColumn(ColumnType.NUMBER, "Number of Movies", "number");
			dataset.addRows(251);

		}

		int i = 0;
		for (CountryCounter c : result.getCountryData()) {
			dataset.setValue(i, 0, c.getCountryName());
			dataset.setValue(i, 1, c.getNumberOfMovies());
			i++;

		}
		totalMoviesFoundlbl.setText("Total Movies Found: " + result.getTotalMoviesFound());
		totalMoviesVisualizedlbl.setText("Total Movies Visualized: " + result.getTotalMoviesVisualized());
		DrawMap();
	}

	// Default Map Options
	private Options getMapOptions() {

		Options options = Options.create();
		options.setDataMode(GeoMap.DataMode.REGIONS);
		options.setHeight(mapHeight);
		options.setWidth(mapWidth);
		options.setShowLegend(true);
		options.setColors(0x99FFCC, 0x73caff, 0x794dff, 0xff26e4, 0xff0000);
		options.setRegion("world");

		return options;
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		this.add(worldMap);
	}

	// Resets the existing Lists, starts new Query
	public void UpdateWorldMap() {
		updateMovieList();
	}

	// Query to the Server
	private void updateMovieList() {
		if (currentQuery.getYear() == null) {
			if (isInitialized) {
				Window.alert("You have not chosen any criteria! By default, only movies of the current year will be shown");
			}
			currentQuery.setYear(2015);
		}
		
		movieService.getMapDataFromServer(currentQuery, new AsyncCallback<MapQueryResult>() {
			public void onFailure(Throwable caught) {
				// DEBUG: System.out.println("Failed"+ caught.toString());
			}

			public void onSuccess(MapQueryResult result) {
				if (result.getTotalMoviesFound() == 0) {
					Window.alert("No movies found that match selected criteria");
				}
				// DEBUG: System.out.println("Done loading");
				createDataSet(result);
			}
		});

	}

	// ****GETTER AND SETTER***//
	
	public MapQuery getCurrentQuery() {
		return currentQuery;
	}
	
	public Widget getWorldMap() {

		return worldMap;
	}

	public void setTotalMoviesFound(Label lbl) {
		totalMoviesFoundlbl = lbl;
	}

	public void setTotalMoviesVisualized(Label lbl) {
		totalMoviesVisualizedlbl = lbl;
	}
}
