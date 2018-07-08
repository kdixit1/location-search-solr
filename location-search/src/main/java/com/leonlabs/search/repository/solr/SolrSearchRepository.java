package com.leonlabs.search.repository.solr;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import com.leonlabs.search.entity.City;

public interface SolrSearchRepository extends SolrCrudRepository<City, String> {

	@Query("name:*?0*")
    public Page<City> findByNameQuery(String freeText, Pageable pageable);

}
