package com.kupalatu.store.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.kupalatu.store.model.Product;
import com.kupalatu.store.repository.ProductRepository;
import com.kupalatu.store.service.impl.ProductServiceImpl;

public class ProductServiceImplTest {

	private ProductService productService;
	@Mock
	private ProductRepository productRepository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		productService = new ProductServiceImpl(productRepository, null, null);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindAll() {
		assertEquals(0, productService.findAll().size());
		verify(productRepository, times(1)).findAll();
	}

	@Test
	public void findByIdTest() {
		Product product = new Product();
		product.setId(1L);
		Optional<Product> productOpt = Optional.of(product);

		when(productRepository.findById(anyLong())).thenReturn(productOpt);

		Product productReturned = productService.findById(1L);

		assertNotNull("product returned is null", productReturned);
		verify(productRepository, times(1)).findById(1L);
		verify(productRepository, never()).findAll();

	}

}
