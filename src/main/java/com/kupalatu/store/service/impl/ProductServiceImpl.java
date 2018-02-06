package com.kupalatu.store.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.kupalatu.store.commands.ProductCommand;
import com.kupalatu.store.converters.ProductCommandToProduct;
import com.kupalatu.store.converters.ProductToProductCommand;
import com.kupalatu.store.model.Product;
import com.kupalatu.store.repository.ProductRepository;
import com.kupalatu.store.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;
	private ProductCommandToProduct commandToProduct;
	private ProductToProductCommand productToCommand;

	// public ProductServiceImpl(ProductRepository productRepository) {
	// this.productRepository = productRepository;
	// }

	public ProductServiceImpl(ProductRepository productRepository, ProductCommandToProduct commandToProduct,
			ProductToProductCommand productToCommand) {
		this.productRepository = productRepository;
		this.commandToProduct = commandToProduct;
		this.productToCommand = productToCommand;
	}

	@Override
	public List<Product> findAll() {
		log.info("find all products");
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public Product findById(Long id) {
		Optional<Product> found = productRepository.findById(id);
		if (!found.isPresent()) {
			throw new RuntimeException("Product not found!");
		}
		return found.get();
	}

	@Override
	@Transactional
	public ProductCommand saveCommand(ProductCommand command) {
		log.info("save new commnand");
		Product detachedProduct = commandToProduct.convert(command);

		Product saved = productRepository.save(detachedProduct);
		log.debug("Saved Product Id: {}", saved.getId());
		return productToCommand.convert(saved);
	}

}
