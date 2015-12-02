package com.javabeans.test.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MapQueryResult implements IsSerializable {

	private List<CountryCounter> countryData;
	private int totalMoviesFound;
	private int totalMoviesVisualized;

	public List<CountryCounter> getCountryData() {
		return countryData;
	}

	public void setCountryData(List<CountryCounter> countryData) {
		this.countryData = countryData;
	}

	public int getTotalMoviesFound() {
		return totalMoviesFound;
	}

	public void setTotalMoviesFound(int totalMoviesFound) {
		this.totalMoviesFound = totalMoviesFound;
	}

	public int getTotalMoviesVisualized() {
		return totalMoviesVisualized;
	}

	public void setTotalMoviesVisualized(int totalMoviesVisualized) {
		this.totalMoviesVisualized = totalMoviesVisualized;
	}
}
