package com.kupalatu.store.commands;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProductCommand {
	private Long id;
	private String name;
	private String barcode;
	private String description;
	private Integer quantity = 0;
	private BigDecimal salesPrice = BigDecimal.ZERO;
	private String unit;
}
