package com.kupalatu.store.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kupalatu.store.model.Product;
import com.kupalatu.store.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ProductController {

	private ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping(value = "/product")
	public String productHome(Model model) {
		log.info("lombok");
		List<Product> products = (List<Product>) productService.findAll();
		model.addAttribute("products", products);

		for (Product product : products) {
			log.info("product name: {}", product.getName());
		}

		return "product/product";
	}
}
