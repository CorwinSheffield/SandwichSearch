package com.sheffieldcorwin;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sheffieldcorwin.dao.SandwichShopRepository;
import com.sheffieldcorwin.model.SandwichShop;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(SandwichSearchApplication.class)
class SandwichSearchApplicationTests {

	Logger logger = LogManager.getLogger();

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	SandwichShopRepository sandwichShopRepository;
	
	@Test
	void contextLoads() {
	}

	@Test
	void testDataBaseConnectivity(){
		SandwichShop shop = new SandwichShop(null, "capriottis",3.0);
		SandwichShop newShop = sandwichShopRepository.save(shop);
		
		assertThat(newShop.name()).isEqualTo(shop.name());
	}
	
	
	
	@Test
	public void testWebIntoDataBase() {
		
		logger.traceEntry();
		
		SandwichShop sandwichShop = new SandwichShop(null, "Capriottis", 3.0);
		logger.debug("New SandwichShop: {}", sandwichShop.toString());
		ResponseEntity<Void> response = restTemplate.postForEntity("/sandwichshops", sandwichShop, Void.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		logger.debug(response.toString());
		URI location = response.getHeaders().getLocation();
		
		SandwichShop createdShop = restTemplate.getForObject(location, SandwichShop.class);
		assertThat(createdShop.id()).isNotNull();
	
	
	}
}
