package com.javabeans.test.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.cellview.client.ColumnSortEvent.AsyncHandler;
import com.google.gwt.user.cellview.client.ColumnSortList.ColumnSortInfo;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.javabeans.test.shared.Movie;
import com.javabeans.test.shared.MovieQuery;
import com.javabeans.test.shared.MovieQueryResult;
import com.javabeans.test.shared.SortColumn;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TestDB implements EntryPoint {

	private int currentYear = 2015;
	private int startYear = 1888;
	private int endYear = 2015;
	
	private boolean isInMapMode = false;

	private final MovieServiceAsync movieService = GWT.create(MovieService.class);

	private HorizontalPanel hPanelSlider = new HorizontalPanel();

	private ListBox dropBox = new ListBox(true);

	private Button yearUP = new Button("year UP");
	private Button yearDOWN = new Button("year DOWN");

	private VerticalPanel vPanel = new VerticalPanel();
	private ScrollPanel scrollPanelTable = new ScrollPanel();
	private VerticalPanel map = new VerticalPanel();
	private FlowPanel searchMenu = new FlowPanel();

	private HorizontalPanel name = new HorizontalPanel();
	private HorizontalPanel year = new HorizontalPanel();
	private HorizontalPanel lang = new HorizontalPanel();
	private HorizontalPanel country = new HorizontalPanel();
	private HorizontalPanel genre = new HorizontalPanel();
	private HorizontalPanel search = new HorizontalPanel();
	private HorizontalPanel upperPanel=new HorizontalPanel();

	private TextBox nameField = new TextBox();
	private TextBox yearField = new TextBox();
	private TextBox countryField = new TextBox();
	private TextBox languageField = new TextBox();
	private TextBox genreField = new TextBox();
	private Button searchButton = new Button("Search");
	private Button exportButton = new Button("Export");
	private Button updateMapButton =new Button("Update Map");
	
	private WorldMap worldMap;
	private CheckBox toggleUSA=new CheckBox();
	private Label toggleUSAlbl=new Label("Toggle USA");
	private HorizontalPanel mapOptionsPanel=new HorizontalPanel();
	private Label totalMoviesFound = new Label();
	private Label totalMoviesVisualized = new Label();
	private VerticalPanel mapInfo=new VerticalPanel();
	private HorizontalPanel toggleUSContainer=new HorizontalPanel();
	

	private MovieCellTable movietable = new MovieCellTable();
	private MovieQuery currentQuery = new MovieQuery();
	private AsyncDataProvider<Movie> movieTableDataProvider = new AsyncDataProvider<Movie>() {
		@Override
		protected void onRangeChanged(HasData<Movie> display) {
			updateMovies(display.getVisibleRange().getStart(), display
					.getVisibleRange().getLength());
		}
	};

	private Label titleLabel = new Label("Title");
	private Label yearLabel = new Label("Year (e.g. '1990')");
	private Label CountryLabel = new Label("Country");
	private Label LanguageLabel = new Label("Language");
	private Label GenreLabel = new Label("Genre");

	private TabLayoutPanel tabPanel = new TabLayoutPanel(2.5, Unit.EM);

	private Label addPlaceholder = new Label("Placeholder for ADD");

	// private SliderBarSimpleHorizontal mapSliderBarSimpleHorizontal = new
	// SliderBarSimpleHorizontal(8, "80", true);

	// private SliderBarSimpleHorizontal mapSliderBarSimpleHorizontal = new
	// SliderBarSimpleHorizontal(8, "80", true);

	public void onModuleLoad() {
		System.out.println("Module starts loading... ");
		vPanel.add(addPlaceholder);

		// Search Menu
		searchMenu.setWidth("100%");
		upperPanel.add(searchMenu);
		
		name.setStyleName("flowPanel_inline");
		year.setStyleName("flowPanel_inline");
		lang.setStyleName("flowPanel_inline");
		country.setStyleName("flowPanel_inline");
		genre.setStyleName("flowPanel_inline");
		search.setStyleName("flowPanel_inline");
		searchButton.setStyleName("flowPanel_inline");
		exportButton.setStyleName("rightTop");

		name.add(titleLabel);
		name.add(nameField);
		year.add(yearLabel);
		year.add(yearField);
		lang.add(LanguageLabel);
		lang.add(languageField);
		country.add(CountryLabel);
		country.add(countryField);
		genre.add(GenreLabel);
		genre.add(genreField);
		search.add(searchButton);

		searchMenu.add(name);
		searchMenu.add(year);
		searchMenu.add(lang);
		searchMenu.add(country);
		searchMenu.add(genre);
		searchMenu.add(search);
		//searchMenu.add(exportButton);
		
	
		
		
		//ALL MAP UI ELEMENTS
		toggleUSAlbl.addStyleName("mapOptionsPanelContent");
		toggleUSA.addStyleName("mapOptionsPanelContent");
		toggleUSContainer.add(toggleUSAlbl);
		toggleUSContainer.add(toggleUSA);
		toggleUSContainer.setStyleName("mapOptionsPanelContent");
		
		mapInfo.add(totalMoviesFound);
		mapInfo.add(totalMoviesVisualized);
		mapInfo.addStyleName("mapOptionsPanelContent");
		
		mapOptionsPanel.add(toggleUSContainer);
		mapOptionsPanel.add(mapInfo);
		
		mapOptionsPanel.addStyleName("mapOptionsPanel");
		mapOptionsPanel.setWidth("300px");
		
		//map.add(mapOptionsPanel);

		scrollPanelTable.add(movietable);

		// mapSliderBarSimpleHorizontal.setHeight("100px");
		// map.add(mapSliderBarSimpleHorizontal);
		hPanelSlider.add(yearDOWN);
		hPanelSlider.add(dropBox);
		hPanelSlider.add(yearUP);
		//hPanelSlider.add(updateMapButton);
		updateMapButton.setStyleName("rightTop");

		
		upperPanel.add(exportButton);
		upperPanel.add(updateMapButton);
		upperPanel.add(hPanelSlider);
		upperPanel.add(mapOptionsPanel);
		
		
		for (int i = startYear; i < endYear + 1; i++) {
			String year = Integer.toString(i);

			dropBox.addItem(year);
		}

		dropBox.setSelectedIndex(endYear - startYear);

		dropBox.setHeight("50px");
		dropBox.setWidth("100px");
		yearUP.setHeight("50px");
		yearDOWN.setHeight("50px");
		//searchMenu.add(hPanelSlider);
		
		hPanelSlider.addStyleName("mapOptionsPanelContent");
		hPanelSlider.setVisible(false);
		updateMapButton.setVisible(false);
		mapOptionsPanel.setVisible(false);
		

		tabPanel.setHeight("800px");
		tabPanel.setAnimationDuration(1000);
		tabPanel.getElement().getStyle().setMarginBottom(10.0, Unit.PX);

		tabPanel.add(scrollPanelTable, "table");
		tabPanel.add(new ScrollPanel(map), "map");

		//vPanel.add(searchMenu);
		vPanel.add(upperPanel);
		vPanel.add(tabPanel);
		vPanel.setWidth("100%");
		// vPanel.add(mapSliderBarSimpleHorizontal);

		// tabPanel.addSelectionHandler(new SelectionHandler<Integer>() {
		// public void onSelection(SelectionEvent<Integer> event) {
		// // Let the user know what they just did.
		//
		// if (event.getSelectedItem() == 1) {
		//
		// Window.alert("You clicked tab " + event.getSelectedItem());
		// }
		//
		// }
		// });

		// mapSliderBarSimpleHorizontal.addBarValueChangedHandler(new
		// BarValueChangedHandler() {
		// public void onBarValueChanged(BarValueChangedEvent event) {
		// //valueBox.setValue("" + event.getValue());
		// }
		// });

		searchButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				searchMovies();
			}
		});
		searchAtEnter(nameField);
		searchAtEnter(yearField);
		searchAtEnter(countryField);
		searchAtEnter(languageField);
		searchAtEnter(genreField);
		
		movieTableDataProvider.addDataDisplay(movietable.getTable());
	    AsyncHandler columnSortHandler = new AsyncHandler(movietable.getTable());
	    movietable.getTable().addColumnSortHandler(columnSortHandler);

	    
	    
	    //Assign worldMap Attributes
		worldMap = new WorldMap(700,1200,currentQuery,movieService);
		map.add(worldMap);
		worldMap.setExcludeUS(toggleUSA);
		worldMap.setTotalMoviesFound(totalMoviesFound);
		worldMap.setTotalMoviesVisualized(totalMoviesVisualized);
		//map.add(updateMapButton);
		
		RootPanel.get().add(vPanel);
		yearUP.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(currentYear==endYear){
					currentYear=startYear;	
				}
				else{
					currentYear++;
				}
				dropBox.setSelectedIndex(currentYear - startYear);
				worldMap.getCurrentQuery().setYear(Integer.toString(currentYear));
				worldMap.UpdateWorldMap();
			}
		});

		yearDOWN.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(currentYear==startYear){
					currentYear=endYear;
				}
				else{
					currentYear--;
				}
				dropBox.setSelectedIndex(currentYear - startYear);	
				worldMap.getCurrentQuery().setYear(Integer.toString(currentYear));
				worldMap.UpdateWorldMap();
			}
		});

		
		updateMapButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				worldMap.getCurrentQuery().setYear(yearField.getText().toString());
				worldMap.getCurrentQuery().setName("");
				worldMap.getCurrentQuery().setCountry("");
				worldMap.getCurrentQuery().setLanguage("");
				worldMap.getCurrentQuery().setGenre("");
				worldMap.UpdateWorldMap();
			}
		});

		exportButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				String exportUrl = Window.Location.createUrlBuilder().setPath("exportcsv")
						.setParameter("name", currentQuery.getName()).setParameter("year", currentQuery.getYear())
						.setParameter("country", currentQuery.getCountry())
						.setParameter("language", currentQuery.getLanguage())
//						.setParameter("duration", currentQuery.getLength())
						.setParameter("genre", currentQuery.getGenre()).buildString();
				
				Window.Location.replace(exportUrl);
			}
		});
		tabPanel.addSelectionHandler(new SelectionHandler<Integer>(){
			  public void onSelection(SelectionEvent<Integer> event){
			   int tabId = event.getSelectedItem();
			   if(tabId==0)
			   {
				   isInMapMode=false;
				   toggleMapMode(); 
			   }
			   else
			   {
				   isInMapMode=true;
				   toggleMapMode();
			   }
			 }
			});

	}

	private void searchMovies() {
		currentQuery.setName(nameField.getText());
		currentQuery.setYear(yearField.getText());
		currentQuery.setCountry(countryField.getText());
		currentQuery.setLanguage(languageField.getText());
		currentQuery.setGenre(genreField.getText());
		updateMovies(0, movietable.getTable().getVisibleRange().getLength());
	}

	private void updateMovies(int start, int length) {
		currentQuery.setOffset(start);
		currentQuery.setLimit(length);
		ColumnSortInfo columnSortInfo = movietable.getTable().getColumnSortList().get(0);
		SortColumn sortColumn = SortColumn.valueOf(columnSortInfo.getColumn().getDataStoreName());
		currentQuery.setAscending(columnSortInfo.isAscending());
		currentQuery.setSortColumn(sortColumn);
		movieService.getMoviesFromServer(currentQuery,
				new AsyncCallback<MovieQueryResult>() {
					public void onFailure(Throwable caught) {
						System.out.println("Failed: " + caught.toString());
					}

					public void onSuccess(MovieQueryResult result) {
						System.out.println("Success: return "
								+ result.getMovies().size() + " of "
								+ result.getTotalMovieCount() + " movies.");

						if (result.getMovies().size() == 0) {
							Window.alert("No movies found that match selected criteria");
						}
						movieTableDataProvider.updateRowData(
								currentQuery.getOffset(), result.getMovies());
						movieTableDataProvider.updateRowCount(
								result.getTotalMovieCount(), true);
					}
				});
	}

	private void searchAtEnter(TextBox filter) {
		filter.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					if(!isInMapMode){
						searchMovies();
						currentYear=Integer.parseInt(yearField.getText());
						
					
					}
					else{
						currentYear=Integer.parseInt(yearField.getText());
						worldMap.getCurrentQuery().setYear(yearField.getText());
						worldMap.getCurrentQuery().setName("");
						
						worldMap.getCurrentQuery().setCountry("");
						worldMap.getCurrentQuery().setLanguage("");
						worldMap.getCurrentQuery().setGenre("");
						worldMap.UpdateWorldMap();
						System.out.println(Integer.parseInt(yearField.getText()));
					}
				}
			}
		});
	}
	
	

	
	private void toggleMapMode()
	{
		if(isInMapMode)
		{
			mapOptionsPanel.setVisible(true);
			hPanelSlider.setVisible(true);
			name.setVisible(false);
			lang.setVisible(false);
			country.setVisible(false);
			genre.setVisible(false);
			search.setVisible(false);
			updateMapButton.setVisible(true);
			if(yearField.getText()!=""){
				worldMap.getCurrentQuery().setYear(yearField.getText());
				worldMap.UpdateWorldMap();
			}
		}
		else
		{
			mapOptionsPanel.setVisible(false);
			hPanelSlider.setVisible(false);
			name.setVisible(true);
			lang.setVisible(true);
			country.setVisible(true);
			genre.setVisible(true);
			search.setVisible(true);
			updateMapButton.setVisible(false);
		}
	}
}
