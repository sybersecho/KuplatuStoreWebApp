package com.kupalatu.store.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import com.kupalatu.store.product.commands.ProductCommand;
import com.kupalatu.store.product.controller.ProductController;
import com.kupalatu.store.product.model.Product;
import com.kupalatu.store.product.service.ProductService;

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
		ProductCommand command = new ProductCommand();
		command.setBarcode("barcode");
		command.setDescription("descritption");
		command.setId(1L);
		command.setName("name");
		command.setQuantity(20);
		command.setSalesPrice(BigDecimal.TEN);
		command.setUnit("Unit");

		when(productService.findOneById(1L)).thenReturn(command);

		mockMvc.perform(get("/product/{id}", Long.valueOf(1))).andExpect(status().isOk())
				.andExpect(view().name("product/show-product")).andExpect(model().attributeExists("product"));

		verify(productService, times(1)).findOneById(1L);
	}

	@Test
	public void testSavingProduct() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

		Product p = new Product("name", "barcode", "desc", 20, BigDecimal.TEN);
		p.setId(1L);

		mockMvc.perform(post("/product/")).andExpect(view().name("redirect:/product/"));
	}

	// @SuppressWarnings("unchecked")
	// @Ignore
	@Test
	public void testProductHome() {
		List<ProductCommand> products = new ArrayList<ProductCommand>();
		products.add(new ProductCommand());
		ProductCommand newProduct = new ProductCommand();
		newProduct.setId(3L);
		products.add(newProduct);

		when(productService.findAllCommands()).thenReturn(products);

		ArgumentCaptor<List<Product>> argumentCaptor = ArgumentCaptor.forClass(List.class);

		String viewName = productController.productHome(model);
		assertEquals("product/product", viewName);

		verify(productService, times(1)).findAllCommands();
		verify(model, times(1)).addAttribute(eq("products"), argumentCaptor.capture());
		List<Product> listInController = argumentCaptor.getValue();
		assertEquals(2, listInController.size());
	}

}
