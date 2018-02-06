package com.kupalatu.store.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kupalatu.store.model.Product;
import com.kupalatu.store.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping(value = "/product")
public class ProductController {

	private ProductService productService;
	// private ProductCommandToProduct commandToProduct;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	// public ProductController(ProductService productService,
	// ProductCommandToProduct commandToProduct) {
	// super();
	// this.productService = productService;
	// this.commandToProduct = commandToProduct;
	// }

	@RequestMapping(value = "/")
	public String productHome(Model model) {
		List<Product> products = (List<Product>) productService.findAll();
		model.addAttribute("products", products);

		for (Product product : products) {
			log.info("product name: {}", product.getName());
		}

		return "product/product";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String productById(@PathVariable Long id, Model model) {
		log.info("get product by Id: {}", id);
		Product product = productService.findById(id);
		model.addAttribute("product", product);

		log.info("product: {}", product);
		return "product/show-product";
	}
}
