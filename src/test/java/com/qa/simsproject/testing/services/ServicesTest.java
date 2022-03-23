package com.qa.simsproject.testing.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.simsproject.model.SneakerEntry;
import com.qa.simsproject.repo.Repo;
import com.qa.simsproject.services.Services;

@SpringBootTest
public class ServicesTest {
	
	@MockBean
	private Repo repo;
	
	@Autowired
	private Services services;
	
//	Mock objects
	
//	With ID
	
	SneakerEntry sneaker1Id = new SneakerEntry(1l, "Jordan 4", 7.5f, 180.00f, 300.00f, false);
	SneakerEntry sneaker2Id = new SneakerEntry(2l, "Jordan 1", 9.5f, 140.00f, 190.00f, false);
	
//	Without ID
	
	SneakerEntry sneaker1 = new SneakerEntry("Jordan 4", 7.5f, 180.00f, 300.00f, false);
	SneakerEntry sneaker2 = new SneakerEntry("Jordan 1", 9.5f, 140.00f, 190.00f, false);
	
	
	@Test
	public void testCreate() {
		
		Mockito.when(repo.save(sneaker1)).thenReturn(sneaker1Id);
		
		boolean result = services.createEntry(sneaker1);
		
		Assertions.assertTrue(result);
		
	}
	
	
}