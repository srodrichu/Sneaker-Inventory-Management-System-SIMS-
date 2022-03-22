package com.qa.simsproject.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.simsproject.model.SneakerEntry;

public interface Repo extends JpaRepository<SneakerEntry, Long>{

	public List<SneakerEntry> findBySizeBetweenAndIsSoldFalse(float startSize, float endSize);
	
	public List<SneakerEntry> findByListedPriceLessThanEqualAndIsSoldFalse(float price);
	
	public List<SneakerEntry> findByIsSoldFalse();
	
	
}
