package com.qa.simsproject.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.simsproject.model.SneakerEntry;
import com.qa.simsproject.services.Services;

@RestController
public class Controller {
	
	private Services service;

	public Controller(Services service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/createEntry")
	public ResponseEntity<SneakerEntry> createBooking(@RequestBody SneakerEntry entry){
		
		
		ResponseEntity<SneakerEntry> response = new ResponseEntity<>(service.createEntry(entry), HttpStatus.ACCEPTED);
		
		return response;
		
	}
	
	@GetMapping("/getAllSneakers")
	public ResponseEntity<List<SneakerEntry>> getAllSneakers(){
		
		List<SneakerEntry> response = service.getAllSneakers();
		
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<SneakerEntry> getById(@PathVariable("id") long id){
		
		SneakerEntry response = service.getById(id);
		
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<SneakerEntry> updateById(@PathVariable("id") long id, @RequestBody SneakerEntry entry){
		
		
		ResponseEntity<SneakerEntry> response = new ResponseEntity<>(service.updateById(id, entry), HttpStatus.ACCEPTED);
		
		return response;
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<SneakerEntry> deleteById(@PathVariable("id") long id){
		
		ResponseEntity<SneakerEntry> response = new ResponseEntity<>(service.deleteById(id), HttpStatus.ACCEPTED);
		
		return response;
		
	}
	
	@DeleteMapping("/deleteAll")
	public ResponseEntity<String> deleteAll(){
		
		service.deleteAll();
		
		ResponseEntity<String> response = new ResponseEntity<>("All entries have been deleted", HttpStatus.ACCEPTED);
		
		return response;
		
	}
	
	@PutMapping("/markSold/{id}")
	public ResponseEntity<SneakerEntry> markAsSold(@PathVariable("id") long id){

		
		ResponseEntity<SneakerEntry> response = new ResponseEntity<>(service.markAsSoldById(id), HttpStatus.ACCEPTED);
		
		return response;
		
	}
	
	@GetMapping("/profit/{id}")
	public ResponseEntity<Float> profitById(@PathVariable("id") long id){
		
		float profit = service.profitById(id);
		
		ResponseEntity<Float> response = new ResponseEntity<>(profit, HttpStatus.ACCEPTED);
		
		return response;
		
	}
	
	@GetMapping("/findBySizeRange/{startSize}/{endSize}")
	public ResponseEntity<List<SneakerEntry>> findBySize(@PathVariable("startSize") float startSize, @PathVariable("endSize") float endSize){
		
		List<SneakerEntry> response = service.findBySizeBetweenAndIsSoldFalse(startSize, endSize);
		
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/findByPriceLessThanEqual/{price}")
	public ResponseEntity<List<SneakerEntry>> findByPriceLessThanEqual(@PathVariable("price") float price){
		
		List<SneakerEntry> response = service.findByListedPriceLessThanEqual(price);
		
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/findByIsSoldFalse")
	public ResponseEntity<List<SneakerEntry>> findByIsSoldFalse(){
		
		List<SneakerEntry> response = service.findByIsSoldFalse();
		
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		
	}
}
