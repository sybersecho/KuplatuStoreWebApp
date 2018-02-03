package com.kupalatu.store.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.kupalatu.store.repository.ProductRepository;
import com.kupalatu.store.service.impl.ProductServiceImpl;

public class ProductServiceImplTest {

	private ProductService productService;
	@Mock
	private ProductRepository productRepository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		productService = new ProductServiceImpl(productRepository);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindAll() {
		assertEquals(0, productService.findAll().size());
		verify(productRepository, times(1)).findAll();
	}

}
