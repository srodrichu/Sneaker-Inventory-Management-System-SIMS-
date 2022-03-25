package com.qa.simsproject.testing.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.simsproject.model.SneakerEntry;

@SpringBootTest
@AutoConfigureMockMvc

@Sql(scripts = {"classpath:test-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)

@ActiveProfiles("dev")
public class ControllerTest {

	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	public void testCreate() throws Exception {
		
		SneakerEntry newEntry = new SneakerEntry("testCreate", 7.5f, 180.00f, 300.00f, false);
		SneakerEntry expectOut = new SneakerEntry(5l, "testCreate", 7.5f, 180.00f, 300.00f, false);
		
		String createJSON = mapper.writeValueAsString(newEntry);
		String expectOutJSON = mapper.writeValueAsString(expectOut);
		
		RequestBuilder mockReq = post("/createEntry").contentType(MediaType.APPLICATION_JSON).content(createJSON);
		
		ResultMatcher matchStatus = status().isAccepted();
		ResultMatcher matchBody = content().json(expectOutJSON);
		
		mock.perform(mockReq).andExpect(matchStatus).andExpect(matchBody);
	}
	
	@Test
	public void testGetAllSneakers() throws Exception {
		
		SneakerEntry sneaker1 = new SneakerEntry(1l,"sneaker1", 6.5f, 160.00f, 240.00f, false);
		SneakerEntry sneaker2 = new SneakerEntry(2l,"sneaker2", 8.5f, 170.00f, 290.00f, false);
		SneakerEntry sneaker3 = new SneakerEntry(3l,"sneaker3", 9.0f, 110.00f, 280.00f, false);
		SneakerEntry sneaker4 = new SneakerEntry(4l,"sneaker4", 4.5f, 75.00f, 180.00f, false);
		
		List<SneakerEntry> dbCopy = List.of(sneaker1, sneaker2, sneaker3, sneaker4);
		
		String dbCopyJson = mapper.writeValueAsString(dbCopy);
		
		RequestBuilder mockReq = get("/getAllSneakers");
		
		ResultMatcher matchStatus = status().isAccepted();
		ResultMatcher matchBody = content().json(dbCopyJson);
		
		mock.perform(mockReq).andExpect(matchStatus).andExpect(matchBody);
		
	}
	
	@Test
	public void testGetById() throws Exception {
		
		SneakerEntry sneaker1 = new SneakerEntry(1l,"sneaker1", 6.5f, 160.00f, 240.00f, false);
		
		String sneakerJSON = mapper.writeValueAsString(sneaker1);
		
		RequestBuilder mockReq = get("/getById/1");
		
		ResultMatcher matchStatus = status().isAccepted();
		ResultMatcher matchBody = content().json(sneakerJSON);
		
		mock.perform(mockReq).andExpect(matchStatus).andExpect(matchBody);
	
	}
	
	@Test
	public void testUpdate() throws Exception {
		
//		Updating entry with id = 1
		
		SneakerEntry sneakerUpdateIn = new SneakerEntry("sneakerUpdate", 7.5f, 200.00f, 340.00f, false);
		SneakerEntry sneakerUpdateOut = new SneakerEntry(1l, "sneakerUpdate", 7.5f, 200.00f, 340.00f, false);
		
		String sneakerUpdateJSONIn = mapper.writeValueAsString(sneakerUpdateIn);
		String sneakerUpdatedJSONOut = mapper.writeValueAsString(sneakerUpdateOut);
		RequestBuilder mockReq = put("/update/1").contentType(MediaType.APPLICATION_JSON).content(sneakerUpdateJSONIn);
		
		ResultMatcher matchStatus = status().isAccepted();
		ResultMatcher matchBody = content().json(sneakerUpdatedJSONOut);
		
		mock.perform(mockReq).andExpect(matchStatus).andExpect(matchBody);
		
	}
	
	@Test
	public void testDeleteById() throws Exception {
		
//		"sneakerId1" is a copy of the entry that has id = 1 in the test database
		
		SneakerEntry sneakerId1 = new SneakerEntry(1l, "sneaker1", 6.5f, 160.00f, 240.00f, false);
		
		String sneakerId1JSON = mapper.writeValueAsString(sneakerId1);
		
		RequestBuilder mockReq = delete("/delete/1");
		
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(sneakerId1JSON);
		
		mock.perform(mockReq).andExpect(checkStatus).andExpect(checkBody);
		
	}
	
	@Test
	public void testMarkSold() throws Exception {
		
//		"sneakerId1" is the same as id = 1 in the database but with isSold set to true
		
		SneakerEntry sneakerId1Output = new SneakerEntry(1l, "sneaker1", 6.5f, 160.00f, 240.00f, true);
		
		String sneakerId1JSON = mapper.writeValueAsString(sneakerId1Output);
		
		RequestBuilder mockReq = put("/markSold/1");
		
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(sneakerId1JSON);
		
		mock.perform(mockReq).andExpect(checkStatus).andExpect(checkBody);
		
	}
	
	@Test
	public void testProfitById() throws Exception {
		
//		"sneakerId1" is a copy of the entry that has id = 1 in the test database
		
		SneakerEntry sneakerId1 = new SneakerEntry(1l, "sneaker1", 6.5f, 160.00f, 240.00f, false);
		
		float result = sneakerId1.getListedPrice() - sneakerId1.getCostPrice();
		
		String resultJSON = mapper.writeValueAsString(result);
		
		RequestBuilder mockReq = get("/profit/1");
		
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(resultJSON);
		
		mock.perform(mockReq).andExpect(checkStatus).andExpect(checkBody);
		
	}
	
}
