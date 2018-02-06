package com.kupalatu.store.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.kupalatu.store.model.Product;
import com.kupalatu.store.service.ProductService;

public class ProductControllerTest {

	@Mock
	private ProductService productService;
	@Mock
	private Model model;
	private ProductController productController;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		productController = new ProductController(productService);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMockMvc() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

		mockMvc.perform(get("/product/")).andExpect(status().isOk()).andExpect(view().name("product/product"));
	}

	@Test
	public void testProductById() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

		Product p = new Product("name", "barcode", "desc", 20, BigDecimal.TEN);
		p.setId(1L);

		when(productService.findById(1L)).thenReturn(p);

		mockMvc.perform(get("/product/{id}", Long.valueOf(1))).andExpect(status().isOk())
				.andExpect(view().name("product/show-product")).andExpect(model().attributeExists("product"));

		verify(productService, times(1)).findById(1L);
	}

//	@SuppressWarnings("unchecked")
	// @Ignore
	@Test
	public void testProductHome() {
		List<Product> products = new ArrayList<Product>();
		products.add(new Product());
		Product newProduct = new Product();
		newProduct.setId(3L);
		products.add(newProduct);

		when(productService.findAll()).thenReturn(products);

		ArgumentCaptor<List<Product>> argumentCaptor = ArgumentCaptor.forClass(List.class);

		String viewName = productController.productHome(model);
		assertEquals("product/product", viewName);

		verify(productService, times(1)).findAll();
		verify(model, times(1)).addAttribute(eq("products"), argumentCaptor.capture());
		List<Product> listInController = argumentCaptor.getValue();
		assertEquals(2, listInController.size());
	}

}
