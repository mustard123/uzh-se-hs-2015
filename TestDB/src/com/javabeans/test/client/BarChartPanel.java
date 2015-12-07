package com.javabeans.test.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.GeoMap;
import com.google.gwt.visualization.client.visualizations.BarChart;
import com.google.gwt.visualization.client.visualizations.BarChart.Options;
import com.javabeans.test.shared.BarChartQuery;
import com.javabeans.test.shared.BarChartQueryResult;
import com.javabeans.test.shared.MapQueryResult;
import com.javabeans.test.shared.MovieQuery;

public class BarChartPanel extends VerticalPanel {

	private DataTable dataset;
	private MovieServiceAsync movieService;
	private BarChart barChart;

	private int barChartWidth;
	private int barChartHeight;
	private BarChartQuery currentBarChartQuery;
	private BarChart.Options options;
	
	public BarChartPanel(){
		
	}

	public BarChartPanel(int height, int width, MovieServiceAsync movieService) {

		this.movieService = movieService;
		this.barChartHeight = height;
		this.barChartWidth = width;

		options = BarChart.Options.create();
		options.setTitle("Movie Duration Distribution");
		options.setWidth(width);
		options.setHeight(height);
		options.setTitleX("Amount of Movies");
		options.setTitleY("Duration in Minutes");

		DataTable defaultTable = DataTable.create();
		defaultTable.addColumn(ColumnType.STRING, "Duration");
		defaultTable.addColumn(ColumnType.NUMBER, "Value");
		defaultTable.addRows(10);

		defaultTable.setValue(0, 0, "< 20");
		defaultTable.setValue(1, 0, "20 - 40");
		defaultTable.setValue(2, 0, "40 - 60");
		defaultTable.setValue(3, 0, "60 - 80");
		defaultTable.setValue(4, 0, "80 - 100");
		defaultTable.setValue(5, 0, "100 - 120");
		defaultTable.setValue(6, 0, "120 - 140");
		defaultTable.setValue(7, 0, "140 - 160");
		defaultTable.setValue(8, 0, "160 - 180");
		defaultTable.setValue(9, 0, "> 180");

		barChart = new BarChart(defaultTable, options);
		barChart.draw(defaultTable, options);

		this.add(barChart);

		initializeDataSet();

	}

	public void onLoad() {

		super.onLoad();
		// this.add(barChart);

	}

	private void initializeDataSet() {
		// Debug
		System.out.println("initialized DataSet reached");

		dataset = DataTable.create();
		dataset.addColumn(ColumnType.STRING, "Duration", "duration");
		dataset.addColumn(ColumnType.NUMBER, "Value", "value");
		dataset.addRows(10);

		dataset.setValue(0, 0, "< 20");
		dataset.setValue(1, 0, "20 - 40");
		dataset.setValue(2, 0, "40 - 60");
		dataset.setValue(3, 0, "60 - 80");
		dataset.setValue(4, 0, "80 - 100");
		dataset.setValue(5, 0, "100 - 120");
		dataset.setValue(6, 0, "120 - 140");
		dataset.setValue(7, 0, "140 - 160");
		dataset.setValue(8, 0, "160 - 180");
		dataset.setValue(9, 0, "> 180");

		// Debug
		System.out.println("initialized Dataset Done");

	}

	public void setCurrentBarChartQuery(MovieQuery currentMovieQuery) {
		// Debug
		System.out.println("setCurrentBarChartQuery reached");

		currentBarChartQuery = new BarChartQuery();
		currentBarChartQuery.setCountry(currentMovieQuery.getCountry());
		currentBarChartQuery.setGenre(currentMovieQuery.getGenre());
		currentBarChartQuery.setLanguage(currentMovieQuery.getLanguage());
		currentBarChartQuery.setName(currentMovieQuery.getName());
		currentBarChartQuery.setYear(currentMovieQuery.getYear());
		currentBarChartQuery.setWikiMovieId(currentMovieQuery.getWikiMovieId());

		// Debug
		System.out.println("setCurrentBarChartQuery done");

		updateDataSet();
	}

	public void updateDataSet() {
		// Debug
		System.out.println("Update Dataset reached");

		movieService.getBarChartDataFromServer(currentBarChartQuery, new AsyncCallback<BarChartQueryResult>() {
			public void onFailure(Throwable caught) {
				// DEBUG:
				System.out.println("Failed" + caught.toString());
			}

			@Override
			public void onSuccess(BarChartQueryResult result) {
				if (result.getMovieLengthList() == null) {
					Window.alert("NullArray recieved from Server");
				}
				createDataSet(result);
				System.out.println("result recieved");
			}

		});
	}

	private void createDataSet(BarChartQueryResult result) {
		int[] movieLengthList = result.getMovieLengthList();

		for (int i = 0; i < 10; i++) {
			dataset.setValue(i, 1, movieLengthList[i]);
			System.out.println(movieLengthList[i]);
		}

		drawBarChart();
	}

	private void drawBarChart() {
		System.out.println("Draw Chart Reached");

		barChart.draw(dataset, options);

		System.out.println("Draw Chart generated");

	}

}
