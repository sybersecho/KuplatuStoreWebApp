package com.kupalatu.store.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kupalatu.store.commands.ProductCommand;
import com.kupalatu.store.model.Product;

public class ProductCommandToProductTest {

	private ProductCommandToProduct converter;

	@Before
	public void setUp() throws Exception {
		converter = new ProductCommandToProduct();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNullParameter() throws Exception {
		assertNull(converter.convert(null));
	}

	@Test
	public void testEmptyObject() throws Exception {
		assertNotNull(converter.convert(new ProductCommand()));
	}

	@Test
	public void testConvert() throws Exception {
		// given
		ProductCommand command = new ProductCommand();
		command.setId(1L);
		command.setBarcode("8999909028234");
		command.setDescription("Rokok DJI SAM SOE KRETEK 12");
		command.setName("DJI SAM SOE KRETEK 12");
		command.setQuantity(20);
		command.setSalesPrice(BigDecimal.TEN);
		command.setUnit("Bungkus");

		// when
		Product p = converter.convert(command);

		// then
		assertEquals(command.getBarcode(), p.getBarcode());
		assertEquals(command.getDescription(), p.getDescription());
		assertEquals(command.getId(), p.getId());
		assertEquals(command.getName(), p.getName());
		assertEquals(command.getQuantity(), p.getQuantity());
		assertEquals(command.getSalesPrice(), p.getSalesPrice());
		assertEquals(command.getUnit(), p.getUnit());
	}

}
