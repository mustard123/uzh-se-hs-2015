package com.javabeans.test.shared;

import java.util.Collections;
import java.util.Comparator;

import com.javabeans.test.shared.comparator.AlphanumComparator2;
import com.javabeans.test.shared.comparator.AlphanumComparator3;
import com.javabeans.test.shared.comparator.AlphanumComparator4;
import com.javabeans.test.shared.comparator.AlphanumComparator5;
import com.javabeans.test.shared.comparator.WikiMovieIdComparator;

public enum SortColumn {

	BOX_OFFICE_REVENUE(new AlphanumComparator4()),
	FREEBASE_MOVIE_ID(new AlphanumComparator2()),
	RELEASE_DATE(new AlphanumComparator3()),
	RUNTIME(new AlphanumComparator5()),
	TITLE(new Comparator<Movie>() {
		public int compare(Movie o1, Movie o2) {
			if (o1 == o2) {
				return 0;
			}
			
			if (o1 != null) {
				return (o2 != null) ? o1.getTitle()
						.compareTo(o2.getTitle()) : 1;
			}
			return -1;
		}
	}),
	WIKI_MOVIE_ID(new WikiMovieIdComparator());
	
	private final Comparator<Movie> comparator;
	
	private SortColumn(Comparator<Movie> comparator) {
		this.comparator = comparator;
	}
	
	public Comparator<Movie> getComparator(boolean ascending) {
		if(ascending) {
			return comparator;
		}
		return Collections.reverseOrder(comparator);
	}
}
