package com.kupalatu.store.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.kupalatu.store.commands.ProductCommand;
import com.kupalatu.store.model.Product;

import lombok.Synchronized;

@Component
public class ProductCommandToProduct implements Converter<ProductCommand, Product> {

	@Synchronized
	@Nullable
	@Override
	public Product convert(ProductCommand source) {
		if (source == null) {
			return null;
		}
		final Product finaleProduct = new Product(source.getName(), source.getBarcode(), source.getDescription(),
				source.getQuantity(), source.getSalesPrice());
		finaleProduct.setId(source.getId());
		finaleProduct.setUnit(source.getUnit());
		return finaleProduct;
	}

}
