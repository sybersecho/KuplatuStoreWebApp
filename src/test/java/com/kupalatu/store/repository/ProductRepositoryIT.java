package com.kupalatu.store.repository;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kupalatu.store.commands.ProductCommand;
import com.kupalatu.store.converters.ProductCommandToProduct;
import com.kupalatu.store.converters.ProductToProductCommand;
import com.kupalatu.store.model.Product;
import com.kupalatu.store.service.ProductService;

@RunWith(SpringRunner.class)
// @DataJpaTest
@SpringBootTest
public class ProductRepositoryIT {

	@Autowired
	ProductRepository productRepository;
	@Autowired
	ProductService productService;
	@Autowired
	ProductCommandToProduct commandToProduct;
	@Autowired
	ProductToProductCommand productToCommand;

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

	@Test
	public void testSaveProduct() throws Exception {
		// given
		Iterable<Product> products = productRepository.findAll();
		Product aProduct = products.iterator().next();
		ProductCommand command = productToCommand.convert(aProduct);

		// when
		command.setDescription("testes");
		ProductCommand savedCommand = productService.saveCommand(command);

		// then
		assertEquals(command.getDescription(), savedCommand.getDescription());
		assertEquals(aProduct.getId(), savedCommand.getId());

	}

}
