package com.kupalatu.store.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kupalatu.store.product.commands.ProductCommand;
import com.kupalatu.store.product.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping(value = "/product")
public class ProductController {

	private ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping(value = "/")
	public String productHome(Model model) {
		model.addAttribute("products", productService.findAllCommands());

		return "product/product";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String productById(@PathVariable Long id, Model model) {
		log.info("get product by Id: {}", id);
		model.addAttribute("product", productService.findOneById(id));

		return "product/show-product";
	}

	@PostMapping
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String savingProduct(@ModelAttribute ProductCommand command) {
		log.info("saved product");
		productService.saveCommand(command);
		// model.addAttribute("product", product);
		return "redirect:/product/";
	}
}
