package com.javabeans.test.shared;

import java.io.Serializable;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Movie implements IsSerializable, Serializable {

	private static final long serialVersionUID = 1L;

	private Long wikiMovieID;
	private String freebaseMovieID;
	private String title;

	private List<String> countries;
	private List<String> languages;
	private List<String> genres;

	private String length;
	private String year;
	private String boxOfficeRevenue;
	private String information;

	public Movie() {
	}

	public Movie(String information) {
		this.setInformation(information);
	}

	public Movie(Long wikiMovieID, String freebaseMovieID, String title, String year, String boxOfficeRevenue,
			String length, List<String> languages, List<String> countries, List<String> genres) {

		this.wikiMovieID = wikiMovieID;
		this.freebaseMovieID = freebaseMovieID;
		this.title = title;
		this.countries = countries;
		this.languages = languages;
		this.genres = genres;
		this.length = length;
		this.year = year;
		this.boxOfficeRevenue = boxOfficeRevenue;
	}

	public Long getWikiMovieID() {
		return wikiMovieID;
	}

	public void setWikiMovieID(Long wikiMovieID) {
		this.wikiMovieID = wikiMovieID;
	}

	public String getFreebaseMovieID() {
		return freebaseMovieID;
	}

	public void setFreebaseMovieID(String freebaseMovieID) {
		this.freebaseMovieID = freebaseMovieID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getCountries() {
		return countries;
	}

	public void setCountries(List<String> countries) {
		this.countries = countries;
	}

	public List<String> getLanguages() {
		return languages;
	}

	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getBoxOfficeRevenue() {
		return boxOfficeRevenue;
	}

	public void setBoxOfficeRevenue(String boxOfficeRevenue) {
		this.boxOfficeRevenue = boxOfficeRevenue;
	}

	public String getLangAsString() {
		return languages.toString();
	}

	public String getCountrAsString() {
		return countries.toString();
	}

	public String getGenreAsString() {
		return genres.toString();
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information2) {
		this.information = information2;
	}

	public void printList() {
		System.out.println(getInformation());

	}
}