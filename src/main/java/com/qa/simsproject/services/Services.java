package com.qa.simsproject.services;

import java.util.List;

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
	
	public boolean createEntry(SneakerEntry entry) {
		
		repo.save(entry);
		return true;
		
	}
	
//	Get all the sneaker entries in the database
	
	public List<SneakerEntry> getAllSneakers() {
		
		return repo.findAll();
		
	}
	
//	Get an entry by it's ID
	
	public SneakerEntry getById(long id) {
		
		return repo.findById(id).get();
		
	}
	
//	Update an entry by it's ID
	
	public boolean updateByIndex(long id, SneakerEntry entry) {
		
		SneakerEntry originalEntry = getById(id);
		
		originalEntry.setName(entry.getName());
		originalEntry.setSize(entry.getSize());
		originalEntry.setCostPrice(entry.getCostPrice());
		originalEntry.setListedPrice(entry.getListedPrice());
		originalEntry.setSold(entry.isSold());
		
		repo.save(originalEntry);
		
		return true;
	}
	
//	Delete an entry by it's ID
	
	public boolean deleteByIndex(long id) {
		
		repo.deleteById(id);
		
		return true;
		
	}
	
//	Mark an entry as sold - change isSold from false to true
	
	public boolean markAsSoldById(long id) {
		
		SneakerEntry soldSneaker = getById(id);
		
		soldSneaker.setSold(true);
		
		repo.save(soldSneaker);
		
		return true;
		
	}
	
//	Delete all entries in the DB
	
	public boolean deleteAll() {
		
		repo.deleteAll();
		
		return true;
		
	}
	
//	Calculate profit by ID
	
	public float profitById(long id){
		
		SneakerEntry sneaker = repo.findById(id).get();
		
		if (sneaker.isSold() == true) {
			
			System.out.println("Realised profit");
				
			return sneaker.getListedPrice() - sneaker.getCostPrice();
		
		} else {
			
			System.out.println("Unrealised profit");
			
			return sneaker.getListedPrice() - sneaker.getCostPrice();
			
		}
	}
	
	
//	Find sneakers for which their size is between startSize and endSize and isn't sold
	
	public List<SneakerEntry> findBySizeBetween(float startSize, float endSize){
		
		return repo.findBySizeBetweenAndIsSoldFalse(startSize, endSize);
		
	}
	
//	Find sneakers with listed price less than argument
	
	public List<SneakerEntry> findByListedPriceLessThanEqual(float price){
		
		return repo.findByListedPriceLessThanEqualAndIsSoldFalse(price);
		
	}
	
//	Find all sneakers that haven't been sold
	
	public List<SneakerEntry> findByIsSoldFalse(){
		
		return repo.findByIsSoldFalse();
		
	}
	
	
	
	
	
	
	
	
}
