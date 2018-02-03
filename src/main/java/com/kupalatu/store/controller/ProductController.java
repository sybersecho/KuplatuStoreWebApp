package com.kupalatu.store.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kupalatu.store.model.Product;
import com.kupalatu.store.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ProductController {

	private ProductRepository productRepository;

	public ProductController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@RequestMapping(value = "/product")
	public String productHome(Model model) {
		log.info("lombok");
		List<Product> products = (List<Product>) productRepository.findAll();
		model.addAttribute("products", products);

		for (Product product : products) {
			log.info("product name: {}", product.getName());
		}

		return "product/product";
	}
}
