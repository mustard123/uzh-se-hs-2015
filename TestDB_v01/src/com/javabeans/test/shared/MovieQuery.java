package com.javabeans.test.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MovieQuery implements IsSerializable {

	private String name;
	private Long wikiMovieId;
	private String year;
	private String country;
	private String language;
	private String genre;
	// TODO add other fields
	// yearFrom
	// yearTo
	// language
	// country
	// etc.

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Long getWikiMovieId() {
		return wikiMovieId;
	}
	
	public void setWikiMovieId(Long wikiMovieId) {
		this.wikiMovieId = wikiMovieId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}



}
