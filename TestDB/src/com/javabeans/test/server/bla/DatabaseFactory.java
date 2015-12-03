package com.javabeans.test.server.bla;

public class DatabaseFactory {

	private static Database instance = new FileBasedDatabase(new SerializedFileDataProvider(Thread.currentThread()
			.getContextClassLoader().getResourceAsStream("movies.serialized")));

	/**
	 * Returns the database instance.
	 * 
	 * @return the database instance
	 */
	public static Database getInstance() {
		return instance;
	}
}
