package com.javabeans.test.shared.comparator;

import java.util.Comparator;

import com.javabeans.test.shared.Movie;

public class YearComparator implements Comparator<Movie>{
	@Override
	public int compare(Movie movie1, Movie movie2) {
		if(movie1.getYear() == null) {
			if(movie2.getYear() == null) {
				return 0;
			}
			return -1;
		}
		if(movie2.getYear() == null) {
			return 1;
		}
		return Integer.compare(movie1.getYear(), movie2.getYear());
	}
}
