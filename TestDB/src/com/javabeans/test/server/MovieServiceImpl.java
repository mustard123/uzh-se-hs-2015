package com.javabeans.test.server;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.javabeans.test.client.MovieService;
import com.javabeans.test.server.bla.Database;
import com.javabeans.test.server.bla.DatabaseFactory;
import com.javabeans.test.shared.CountryCounter;
import com.javabeans.test.shared.MapQuery;
import com.javabeans.test.shared.MapQueryResult;
import com.javabeans.test.shared.Movie;
import com.javabeans.test.shared.MovieQuery;
import com.javabeans.test.shared.MovieQueryResult;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class MovieServiceImpl extends RemoteServiceServlet implements MovieService {

	// use interface in declaration so that you can change the implementation
	private Database database = DatabaseFactory.getInstance();

	public MovieQueryResult getMoviesFromServer(MovieQuery query) {
		return database.query(query);
	}

	@Override
	public MapQueryResult getMapDataFromServer(MapQuery query) {

		MovieQuery databaseQuery = new MovieQuery();
		System.out.println("Querying year " + query.getYear());
		databaseQuery.setYear(query.getYear());
		MovieQueryResult moviesResult = database.query(databaseQuery);
		List<Movie> movieList = moviesResult.getMovies();

		List<CountryCounter> countryList = initializeCountryList();

		int totalMoviesVisualized = 0;

		for (Movie movie : movieList) {
			boolean isInList = false;
			if (query.isExcludeUs() && movie.getCountrAsString().contains("United States")) {
				/*
				 * DO NOTHING, this is to improve the maps visualization
				 * solution,if the User is not interested in US-Movies
				 */
			} else
			{
				// handling common exceptions first
				if (movie.getCountrAsString().contains("Soviet") == true) {
					for (CountryCounter c : countryList) {
						if (c.getCountryName().equals("Russia")) {
							c.increaseNumberOfMovies();
							isInList = true;
							break;
	
						}
					}
	
				}
				if (movie.getCountrAsString().contains("England") == true) {
					for (CountryCounter c : countryList) {
						if (c.getCountryName().equals("United Kingdom")) {
							c.increaseNumberOfMovies();
							isInList = true;
							break;
						}
					}
	
				}
				if (movie.getCountrAsString().contains("Scotland") == true) {
					for (CountryCounter c : countryList) {
						if (c.getCountryName().equals("United Kingdom")) {
							c.increaseNumberOfMovies();
							isInList = true;
							break;
						}
					}
	
				}
				if (movie.getCountrAsString().contains("German") == true) {
					for (CountryCounter c : countryList) {
						if (c.getCountryName().equals("Germany")) {
							c.increaseNumberOfMovies();
							isInList = true;
							break;
						}
					}
	
				}
	
				for (CountryCounter countryCounter : countryList) {
					if (movie.getCountrAsString().contains(countryCounter.getCountryName())) {
						countryCounter.increaseNumberOfMovies();
						isInList = true;
						break;
					} else {
						// DEBUG: System.out.println("Country not maching");
					}
				}
				totalMoviesVisualized++;
				if (isInList != true) {
//					System.out.println("Country not in List: " + movie.getCountrAsString());
					totalMoviesVisualized--;
				}
			}
		}

		MapQueryResult result = new MapQueryResult();
		result.setTotalMoviesFound(moviesResult.getTotalMovieCount());
		result.setTotalMoviesVisualized(totalMoviesVisualized);
		result.setCountryData(countryList);

		return result;
	}

	// Called only once, assigns to each CountryCounter the Name
	public List<CountryCounter> initializeCountryList() {
		List<CountryCounter> countryList = new ArrayList<>();
		for (String countryName : countryNames) {
			CountryCounter c = new CountryCounter(countryName);
			countryList.add(c);
		}
		return countryList;
	}

	private String[] countryNames = { "United States", "India", "Canada", "Andorra", "United Arab Emirates",
			"Afghanistan", "Antigua and Barbuda", "Anguilla", "Albania", "Armenia", "Netherlands Antilles", "Angola",
			"Antarctica", "Argentina", "American Samoa", "Austria", "Australia", "Aruba", "�land Islands",
			"Azerbaijan", "Bosnia and Herzegovina", "Barbados", "Bangladesh", "Belgium", "Burkina Faso", "Bulgaria",
			"Bahrain", "Burundi", "Benin", "Saint Barth�lemy", "Bermuda", "Brunei", "Bolivia",
			"Bonaire, Sint Eustatius and Saba", "Brazil", "Bahamas", "Bhutan", "Bouvet Island", "Botswana", "Belarus",
			"Belize", "Cocos Islands", "The Democratic Republic Of Congo", "Central African Republic", "Congo",
			"Switzerland", "Cote d'Ivoire", "Cook Islands", "Chile", "Cameroon", "China", "Colombia", "Costa Rica",
			"Cuba", "Cape Verde", "Curacao", "Christmas Island", "Cyprus", "Czech Republic", "Germany", "Djibouti",
			"Denmark", "Dominica", "Dominican Republic", "Algeria", "Ecuador", "Estonia", "Egypt", "Western Sahara",
			"Eritrea", "Spain", "Ethiopia", "Finland", "Fiji", "Falkland Islands", "Micronesia", "Faroe Islands",
			"France", "Gabon", "United Kingdom", "Grenada", "Georgia", "French Guiana", "Guernsey", "Ghana",
			"Gibraltar", "Greenland", "Gambia", "Guinea", "Guadeloupe", "Equatorial Guinea", "Greece",
			"South Georgia And The South Sandwich Islands", "Guatemala", "Guam", "Guinea-Bissau", "Guyana",
			"Hong Kong", "Heard Island And McDonald Islands", "Honduras", "Croatia", "Haiti", "Hungary", "Indonesia",
			"Ireland", "Israel", "Isle Of Man", "British Indian Ocean Territory", "Iraq", "Iran", "Iceland", "Italy",
			"Jersey", "Jamaica", "Jordan", "Japan", "Kenya", "Kyrgyzstan", "Cambodia", "Kiribati", "Comoros",
			"Saint Kitts And Nevis", "North Korea", "South Korea", "Kuwait", "Cayman Islands", "Kazakhstan", "Laos",
			"Lebanon", "Saint Lucia", "Liechtenstein", "Sri Lanka", "Liberia", "Lesotho", "Lithuania", "Luxembourg",
			"Latvia", "Libya", "Morocco", "Monaco", "Moldova", "Montenegro", "Saint Martin", "Madagascar",
			"Marshall Islands", "Macedonia", "Mali", "Myanmar", "Mongolia", "Macao", "Northern Mariana Islands",
			"Martinique", "Mauritania", "Montserrat", "Malta", "Mauritius", "Maldives", "Malawi", "Mexico", "Malaysia",
			"Mozambique", "Namibia", "New Caledonia", "Niger", "Norfolk Island", "Nigeria", "Nicaragua", "Netherlands",
			"Norway", "Nepal", "Nauru", "Niue", "New Zealand", "Oman", "Panama", "Peru", "French Polynesia",
			"Papua New Guinea", "Philippines", "Pakistan", "Poland", "Saint Pierre And Miquelon", "Pitcairn",
			"Puerto Rico", "Palestine", "Portugal", "Palau", "Paraguay", "Qatar", "Reunion", "Romania", "Serbia",
			"Russia", "Rwanda", "Saudi Arabia", "Solomon Islands", "Seychelles", "Sudan", "Sweden", "Singapore",
			"Saint Helena", "Slovenia", "Svalbard And Jan Mayen", "Slovakia", "Sierra Leone", "San Marino", "Senegal",
			"Somalia", "Suriname", "South Sudan", "Sao Tome And Principe", "El Salvador", "Sint Maarten (Dutch part)",
			"Syria", "Swaziland", "Turks And Caicos Islands", "Chad", "French Southern Territories", "Togo",
			"Thailand", "Tajikistan", "Tokelau", "Timor-Leste", "Turkmenistan", "Tunisia", "Tonga", "Turkey",
			"Trinidad and Tobago", "Tuvalu", "Taiwan", "Tanzania", "Ukraine", "Uganda",
			"United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vatican",
			"Saint Vincent And The Grenadines", "Venezuela", "British Virgin Islands", "U.S. Virgin Islands",
			"Vietnam", "Vanuatu", "Wallis And Futuna", "Samoa", "Yemen", "Mayotte", "South Africa", "Zambia",
			"Zimbabwe" };
}
