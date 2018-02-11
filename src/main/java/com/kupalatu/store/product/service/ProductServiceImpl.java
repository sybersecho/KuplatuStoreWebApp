package com.kupalatu.store.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.kupalatu.store.product.commands.ProductCommand;
import com.kupalatu.store.product.converters.ProductCommandToProduct;
import com.kupalatu.store.product.converters.ProductToProductCommand;
import com.kupalatu.store.product.model.Product;
import com.kupalatu.store.product.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;
	private ProductCommandToProduct commandToProduct;
	private ProductToProductCommand productToCommand;

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

	@Override
	public ProductCommand findOneById(Long id) {
		Product found = findById(id);

		return productToCommand.convert(found);
	}

	@Override
	public List<ProductCommand> findAllCommands() {
		Iterable<Product> products = findAll();
		List<ProductCommand> commands = new ArrayList<ProductCommand>();

		for (Product product : products) {
			commands.add(productToCommand.convert(product));
		}

		return commands;
	}

}
