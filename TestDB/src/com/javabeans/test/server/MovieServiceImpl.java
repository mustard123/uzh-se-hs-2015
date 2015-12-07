package com.javabeans.test.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.javabeans.test.client.MovieService;
import com.javabeans.test.server.bla.Database;
import com.javabeans.test.server.bla.DatabaseFactory;
import com.javabeans.test.shared.BarChartQuery;
import com.javabeans.test.shared.BarChartQueryResult;
import com.javabeans.test.shared.CountryCounter;
import com.javabeans.test.shared.MapQuery;
import com.javabeans.test.shared.MapQueryResult;
import com.javabeans.test.shared.Movie;
import com.javabeans.test.shared.MovieQuery;
import com.javabeans.test.shared.MovieQueryResult;
import com.javabeans.test.shared.SearchFormData;

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
	public SearchFormData getSearchFormData() {
		// create lists of all countries, genres, languages and years in the database
		Set<String> countries = new HashSet<>();
		Set<String> genres = new HashSet<>();
		Set<String> languages = new HashSet<>();
		Set<Integer> years = new HashSet<>();
		// iterate over all movies and extract countries, genres, languages and year
		for(Movie movie : database.query(new MovieQuery()).getMovies()) {
			countries.addAll(movie.getCountries());
			genres.addAll(movie.getGenres());
			languages.addAll(movie.getLanguages());
			if(movie.getYear() != null) {
				years.add(movie.getYear());
			}
		}
		SearchFormData data = new SearchFormData();
		// add data to the response object
		data.getCountries().addAll(countries);
		data.getGenres().addAll(genres);
		data.getLanguages().addAll(languages);
		data.getYears().addAll(years);
		// sort values so that they appear sorted in the UI
		Collections.sort(data.getCountries());
		Collections.sort(data.getGenres());
		Collections.sort(data.getLanguages());
		Collections.sort(data.getYears());
		return data;
	}
	
	//NEW BARCHART
		@Override
		public BarChartQueryResult getBarChartDataFromServer(BarChartQuery query)
		{
			System.out.println("BarChartQueryResult reached");
			
			
			MovieQuery databaseQuery = new MovieQuery();
			
			databaseQuery.setCountry(query.getCountry());
			databaseQuery.setGenre(query.getGenre());
			databaseQuery.setLanguage(query.getLanguage());
			databaseQuery.setName(query.getName());
			databaseQuery.setYear(query.getYear());
			
			MovieQueryResult moviesResult = database.query(databaseQuery);
			List<Movie> movieList=moviesResult.getMovies();
			
			
			/*
			 * 0:	0 	-	20	min
			 * 1:	20	-	40 
			 * 2:	40	-	60
			 * 3:	60 	- 	80
			 * 4:	80	-	100
			 * 5:	100	-	120
			 * 6:	120	-	140
			 * 7: 	140 -	160
			 * 8:	160 - 	180
			 * 9:	180	-	n
			 */
			int[] movieLengthList=new int[10];
			
			//iterate over all movies, add each movie into the movieLengthList
			
			for(Movie movie : movieList)
			{
				if(!movie.getLength().isEmpty()){

					double temp = Double.parseDouble(movie.getLength());
					
					int movieLength = (int)temp;
					
					if(movieLength<20)
					{
						movieLengthList[0]++;
					}else if(movieLength < 40)
					{
						movieLengthList[1]++;
					}else if(movieLength < 60)
					{
						movieLengthList[2]++;
					}else if(movieLength < 80)
					{
						movieLengthList[3]++;
					}else if(movieLength < 100)
					{
						movieLengthList[4]++;
					}else if(movieLength < 120)
					{
						movieLengthList[5]++;
					}else if(movieLength < 140)
					{
						movieLengthList[6]++;
					}else if(movieLength < 160)
					{
						movieLengthList[7]++;
					}
					else if(movieLength < 180)
					{
						movieLengthList[8]++;
					}else if(movieLength >= 180)
					{
						movieLengthList[9]++;
					}
					
				}
				
			}
			
			BarChartQueryResult result=new BarChartQueryResult();
			result.setMovieLengthList(movieLengthList);
			System.out.println("return reached");
			
			return result;
			
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
