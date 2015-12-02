package com.javabeans.test.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MovieQuery implements IsSerializable {

	private int offset;
	private Integer limit;
	private boolean ascending;
	private SortColumn sortColumn;

	private String name;
	private Long wikiMovieId;
	private Integer year;
	private String country;
	private String language;
	private String genre;

	// TODO add other fields
	// yearFrom
	// yearTo
	// language
	// country
	// etc.

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public boolean isAscending() {
		return ascending;
	}

	public void setAscending(boolean ascending) {
		this.ascending = ascending;
	}
	
	public SortColumn getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(SortColumn sortColumn) {
		this.sortColumn = sortColumn;
	}

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

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
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

	@Override
	public String toString() {
		return new StringBuilder().append(this.getClass().getSimpleName()).append("{")
			.append("name:").append(this.getName() == null ? "<null>" : this.getName())
			.append(", language:").append(this.getLanguage()  == null ? "<null>" : this.getLanguage())
			.append(", country:").append(this.getCountry()  == null ? "<null>" : this.getCountry())
			.append(", genre:").append(this.getGenre()  == null ? "<null>" : this.getGenre())
			.append(", wikiMovieId:").append(this.getWikiMovieId())
			.append(", year:").append(this.getYear())
			.append(", offset:").append(this.getOffset())
			.append(", limit:").append(this.getLimit())
			.append(", sortColumn:").append(this.getSortColumn())
			.append("}")
			.toString();
	};
}
