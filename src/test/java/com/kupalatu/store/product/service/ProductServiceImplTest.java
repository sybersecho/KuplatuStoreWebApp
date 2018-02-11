package com.kupalatu.store.product.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.kupalatu.store.product.commands.ProductCommand;
import com.kupalatu.store.product.converters.ProductCommandToProduct;
import com.kupalatu.store.product.converters.ProductToProductCommand;
import com.kupalatu.store.product.model.Product;
import com.kupalatu.store.product.repository.ProductRepository;
import com.kupalatu.store.product.service.ProductService;
import com.kupalatu.store.product.service.ProductServiceImpl;

public class ProductServiceImplTest {

	private ProductService productService;
	@Mock
	private ProductRepository productRepository;
	@Mock
	private ProductCommandToProduct commandToProduct;
	@Mock
	private ProductToProductCommand productToCommand;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		productService = new ProductServiceImpl(productRepository, commandToProduct, productToCommand);
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

	@Test
	@Ignore
	public void testFindAllCommands() {
		List<Product> products = new ArrayList<Product>();
		products.add(new Product());
		products.add(new Product());

		when(productRepository.findAll()).thenReturn(products);

		List<ProductCommand> commands = new ArrayList<ProductCommand>();
		commands.add(new ProductCommand());
		commands.add(new ProductCommand());

		// when(productService.findAllCommands()).thenReturn(commands);
	}

	@Test
	public void testFindOneById() {
		Product product = new Product();
		product.setId(1L);
		Optional<Product> productOpt = Optional.of(product);

		when(productRepository.findById(anyLong())).thenReturn(productOpt);

		ProductCommand command = new ProductCommand();
		command.setId(1L);

		when(productToCommand.convert(any())).thenReturn(command);

		ProductCommand commandReturn = productService.findOneById(1L);
		assertNotNull("null product return", commandReturn);

		verify(productRepository, times(1)).findById(anyLong());

	}

}
