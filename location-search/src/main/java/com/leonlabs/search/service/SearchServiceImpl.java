package com.leonlabs.search.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leonlabs.search.entity.City;
import com.leonlabs.search.exception.ApplicationException;
import com.leonlabs.search.exception.NoResultFoundException;
import com.leonlabs.search.repository.solr.SolrSearchRepository;
import com.leonlabs.search.util.message.AppMessage;
import com.leonlabs.search.view.CityView;
import com.leonlabs.search.view.SearchQuery;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	public SolrSearchRepository solrSearchRepository;


	@Transactional(readOnly=true)
	public List<CityView> getCities(SearchQuery searchQuery) throws ApplicationException, NoResultFoundException {
		String freeTxt = searchQuery.getFreeText();
		Integer maxResult = searchQuery.getMaxResult();
		Integer startIndex = searchQuery.getStartIndex();
		List<CityView> listCity = new ArrayList<>();
		try {
			Sort sort = new Sort(new Sort.Order(Direction.ASC, "name"));
			Pageable pageable = new PageRequest(startIndex, maxResult, sort);
			Page<City> cities = solrSearchRepository.findByNameQuery(freeTxt, pageable);
			Optional.ofNullable(cities).ifPresent(city -> {
				CityView cityVO = new CityView();
				BeanUtils.copyProperties(city, cityVO);
				listCity.add(cityVO);
			});
			return listCity;
		} catch (Exception e) {
			throw new ApplicationException(AppMessage.GeneralException.GENERAL_SERVER_EXCEPTION, e);
		}
	}

}
