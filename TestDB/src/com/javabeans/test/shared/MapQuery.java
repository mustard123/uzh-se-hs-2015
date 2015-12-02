package com.javabeans.test.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MapQuery implements IsSerializable{
	
	private Integer year;
	private boolean excludeUs;
	
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public boolean isExcludeUs() {
		return excludeUs;
	}

	public void setExcludeUs(boolean excludeUs) {
		this.excludeUs = excludeUs;
	}
}
