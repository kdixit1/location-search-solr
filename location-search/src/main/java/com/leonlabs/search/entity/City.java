package com.leonlabs.search.entity;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@SolrDocument(solrCoreName = "kd_test")
public class City extends BaseEntity{

	private static final long serialVersionUID = 1L;

	@Field
	private Integer id;

	@Field
	private String name;

	@Field
	private Integer stateId;

}
