package com.kupalatu.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kupalatu.store.repository.SupplierRepository;

@Controller
public class SupplierController {

	private SupplierRepository supplierRepository;

	public SupplierController(SupplierRepository supplierRepository) {
		this.supplierRepository = supplierRepository;
	}

	@RequestMapping(value = "/supplier")
	public String supplierHome(Model model) {
		model.addAttribute("suppliers", supplierRepository.findAll());
		return "supplier/supplier";
	}
}
