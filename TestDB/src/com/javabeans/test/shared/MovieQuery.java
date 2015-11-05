package com.javabeans.test.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MovieQuery implements IsSerializable {

	private String name;
	private Long wikiMovieId;
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
}
