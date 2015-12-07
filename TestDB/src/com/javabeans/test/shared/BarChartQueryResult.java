package com.javabeans.test.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class BarChartQueryResult implements IsSerializable {

	/**
	 * The slice of the list of movies that matched the given query. The slice
	 * is defined by {@link MovieQuery#getOffset()} and
	 * {@link MovieQuery#getLimit()}.
	 */
	private int[] movieLengthList=new int[10];
	/**
	 * The total amount of movies that matched the query.
	 */
	private int totalMovieCount;

	public int[] getMovieLengthList()
	{
		return movieLengthList;
	}
	
	public void setMovieLengthList(int[] movieLengthList)
	{
		this.movieLengthList = movieLengthList;
	}

	public int getTotalMovieCount() {
		return totalMovieCount;
	}

	public void setTotalMovieCount(int totalMovieCount) {
		this.totalMovieCount = totalMovieCount;
	}
}
