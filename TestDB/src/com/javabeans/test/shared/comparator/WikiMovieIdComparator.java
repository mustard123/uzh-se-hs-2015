package com.javabeans.test.shared.comparator;

import java.util.Comparator;

import com.javabeans.test.shared.Movie;

public class WikiMovieIdComparator implements Comparator<Movie>{
	@Override
	public int compare(Movie movie1, Movie movie2) {
		if(movie1.getWikiMovieID() == null) {
			if(movie2.getWikiMovieID() == null) {
				return 0;
			}
			return -1;
		}
		if(movie2.getWikiMovieID() == null) {
			return 1;
		}
		return Long.compare(movie1.getWikiMovieID(), movie2.getWikiMovieID());
	}
}
