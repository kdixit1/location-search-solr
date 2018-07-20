package com.leonlabs.search.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ErrorView{

	/**
	 * 
	 */
	private String errorCode;

	private String errorDescription;
	
}
