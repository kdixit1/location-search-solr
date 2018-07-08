package com.leonlabs.search.view;

import lombok.Data;

@Data
public class SearchQuery {
	
	private String freeText;
	
	private Integer startIndex;
	
	private Integer maxResult;
	
}
