package com.tabletest.client;

public class Movie {
	
	
	private String wikiMovieID;
	private String freebaseMovieID;
	private String title;
	
	private String[] countries;
	private String[] languages;
	private String[] genres;
	
	private String length;
	private String year;
	private String boxOfficeRevenue;



	public Movie(String wikiMovieID, String freebaseMovieID, String title, String year, String boxOfficeRevenue,String length, String [] languages, String[] countries,  String[] genres){
		
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



	public String getWikiMovieID() {
		return wikiMovieID;
	}



	public void setWikiMovieID(String wikiMovieID) {
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



	public String[] getCountries() {
		return countries;
	}



	public void setCountries(String[] countries) {
		this.countries = countries;
	}



	public String[] getLanguages() {
		return languages;
	}



	public void setLanguages(String[] languages) {
		this.languages = languages;
	}



	public String[] getGenres() {
		return genres;
	}



	public void setGenres(String[] genres) {
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
	
	public String getLangAsString(){
		
		String languageAsString="";
		for(int i=0; i<languages.length; i++){
		
			languageAsString=languageAsString+ languages[i]+" ";
		}
		return languageAsString;
	}
	
	public String getCountrAsString(){
		
		String countriesAsString="";
		for(int i=0; i<countries.length; i++){
		
			countriesAsString=countriesAsString+ countries[i]+" ";
		}
		return countriesAsString;
	}
	
	public String getGenreAsString(){
		
		String genreAsString="";
		for(int i=0; i<genres.length; i++){
		
			genreAsString=genreAsString+ genres[i]+" ";
		}
		return genreAsString;
	}




	
	


}