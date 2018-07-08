package com.leonlabs.search.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CityView{

	private Integer id;

	private String name;

	private Integer zipcode;
	
	private Integer countryId;

}
