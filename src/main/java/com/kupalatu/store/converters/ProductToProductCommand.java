package com.kupalatu.store.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.kupalatu.store.commands.ProductCommand;
import com.kupalatu.store.model.Product;

@Component
public class ProductToProductCommand implements Converter<Product, ProductCommand> {

	@Override
	public ProductCommand convert(Product source) {
		ProductCommand command = new ProductCommand();
		command.setBarcode(source.getBarcode());
		command.setDescription(source.getDescription());
		command.setId(source.getId());
		command.setName(source.getName());
		command.setQuantity(source.getQuantity());
		command.setSalesPrice(source.getSalesPrice());
		command.setUnit(source.getUnit());
		return command;
	}

}
