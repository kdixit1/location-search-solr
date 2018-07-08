package com.leonlabs.search.rest;

import java.math.BigDecimal;
import java.util.List;

import javax.naming.directory.SearchResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.leonlabs.search.service.SearchServiceImpl;
import com.leonlabs.search.view.CityView;
import com.leonlabs.search.view.SearchQuery;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/search/v1")
@Api(tags = { "location-search" })
public class SearchController extends AbstractRestHandler {

	@Autowired
	private SearchServiceImpl searchService;
	
	private static final Logger log = LoggerFactory.getLogger(SearchController.class);

	@RequestMapping(value = "suggest_cities", method = RequestMethod.POST, produces = { "application/json" }, consumes = { "application/json" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "All cities with matching search criteria", notes = "Maximum of 10 cities will be populated at a time", response = SearchResult.class)
	public ResponseEntity<List<CityView>> getAllCitiesWithRequestedCriteria(
			@ApiParam(value = "The module name.", required = true) @PathVariable("name") SearchQuery searchQuery,
			@RequestParam("coverage_amount") BigDecimal coverageAmount) throws Exception {
		List<CityView> searchResult;
		try {
			searchResult = searchService.getCities(searchQuery);
		} catch (Exception e) {
			log.error("Exception occured while fetching search results for city - "+ searchQuery.getFreeText());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(searchResult, HttpStatus.OK);
	}
	
}
