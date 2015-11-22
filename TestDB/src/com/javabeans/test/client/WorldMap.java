package com.javabeans.test.client;

import com.google.gwt.core.client.EntryPoint;
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
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.javabeans.test.shared.Movie;

public class WorldMap extends Widget{

	private Element container;
	
	
	public WorldMap()
	{
		container = DOM.createDiv();
		container.setId("worldMap");
		
		setElement(container);
		
	}
	
	@Override
	protected void onLoad()
	{
		super.onLoad();
		createWorldMap();
	}
	
	
	
	public static native void createWorldMap() 
	/*-{
		

		var data = $wnd.google.visualization.arrayToDataTable([
          ['Country', 'Popularity'],
          ['Germany', 200],
          ['United States', 300],
          ['Brazil', 400],
          ['Canada', 500],
          ['France', 600],
          ['RU', 700]
        ]);

        var options = {};
        options['dataMode'] = 'regions';

        var container = $wnd.document.getElementById('worldMap');
        
        
        var geomap = new $wnd.google.visualization.GeoMap(container);
        
        options['height'] = '777';
        options['width'] = '1400';

        geomap.draw(data, options);

	}-*/;
	

}





