package com.kupalatu.store.model;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductTest {

	private Product productTest;

	@Before
	public void setUp() throws Exception {
		log.info("set up");
		productTest = new Product("DJI SAM SOE KRETEK 12", "8999909028234", "Rokok DJI SAM SOE KRETEK 12", 20,
				BigDecimal.valueOf(12000));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetName() {
		log.info("test get name");
		assertEquals("DJI SAM SOE KRETEK 12", productTest.getName());
	}

	@Test
	public void testGetBarcode() {
		log.info("test get barcode");
		assertEquals("8999909028234", productTest.getBarcode());
	}

	@Test
	public void testSetName() {
		log.info("test set name");
		productTest.setName("new name");
		assertEquals("new name", productTest.getName());
	}

	@Test
	public void testSetBarcode() {
		log.info("test set barcode");
		productTest.setBarcode("new barcode");
		assertEquals("new barcode", productTest.getBarcode());
	}

}
