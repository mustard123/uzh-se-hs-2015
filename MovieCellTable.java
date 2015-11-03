package com.tabletest.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;



public  class MovieCellTable extends Composite {
	

	 CellTable<Movie> table = new CellTable<Movie>();
	 VerticalPanel vPanel = new VerticalPanel();
		List<Movie> list;
		SimplePager pager;
		List<Movie> mlist = new ArrayList<Movie>();
		
	
	
		
	
	
		TextColumn<Movie> wikiMovieIDColumn = new TextColumn<Movie>() {
		      @Override
		      public String getValue(Movie object) {
		        return object.getWikiMovieID();
		      }
				};
				
		    
		TextColumn<Movie> freebaseMovieIDColumn = new TextColumn<Movie>() {
			      @Override
			      public String getValue(Movie object) {
			        return object.getFreebaseMovieID();
			      }
			    };
			    
		
	 TextColumn<Movie> titleColumn = new TextColumn<Movie>() {
				  @Override
				  public String getValue(Movie object) {
					  return object.getTitle();
				  }
				};
				
	    
	  TextColumn<Movie> releaseDateColumn = new TextColumn<Movie>() {
		      	  @Override
		      	  public String getValue(Movie object) {
		      		  return object.getYear();
		      	  }
		    	};
		    	
		    	
		TextColumn<Movie> boxOfficeRevenueColumn = new TextColumn<Movie>() {
				@Override
				public String getValue(Movie object) {
					return object.getBoxOfficeRevenue();
				  }
				};
				    	
		    
		TextColumn<Movie> runtimeColumn = new TextColumn<Movie>() {
			      @Override
			      public String getValue(Movie object) {
			    	  return object.getLength();
			      }
			    };
			    
			    
			    
				    
		TextColumn<Movie> languagesColumn = new TextColumn<Movie>() {
				  @Override
				  public String getValue(Movie object) {
					  return object.getLangAsString();
					  
				  }
				};
				
	    
	  
		TextColumn<Movie> CountriesColumn = new TextColumn<Movie>() {
				  @Override
				  public String getValue(Movie object) {
					 
						return object.getCountrAsString();
											  
				  }
				};
					
				
		TextColumn<Movie> genresColumn = new TextColumn<Movie>() {
				  @Override
				  public String getValue(Movie object) {
					return object.getGenreAsString();
						  
				  }
				};
		
				
				
				
				
				
				
	public MovieCellTable(List<Movie> newlist){	
		this.mlist = newlist;
		
		initWidget(this.vPanel);
		
		
		
		table.addColumn(wikiMovieIDColumn, "Wikipedia movie ID");
		table.addColumn(freebaseMovieIDColumn, "Freebase movie ID"); 
		table.addColumn(titleColumn, "Name");
		table.addColumn(releaseDateColumn, "Movie release date");
		table.addColumn(boxOfficeRevenueColumn, "Movie box office revenue");
		table.addColumn(runtimeColumn, "Movie runtime");
		table.addColumn(languagesColumn, "Movie languages");
		table.addColumn(CountriesColumn, "Movie countries");
		table.addColumn(genresColumn, "Movie genres");
		
		wikiMovieIDColumn.setSortable(true);
		freebaseMovieIDColumn.setSortable(true);
		titleColumn.setSortable(true);
		releaseDateColumn.setSortable(true);
		boxOfficeRevenueColumn.setSortable(true);
		runtimeColumn.setSortable(true);
		
		
		/*List<Movie> Movies = Arrays.asList(
				new Movie("1", "asdf", "aaaaaaaaaaaa", "20.01.2015", "234"," this ims s", null , null, null),
				new Movie("2", "asdf", "sadfsdf", "2000", "96"," this ims s", null , null, null),
				new Movie("m3", "asdf", "sadfsdf", "02.03.2000", "51"," this ims s", null , null, null),
				new Movie("4", "asdf", "sadfsdf", "1990", "7878778"," this ims s", null , null, null),
				new Movie("m5", "asdf", "sabbbbbbbbdfsdf", "01.01.1990", "334534533"," this ims s", null , null, null),
				new Movie("6", "asdf", "sadfsdf", "1.1.1990", "4556466"," this ims s", null , null, null),
				new Movie("m23", "aaa", "sadddddddddddfsdf", "01.01.2015", "36099"," this ims s", null , null, null),
				new Movie("m8", "asdf", "sadfsdf", "pipap", "55"," this ims s", null , null, null),
				new Movie("9", "asdf", "sadfsdf", "pipap", "8"," this ims s", null , null, null),
				new Movie("10", "asdf", "sadfsdf", "pipap", "900"," this ims s", null , null, null),
				new Movie("11", "asdf", "sadfsdf", "pipap", "354"," this ims s", null , null, null),
				new Movie("m55", "asdf", "sadssssssssssfsdf", "34", "78998"," this ims s", null , null, null),
				new Movie("m55", "asdf", "sadssssssssssfsdf", "22", "7765"," this ims s", null , null, null),
				new Movie("m55", "zzzz", "sadssssssssssfsdf", "567", "2366734"," this ims s", null , null, null),
				new Movie("m55", "asdfsadf", "sadssssssssssfsdf", "765", "13565246"," this ims s", null , null, null),
				new Movie("m55", "asdf", "sadssssssssssfsdf", "333", "574256213"," this ims s", null , null, null),
				new Movie("dfsg", "asdf", "sadfsdf", "pipap", "6665"," this ims s", null , null, null),
				new Movie("asf", "asdf", "sadfsdf", "pipap", "67546"," this ims s", null , null, null),
				new Movie("zzzzzzz", "asdf", "zzzzzz", "pipap", "1235534"," this ims s", null , null, null));
		*/

		
		
		/* mlist = Arrays.asList(
				 	new Movie("2", "asdf", "sadfsdf", "2000", "96"," this ims s", null , null, null),
					new Movie("m3", "asdf", "sadfsdf", "02.03.2000", "51"," this ims s", null , null, null),
					new Movie("4", "asdf", "sadfsdf", "1990", "7878778"," this ims s", null , null, null),
					new Movie("m5", "asdf", "sabbbbbbbbdfsdf", "01.01.1990", "334534533"," this ims s", null , null, null),
					new Movie("6", "asdf", "sadfsdf", "1.1.1990", "4556466"," this ims s", null , null, null),
					new Movie("m23", "aaa", "sadddddddddddfsdf", "01.01.2015", "36099"," this ims s", null , null, null));
		*/
		
	
		
		ListDataProvider<Movie> dataProvider = new ListDataProvider<Movie>();
		
		dataProvider.addDataDisplay(table);
		list = dataProvider.getList();
		for (Movie movie : mlist){
			list.add(movie);
		}
		
		table.setRowCount(list.size(),true);
		
		table.setVisibleRange(0,20);
		
		
		ListHandler<Movie> columnSortHandler = new ListHandler<Movie>(list);
		
		columnSortHandler.setComparator(titleColumn,
		        new Comparator<Movie>() {
		          public int compare(Movie o1, Movie o2) {
		            if (o1 == o2) {
		              return 0;
		            }

		          
		            if (o1 != null) {
		              return (o2 != null) ? o1.getTitle().compareTo(o2.getTitle()) : 1;
		            }
		            return -1;
		          }
		        });
		table.addColumnSortHandler(columnSortHandler);
		    
		    
		ListHandler<Movie> columnSortHandler1 = new ListHandler<Movie>(list);
		columnSortHandler1.setComparator(wikiMovieIDColumn, new AlphanumComparator1());
		table.addColumnSortHandler(columnSortHandler1);
		
		ListHandler<Movie> columnSortHandler2 = new ListHandler<Movie>(list);
		columnSortHandler2.setComparator(freebaseMovieIDColumn, new AlphanumComparator2());
		table.addColumnSortHandler(columnSortHandler2);
			    
		ListHandler<Movie> columnSortHandler3 = new ListHandler<Movie>(list);
		columnSortHandler3.setComparator(releaseDateColumn, new AlphanumComparator3());
		table.addColumnSortHandler(columnSortHandler3);
		
		ListHandler<Movie> columnSortHandler4 = new ListHandler<Movie>(list);
		columnSortHandler4.setComparator(boxOfficeRevenueColumn, new AlphanumComparator4());
		table.addColumnSortHandler(columnSortHandler4);
		
		ListHandler<Movie> columnSortHandler5 = new ListHandler<Movie>(list);
		columnSortHandler5.setComparator(runtimeColumn, new AlphanumComparator5());
		table.addColumnSortHandler(columnSortHandler5);

		    
		    
		SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
		pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
		pager.setDisplay(table);
			    
		vPanel.add(table);
		vPanel.add(pager);
		
		
		}
	


}
