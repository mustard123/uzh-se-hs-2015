package com.javabeans.test.server.bla;

public class DatabaseFactory {

//	private static Database instance = new StaticFileBasedDatabase(Thread.currentThread().getContextClassLoader()
//					.getResourceAsStream("movies_80000.tsv"));
	
	private static Database instance  = new SerializedStaticFileBasedDatabase(
			Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("movies_80000.serialized"));

	public static Database getInstance() {
		return instance;
	}
}
