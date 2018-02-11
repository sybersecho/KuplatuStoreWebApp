package com.kupalatu.store.product.service;

import java.util.List;

import com.kupalatu.store.product.commands.ProductCommand;
import com.kupalatu.store.product.model.Product;

public interface ProductService {
	public List<Product> findAll();

	public Product findById(Long id);

	public ProductCommand findOneById(Long id);

	public ProductCommand saveCommand(ProductCommand command);

	public List<ProductCommand> findAllCommands();
}
