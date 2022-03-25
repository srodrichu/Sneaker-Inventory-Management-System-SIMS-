package com.qa.simsproject.testing.services;

import java.util.List;
import java.util.Optional;

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
		
		SneakerEntry result = services.createEntry(sneaker1);
		
		Assertions.assertEquals(sneaker1Id, result);;
		
	}
	
	@Test
	public void testGetAll() {
		
		List<SneakerEntry> testList = List.of(sneaker1Id, sneaker2Id);
		
		Mockito.when(repo.findAll()).thenReturn(testList);
		
		List<SneakerEntry> result = services.getAllSneakers();
		
		Assertions.assertEquals(testList, result);
		
	}
	
	@Test
	public void testGetById() {
		
		Mockito.when(repo.findById(1l)).thenReturn(Optional.of(sneaker1Id));
		
		SneakerEntry result = services.getById(1l);
		
		Assertions.assertEquals(sneaker1Id, result);
		
	}
	
	@Test
	public void testUpdateById() {
		
		long id = 2l;
		
		SneakerEntry updatedIn = new SneakerEntry("Jordan 3", 6.5f, 150.00f, 290.00f, false);
		
		SneakerEntry updatedOut = new SneakerEntry(id, updatedIn.getName(), updatedIn.getSize(), updatedIn.getCostPrice(), updatedIn.getListedPrice(), updatedIn.isSold());
		
		Optional<SneakerEntry> toUpdateOpt = Optional.of(sneaker2Id);
		
		Mockito.when(repo.findById(id)).thenReturn(toUpdateOpt);
		Mockito.when(repo.save(updatedOut)).thenReturn(updatedOut);
		
		SneakerEntry result = services.updateById(id, updatedIn);
		
		Assertions.assertEquals(updatedOut, result);
		
	}
	
	@Test
	public void testDeleteById() {
		
		long id = 1L;
		
		Optional<SneakerEntry> deleteOpt = Optional.of(sneaker1Id);
		
		Mockito.when(repo.findById(id)).thenReturn(deleteOpt);
		
		SneakerEntry result = services.deleteById(id);
		
		Assertions.assertEquals(sneaker1Id, result);
		
//		Verify
		
		Mockito.verify(repo, Mockito.times(1)).findById(id);
		Mockito.verify(repo, Mockito.times(1)).deleteById(id);
		
		
	}
	
	@Test
	public void testMarkAsSold() {
		
		long id = 1L;
		
		Optional<SneakerEntry> opt = Optional.of(sneaker1Id);
		
		SneakerEntry soldOut = new SneakerEntry(id, sneaker1Id.getName(), sneaker1Id.getSize(), sneaker1Id.getCostPrice(), sneaker1Id.getListedPrice(), !sneaker1Id.isSold());
		
		
		Mockito.when(repo.findById(id)).thenReturn(opt);
		
		Mockito.when(repo.save(soldOut)).thenReturn(soldOut);
		
		SneakerEntry result = services.markAsSoldById(id);
		
		Assertions.assertEquals(soldOut, result);
		
	}
	
	@Test
	public void realProfitByid() {
		
		long id = 1L;
		
		float expected = sneaker1Id.getListedPrice() - sneaker1Id.getCostPrice();
		
		Optional<SneakerEntry> opt = Optional.of(sneaker1Id);
		
		Mockito.when(repo.findById(id)).thenReturn(opt);
		
		float result = services.profitById(id);
	
		Assertions.assertEquals(expected, result);
			
	}
	
	@Test
	public void testDeleteAll() {
		
		boolean result = services.deleteAll();
		
		Assertions.assertTrue(result);
		
		Mockito.verify(repo, Mockito.times(1)).deleteAll();
		
		
	}
	
	
}
