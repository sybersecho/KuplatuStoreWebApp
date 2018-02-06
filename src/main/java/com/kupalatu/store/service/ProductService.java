package com.kupalatu.store.service;

import java.util.List;

import com.kupalatu.store.commands.ProductCommand;
import com.kupalatu.store.model.Product;

public interface ProductService {
	public List<Product> findAll();

	public Product findById(Long id);

	public ProductCommand saveCommand(ProductCommand command);
}
