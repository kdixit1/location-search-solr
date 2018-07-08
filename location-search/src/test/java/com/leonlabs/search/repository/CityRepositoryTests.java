package com.leonlabs.search.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leonlabs.search.Application;
import com.leonlabs.search.entity.City;
import com.leonlabs.search.repository.solr.SolrSearchRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= {Application.class})
//@ContextConfiguration(classes = Application.class, loader=SpringApplicationContextLoader.class)
public class CityRepositoryTests {
	
	@Autowired
	private SolrSearchRepository solrSearchRepository;
	
	@Before
	public void before() {
		createSampleData();
	}
	
	@Test
	public void findByName() {
		Sort sort = new Sort(new Sort.Order(Direction.ASC, "name"));
		Pageable pageable = new PageRequest(0, 10, sort);
		Page<City> citys = solrSearchRepository.findByNameQuery("Island", pageable);
		assertEquals(citys.getSize(), 4);
	}
	
	private void createSampleData() {
		if (solrSearchRepository.findOne(new Integer(01).toString()) != null) {
			System.out.println("Not adding test data to solr index. Data already exists");
			return;
		}
		addCityToIndex(1, "Milan");
		addCityToIndex(2, "Kanpur");
		addCityToIndex(3, "Lucknow");
		addCityToIndex(4, "Hydrabad");
		addCityToIndex(5, "Banaras");
		addCityToIndex(6, "Varanasi");
		addCityToIndex(7, "New York");
		addCityToIndex(8, "Los Vegas");
		addCityToIndex(9, "London");
		addCityToIndex(10, "Paris");
	}
	
	
	private void addCityToIndex(Integer id, String name) {
		City city = new City();
		city.setName(name);
		city.setId(id);
		solrSearchRepository.save(city);
		System.out.println("Added city with id " + id + " to index");
	}
}
