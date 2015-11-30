package com.javabeans.test.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.visualization.client.*;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.visualizations.GeoMap;
import com.google.gwt.visualization.client.visualizations.GeoMap.Options;
import com.google.gwt.visualization.client.visualizations.corechart.PieChart;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.javabeans.test.server.bla.Database;
import com.javabeans.test.server.bla.DatabaseFactory;
import com.javabeans.test.shared.CountryCounter;
import com.javabeans.test.shared.Movie;
import com.javabeans.test.shared.MovieQuery;
import com.javabeans.test.shared.MovieQueryResult;

public class WorldMap extends VerticalPanel {

	
	
	private DataTable dataset;
	private String[] countryNames={"United States","India","Canada", "Andorra", "United Arab Emirates", "Afghanistan", "Antigua and Barbuda", "Anguilla", "Albania", "Armenia", "Netherlands Antilles", "Angola", "Antarctica", "Argentina", "American Samoa", "Austria", "Australia", "Aruba", "Åland Islands", "Azerbaijan", "Bosnia and Herzegovina", "Barbados", "Bangladesh", "Belgium", "Burkina Faso", "Bulgaria", "Bahrain", "Burundi", "Benin", "Saint Barthélemy", "Bermuda", "Brunei", "Bolivia", "Bonaire, Sint Eustatius and Saba", "Brazil", "Bahamas", "Bhutan", "Bouvet Island", "Botswana", "Belarus", "Belize", "Cocos Islands", "The Democratic Republic Of Congo", "Central African Republic", "Congo", "Switzerland", "Cote d'Ivoire", "Cook Islands", "Chile", "Cameroon", "China", "Colombia", "Costa Rica", "Cuba", "Cape Verde", "Curacao", "Christmas Island", "Cyprus", "Czech Republic", "Germany", "Djibouti", "Denmark", "Dominica", "Dominican Republic", "Algeria", "Ecuador", "Estonia", "Egypt", "Western Sahara", "Eritrea", "Spain", "Ethiopia", "Finland", "Fiji", "Falkland Islands", "Micronesia", "Faroe Islands", "France", "Gabon", "United Kingdom", "Grenada", "Georgia", "French Guiana", "Guernsey", "Ghana", "Gibraltar", "Greenland", "Gambia", "Guinea", "Guadeloupe", "Equatorial Guinea", "Greece", "South Georgia And The South Sandwich Islands", "Guatemala", "Guam", "Guinea-Bissau", "Guyana", "Hong Kong", "Heard Island And McDonald Islands", "Honduras", "Croatia", "Haiti", "Hungary", "Indonesia", "Ireland", "Israel", "Isle Of Man", "British Indian Ocean Territory", "Iraq", "Iran", "Iceland", "Italy", "Jersey", "Jamaica", "Jordan", "Japan", "Kenya", "Kyrgyzstan", "Cambodia", "Kiribati", "Comoros", "Saint Kitts And Nevis", "North Korea", "South Korea", "Kuwait", "Cayman Islands", "Kazakhstan", "Laos", "Lebanon", "Saint Lucia", "Liechtenstein", "Sri Lanka", "Liberia", "Lesotho", "Lithuania", "Luxembourg", "Latvia", "Libya", "Morocco", "Monaco", "Moldova", "Montenegro", "Saint Martin", "Madagascar", "Marshall Islands", "Macedonia", "Mali", "Myanmar", "Mongolia", "Macao", "Northern Mariana Islands", "Martinique", "Mauritania", "Montserrat", "Malta", "Mauritius", "Maldives", "Malawi", "Mexico", "Malaysia", "Mozambique", "Namibia", "New Caledonia", "Niger", "Norfolk Island", "Nigeria", "Nicaragua", "Netherlands", "Norway", "Nepal", "Nauru", "Niue", "New Zealand", "Oman", "Panama", "Peru", "French Polynesia", "Papua New Guinea", "Philippines", "Pakistan", "Poland", "Saint Pierre And Miquelon", "Pitcairn", "Puerto Rico", "Palestine", "Portugal", "Palau", "Paraguay", "Qatar", "Reunion", "Romania", "Serbia", "Russia", "Rwanda", "Saudi Arabia", "Solomon Islands", "Seychelles", "Sudan", "Sweden", "Singapore", "Saint Helena", "Slovenia", "Svalbard And Jan Mayen", "Slovakia", "Sierra Leone", "San Marino", "Senegal", "Somalia", "Suriname", "South Sudan", "Sao Tome And Principe", "El Salvador", "Sint Maarten (Dutch part)", "Syria", "Swaziland", "Turks And Caicos Islands", "Chad", "French Southern Territories", "Togo", "Thailand", "Tajikistan", "Tokelau", "Timor-Leste", "Turkmenistan", "Tunisia", "Tonga", "Turkey", "Trinidad and Tobago", "Tuvalu", "Taiwan", "Tanzania", "Ukraine", "Uganda", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vatican", "Saint Vincent And The Grenadines", "Venezuela", "British Virgin Islands", "U.S. Virgin Islands", "Vietnam", "Vanuatu", "Wallis And Futuna", "Samoa", "Yemen", "Mayotte", "South Africa", "Zambia", "Zimbabwe"};
	private CheckBox excludeUS;
	private MovieQuery currentQuery;
	private MovieServiceAsync movieService;
	private GeoMap worldMap;
	private int mapHeight;
	private int mapWidth;

	private boolean isInitialized = false;
	private List<Movie> movieList=new ArrayList<Movie>();
	private List<CountryCounter> countryList=new ArrayList<CountryCounter>();
	
	private int totalMoviesFound;
	private int totalMoviesVisualized;
	private Label totalMoviesFoundlbl;
	private Label totalMoviesVisualizedlbl;
	
	public WorldMap(int height, int width,MovieQuery currentQuery,MovieServiceAsync movieService) {
		this.movieService = movieService;
		this.mapHeight = height;
		this.mapWidth = width;
		this.currentQuery = currentQuery;
		worldMap=new GeoMap();
		createWorldMap();
	}
	
	
	//Called at Start
	public void createWorldMap(){
		updateMovieList();
		
	}
	
	//Draws the Map with given Data
	public void DrawMap(){
		if(!isInitialized){
			worldMap = new GeoMap(dataset,getMapOptions());
			this.add(worldMap);
			isInitialized = true;
		}
		else{
			worldMap.draw(dataset,getMapOptions());
		}
	}
	
	//Creates the Dataset for the Chart, based on the List<CountryCounter
	public void createDataSet()
	{
		if(!isInitialized){
			initializeCountryList();
			//DEBUG: System.out.println("initializing CountryList Successfull");
		}
		prepareDataSet();
		//DEBUG: System.out.println("prepareDataSet Successfull");

		if(!isInitialized){
			dataset=DataTable.create();
			dataset.addColumn(ColumnType.STRING, "ADDRESS", "address");
			dataset.addColumn(ColumnType.NUMBER, "Number of Movies", "number");
			dataset.addRows(251);
			
		}
		
		int i=0;
		for(CountryCounter c :countryList)
		{
			
			dataset.setValue(i,0,c.getCountryName());
			dataset.setValue(i,1,c.getNumberOfMovies());
			i++;
			
		}
		DrawMap();
	}
	
	//Called only once, assigns to each CountryCounter the Name
 	public void initializeCountryList()
	{
		for(String countryName :countryNames){
			CountryCounter c=new CountryCounter(countryName);
			countryList.add(c);
		}
				
	}
	
 	//Iterates over all Movies in the MovieList and counts them per Country
	public void prepareDataSet(){

		System.out.println("MovieListSize: "+movieList.size());
		
		totalMoviesFound=0;
		totalMoviesVisualized=0;
		
		if(movieList.size()!=0){
			for(Movie movie : movieList){
				boolean isInList = false;
				
				totalMoviesFound++;
				
				if(excludeUS.getValue()&&movie.getCountrAsString().contains("United States")){
					/*
					 * DO NOTHING, this is to improve the maps visualization solution,if the User is not interested in US-Movies 
					 */
				}
				else
					
				{
					//handling common exceptions first
					if(movie.getCountrAsString().contains("Soviet")==true){
						for(CountryCounter c : countryList){
							if(c.getCountryName().equals("Russia")){
								c.increaseNumberOfMovies();		
								isInList = true;
								break;
								
							}
						}
						
					}
					if(movie.getCountrAsString().contains("England")==true){
						for(CountryCounter c : countryList){
							if(c.getCountryName().equals("United Kingdom")){
								c.increaseNumberOfMovies();
								isInList = true;
								break;
							}
						}
						
					}
					if(movie.getCountrAsString().contains("Scotland")==true){
						for(CountryCounter c : countryList){
							if(c.getCountryName().equals("United Kingdom")){
								c.increaseNumberOfMovies();
								isInList = true;
								break;
							}
						}
						
					}
					if(movie.getCountrAsString().contains("German")==true){
						for(CountryCounter c : countryList){
							if(c.getCountryName().equals("Germany")){
								c.increaseNumberOfMovies();
								isInList = true;
								break;
							}
						}
						
					}
	
					
					for(CountryCounter countryCounter : countryList){
						if(movie.getCountrAsString().contains(countryCounter.getCountryName())){
							countryCounter.increaseNumberOfMovies();
							isInList = true;
							break;
						}
						else{
							//DEBUG: System.out.println("Country not maching");
						}
					}
					totalMoviesVisualized++;
					if(isInList!=true){
						System.out.println("Country not in List: "+movie.getCountrAsString());
						totalMoviesVisualized--;
					}
					
				}
			}	
		}	
		else{
			System.out.println("MovieList is Empty");
		}
		totalMoviesFoundlbl.setText("Total Movies Found: "+totalMoviesFound);
		totalMoviesVisualizedlbl.setText("Total Movies Visualized: "+totalMoviesVisualized);
	}
	
	//Default Map Options
	public Options getMapOptions()
	{
		
		Options options=Options.create();
		options.setDataMode(GeoMap.DataMode.REGIONS);
		options.setHeight(mapHeight);
		options.setWidth(mapWidth);
		options.setShowLegend(true);
		options.setColors(0x99FFCC, 0x73caff, 0x794dff, 0xff26e4, 0xff0000);
		options.setRegion("world");
		
		return options;
	}
	
	@Override
	protected void onLoad() {
		super.onLoad();
		this.add(worldMap);
	}

	
	//Resets the existing Lists, starts new Query
	public void UpdateWorldMap(){
		movieList.clear();
		
		for(CountryCounter c : countryList){
			c.reset();
		}
		updateMovieList();
	}
	
	//Query to the Server
	private void updateMovieList()
	{
		currentQuery.setLimit(3000);
		
		
		if(currentQuery.getYear() == null){
			if(isInitialized){
				Window.alert("You have not chosen any criteria! By default, only movies of 1930 will be shown");
			}
			currentQuery.setYear("1930");
		}
		
		movieService.getMoviesFromServer(currentQuery, new AsyncCallback<MovieQueryResult>(){
			public void onFailure(Throwable caught){
				//DEBUG: System.out.println("Failed"+ caught.toString());
			}
			public void onSuccess(MovieQueryResult result){
				if (result.getMovies().size()==0){
					Window.alert("No movies found that match selected criteria");
				}
				//DEBUG: System.out.println("Done loading");
				movieList = result.getMovies();
				createDataSet();
			
			}
		});
		
	}

	
	
	//****GETTER AND SETTER***//
	public void setCurrentQuery(MovieQuery currentQuery){
		this.currentQuery = currentQuery;
	}
	
	public MovieQuery getCurrentQuery(){
		return currentQuery;
	}

	public Widget getWorldMap(){
		
		return worldMap;
	}
	
	public void setExcludeUS(CheckBox excludeUS){
		this.excludeUS = excludeUS;
	}
	
public void setTotalMoviesFound(Label lbl)
	{
		totalMoviesFoundlbl=lbl;
	}
	public void setTotalMoviesVisualized(Label lbl)
	{
		totalMoviesVisualizedlbl=lbl;
	}
	
	
}




