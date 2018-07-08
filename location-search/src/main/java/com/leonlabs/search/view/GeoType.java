package com.leonlabs.search.view;

public enum GeoType{

	CITY("City"), COUNTRY("Country"), COUNTY("County"), ZIPCODE("Zipcode");

	private final String name;

	GeoType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
