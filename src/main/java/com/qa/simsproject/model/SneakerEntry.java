package com.qa.simsproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SneakerEntry {
	
//	Creating the schema. ID, Name, Size, Cost Price, Listed Price, Sold
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, length = 150)
	private String name;
	
	@Column(nullable = false)
	private float size;
	
	@Column(nullable = false)
	private float costPrice;
	
	@Column(nullable = false)
	private float listedPrice;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean isSold = false;

	public SneakerEntry() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SneakerEntry(long id, String name, float size, float costPrice, float listedPrice, boolean isSold) {
		super();
		this.id = id;
		this.name = name;
		this.size = size;
		this.costPrice = costPrice;
		this.listedPrice = listedPrice;
		this.isSold = isSold;
	}
	
	

}
