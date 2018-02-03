package com.kupalatu.store.model;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kupalatu.store.repository.ProductRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryIT {

	@Autowired
	ProductRepository productRepository;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void findById1() {
		Optional<Product> optional = productRepository.findById(1L);
		assertEquals(Long.valueOf(1), optional.get().getId());
	}

	@Test
	public void findById2() {
		Optional<Product> optional = productRepository.findById(2L);
		assertEquals(Long.valueOf(2), optional.get().getId());
	}

}
