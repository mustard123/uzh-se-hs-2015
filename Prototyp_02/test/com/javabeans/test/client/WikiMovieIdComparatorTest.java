package com.javabeans.test.client;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.javabeans.test.shared.Movie;

public class WikiMovieIdComparatorTest {

	@Test
	public void testCompare() {
		Movie movie1 = new Movie();
		Movie movie2 = new Movie();
		movie1.setWikiMovieID(null);
		movie2.setWikiMovieID(null);

		int result = new WikiMovieIdComparator().compare(movie1, movie2);

		assertEquals(0, result);
	}

	// test if output is 1 when Movie m2 has no WikiMovieID but m1 does
	@Test
	public void wikiMovieIDComparatorTest1() {

		Movie m1 = new Movie();
		Movie m2 = new Movie();
		Long l1 = new Long(10);

		m1.setWikiMovieID(l1);
		m2.setWikiMovieID(null);

		int output = new WikiMovieIdComparator().compare(m1, m2);
		assertEquals(1, output);
	}

	// test if output is -1 when Movie m1 has no WikiMovieID but m2 does

	@Test
	public void wikiMovieIDComparatorTest2() {

		Movie m1 = new Movie();
		Movie m2 = new Movie();
		Long l2 = new Long(20);

		m1.setWikiMovieID(null);
		m2.setWikiMovieID(l2);

		int output = new WikiMovieIdComparator().compare(m1, m2);
		assertEquals(-1, output);
	}

	// test if output is 0 when Movie m1 and m2 have no WikiMovieID

	@Test
	public void wikiMovieIDComparatorTest3() {

		Movie m1 = new Movie();
		Movie m2 = new Movie();

		m1.setWikiMovieID(null);
		m2.setWikiMovieID(null);

		int output = new WikiMovieIdComparator().compare(m1, m2);
		assertEquals(0, output);
	}

	// test if output is 1 when Movie m1 WikiMovieID > m2 WikiMovieID

	@Test
	public void wikiMovieIDComparatorTest4() {

		Movie m1 = new Movie();
		Movie m2 = new Movie();
		Long l1 = new Long(10);
		Long l2 = new Long(5);

		m1.setWikiMovieID(l1);
		m2.setWikiMovieID(l2);

		int output = new WikiMovieIdComparator().compare(m1, m2);
		assertEquals(1, output);
	}

	// test if output is -1 when Movie m1 WikiMovieID < m2 WikiMovieID

	@Test
	public void wikiMovieIDComparatorTest5() {

		Movie m1 = new Movie();
		Movie m2 = new Movie();
		Long l1 = new Long(5);
		Long l2 = new Long(10);

		m1.setWikiMovieID(l1);
		m2.setWikiMovieID(l2);

		int output = new WikiMovieIdComparator().compare(m1, m2);
		assertEquals(-1, output);
	}

	// test if output is 0 when Movie m1 WikiMovieID == m2 WikiMovieID

	@Test
	public void wikiMovieIDComparatorTest6() {

		Movie m1 = new Movie();
		Movie m2 = new Movie();
		Long l1 = new Long(5);
		Long l2 = new Long(5);

		m1.setWikiMovieID(l1);
		m2.setWikiMovieID(l2);

		int output = new WikiMovieIdComparator().compare(m1, m2);
		assertEquals(0, output);
	}

	// TODO cover all cases

}
