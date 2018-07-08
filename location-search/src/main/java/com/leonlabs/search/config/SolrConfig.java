package com.leonlabs.search.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@Configuration
@EnableSolrRepositories(basePackages = "com.leonlabs.search.repository.solr", multicoreSupport = true)
public class SolrConfig extends SpringBootServletInitializer {

	@Bean
    public SolrClient solrClient() {
        return new HttpSolrClient("http://localhost:8983/");
    }
 
    @Bean
    public SolrTemplate solrTemplate(SolrClient client) throws Exception {
        return new SolrTemplate(client, "solr");
    }
	
}
