package com.javabeans.test.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MapQuery implements IsSerializable{
	
	private String year;
	private boolean excludeUs;
	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public boolean isExcludeUs() {
		return excludeUs;
	}

	public void setExcludeUs(boolean excludeUs) {
		this.excludeUs = excludeUs;
	}
}
