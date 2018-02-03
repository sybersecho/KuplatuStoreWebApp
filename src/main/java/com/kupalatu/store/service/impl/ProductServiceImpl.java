package com.kupalatu.store.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kupalatu.store.model.Product;
import com.kupalatu.store.repository.ProductRepository;
import com.kupalatu.store.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> findAll() {
		log.info("find all products");
		return (List<Product>) productRepository.findAll();
	}

}
