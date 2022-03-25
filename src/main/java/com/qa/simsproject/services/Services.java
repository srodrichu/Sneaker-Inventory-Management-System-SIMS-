package com.qa.simsproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.simsproject.model.SneakerEntry;
import com.qa.simsproject.repo.Repo;

@Service
public class Services {
	
	private Repo repo;

	public Services(Repo repo) {
		super();
		this.repo = repo; 
	}
	
	public SneakerEntry createEntry(SneakerEntry entry) {

				return repo.save(entry);
		
	}
	
//	Get all the sneaker entries in the database
	
	public List<SneakerEntry> getAllSneakers() {
		
		return repo.findAll();
		
	}
	
//	Get an entry by it's ID
	
	public SneakerEntry getById(long id) {
		
		Optional<SneakerEntry> opt = repo.findById(id);
		
		return opt.orElse(null);
		
	}
	
//	Update an entry by it's ID
	
	public SneakerEntry updateById(long id, SneakerEntry entry) {
		
		Optional<SneakerEntry> opt = repo.findById(id);
		
		SneakerEntry originalEntry = opt.get();
		
		originalEntry.setName(entry.getName());
		originalEntry.setSize(entry.getSize());
		originalEntry.setCostPrice(entry.getCostPrice());
		originalEntry.setListedPrice(entry.getListedPrice());
		originalEntry.setSold(entry.isSold());
		
		return repo.save(originalEntry);
		
	}
	
//	Delete an entry by it's ID
	
	public SneakerEntry deleteById(long id) {
		
		Optional<SneakerEntry> deleteEntry = repo.findById(id);
		
		repo.deleteById(id);
		
		return deleteEntry.orElse(null);
		
	}
	
//	Mark an entry as sold - change isSold from false to true
	
	public SneakerEntry markAsSoldById(long id) {
		
		Optional<SneakerEntry> opt = repo.findById(id);
		
		SneakerEntry soldSneaker = opt.get();
		
		soldSneaker.setSold(true);
		
		return repo.save(soldSneaker);
		
		
	}
	
//	Delete all entries in the DB
	
	public boolean deleteAll() {
		
		repo.deleteAll();
		
		return true;
		
	}
	
//	Calculate profit by ID
	
	public float profitById(long id){
		
		Optional<SneakerEntry> opt = repo.findById(id);
		
		SneakerEntry sneaker = opt.get();
		
		if (sneaker.isSold() == true) {
			
			System.out.println("Realised profit");
				
			return sneaker.getListedPrice() - sneaker.getCostPrice();
		
		} else {
			
			System.out.println("Unrealised profit");
			
			return sneaker.getListedPrice() - sneaker.getCostPrice();
			
		}
	}
	
	
//	Custom Query 1
	
	public List<SneakerEntry> findBySizeBetweenAndIsSoldFalse(float startSize, float endSize){
		
		return repo.findBySizeBetweenAndIsSoldFalse(startSize, endSize);
		
	}
	
//	Custom Query 2
	
	public List<SneakerEntry> findByListedPriceLessThanEqual(float price){
		
		return repo.findByListedPriceLessThanEqualAndIsSoldFalse(price);
		
	}
	
	public List<SneakerEntry> findByIsSoldFalse(){
		
		return repo.findByIsSoldFalse();
		
	}
	
	
	
	
	
	
	
	
	
	
}
