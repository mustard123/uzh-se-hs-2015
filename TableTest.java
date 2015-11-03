package com.tabletest.client;

import com.tabletest.shared.FieldVerifier;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TableTest implements EntryPoint {
	
	VerticalPanel vPanel = new VerticalPanel();

	public void onModuleLoad() {
		
		String[] la;
		String[] co;
		String[] ge;
		
		la= new String[] {"english","german","french"};
		co= new String[] {"usa","germany","france","switzerland"};
		ge= new String[] {"action","thriller"};
		
		List<Movie> newlist = Arrays.asList(
				new Movie("1", "m1", "Star Wars", "20.01.2015", "234","60", la , co, ge),
				new Movie("7", "m7", "Back to the Future", "2000", "96","78", la , co, ge),
				new Movie("4", "m4", "Spiderman", "02.03.2000", "51","89", la , co, ge));
		
		
		
		
		
		MovieCellTable movietable = new MovieCellTable(newlist);
		
		vPanel.add(movietable);
		
		
		
	
		
		
		
		RootPanel.get().add(vPanel);
		
		
		
	}
}
