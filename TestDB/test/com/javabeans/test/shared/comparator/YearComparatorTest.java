package com.javabeans.test.shared.comparator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.javabeans.test.shared.Movie;


public class YearComparatorTest {

	@Test
	public void testCompareWithNullNull() {
		Movie movie1 = new Movie();
		Movie movie2 = new Movie();
		movie1.setYear(null);
		movie2.setYear(null);

		int result = new YearComparator().compare(movie1, movie2);

		assertEquals(0, result);
	}
	
	@Test
	public void testCompareWithNullYear(){
		Movie movie1 = new Movie();
		Movie movie2 = new Movie();
		movie1.setYear(null);
		movie2.setYear(2000);

		int result = new YearComparator().compare(movie1, movie2);

		assertEquals(-1, result);
	}
	
	@Test
	public void testCompareWithYearNull(){
		Movie movie1 = new Movie();
		Movie movie2 = new Movie();
		movie1.setYear(2000);
		movie2.setYear(null);

		int result = new YearComparator().compare(movie1, movie2);

		assertEquals(1, result);
	}
	
	@Test
	public void testCompareWithYear(){
		Movie movie1 = new Movie();
		Movie movie2 = new Movie();
		movie1.setYear(2000);
		movie2.setYear(2001);

		int result = new YearComparator().compare(movie1, movie2);

		assertEquals(-1, result);
	}
	
	@Test
	public void testCompareWithYear2(){
		Movie movie1 = new Movie();
		Movie movie2 = new Movie();
		movie1.setYear(2001);
		movie2.setYear(2000);

		int result = new YearComparator().compare(movie1, movie2);

		assertEquals(1, result);
	}
	
	@Test
	public void testCompareWithSameYear(){
		Movie movie1 = new Movie();
		Movie movie2 = new Movie();
		movie1.setYear(2001);
		movie2.setYear(2001);

		int result = new YearComparator().compare(movie1, movie2);

		assertEquals(0, result);
	}

}
