package com.javabeans.test.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class CountryCounter implements IsSerializable {
	private String countryName;

	private int numberOfMovies;

	public CountryCounter() {}
	
	public CountryCounter(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryName() {
		return countryName;
	}

	public int getNumberOfMovies() {
		return numberOfMovies;
	}

	public void increaseNumberOfMovies() {
		numberOfMovies++;
	}

	public void reset() {
		numberOfMovies = 0;
	}

}
