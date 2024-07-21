package com.learning.simran.jpaTutorial;

import com.learning.simran.jpaTutorial.entities.ProductEntity;
import com.learning.simran.jpaTutorial.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class JpaTutorialApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void aestRepository() {
		ProductEntity productEntity = ProductEntity.builder()
				.sku("nestle123")
				.title("Nestle choc")
				.quantity(12)
				.price(BigDecimal.valueOf(12))
				.build();

		ProductEntity savedEntity = productRepository.save(productEntity);
		System.out.println(savedEntity);
	}

	@Test
	void getRepository() {
		List<ProductEntity> entities = productRepository.findAll();
		System.out.println(entities);
	}

	@Test
	void getRepository_custom() {
		List<ProductEntity> entities = productRepository.findByTitle("Coke");
		System.out.println(entities);
	}

	@Test
	void getRepository_custom2 () {
		List<ProductEntity> entities = productRepository.findByCreatedAtAfter(LocalDateTime.of(2024,1,1, 0 ,0 ,0));
		System.out.println(entities);
	}

	@Test
	void getRepository_custom3 () {
		List<ProductEntity> entities = productRepository.findByPriceAndQuantity(BigDecimal.valueOf(14.40), 4);
		System.out.println(entities);
	}

	@Test
	void getRepository_custom4 () {
		List<ProductEntity> entities = productRepository.findByPriceGreaterThanAndQuantityLessThan(BigDecimal.valueOf(14.39), 5);
		System.out.println(entities);
	}

	@Test
	void getRepository_custom5 () {
		List<ProductEntity> entities = productRepository.findByTitleLike("%oke%");
		System.out.println(entities);
	}

	@Test
	void getRepository_custom6 () {
		List<ProductEntity> entities = productRepository.findByTitleContaining("oke");
		System.out.println(entities);
	}

	@Test
	void getRepository_custom7 () {
		ProductEntity entities = productRepository.findByTitleAndPrice("Coke", BigDecimal.valueOf(14.40));
		System.out.println(entities);
	}

	@Test
	void getRepository_custom8 () {
		ProductEntity entities = productRepository.findByTitleAndPriceCustomQuery("Coke", BigDecimal.valueOf(14.40));
		System.out.println(entities);
	}

}
