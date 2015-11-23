package com.javabeans.test.server.bla;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;

public class TsvFileDataProviderTest {

	@Test
	public void testParseMovies() {
		String input = "1	a	Test movie	2001	1	1.0	{}	{}	{}\n"
				+ "2	a	Test film	2001	1	1.0	{}	{}	{}\n";
		InputStream inputStream = new ByteArrayInputStream(input.getBytes());

		FileDataProvider dataProvider = new TsvFileDataProvider(inputStream);

		assertEquals(2, dataProvider.getMovies().size());
	}
}
