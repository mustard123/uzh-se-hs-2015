package com.javabeans.test.shared;

import java.util.ArrayList;
import com.google.gwt.visualization.client.*;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.visualizations.corechart.Options;
import com.google.gwt.visualization.client.visualizations.corechart.PieChart;

import java.util.List;

public class CountryCounter {
	private String countryName;
	
	private int numberOfMovies;
	
	
	public  CountryCounter(String countryName){
		this.countryName=countryName;
	}
	
	public String getCountryName(){
		return countryName;
	}
	
	public int getNumberOfMovies(){
		return numberOfMovies;
	}
	
	public  void increaseNumberOfMovies(){
		numberOfMovies++;
	}
	
	public void reset(){
		numberOfMovies=0;
	}
	

}
