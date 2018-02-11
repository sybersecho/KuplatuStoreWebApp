package com.kupalatu.store.product.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
@Table(name = "t_product")
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 188064484669134588L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String barcode;
	private String description;
	private Integer quantity = 0;
	@Column(name = "sales_price")
	private BigDecimal salesPrice = BigDecimal.ZERO;
	private String unit;

	public Product() {
		super();
	}

	public Product(@NotEmpty String name, @NotEmpty String barcode, String description, Integer quantity,
			BigDecimal salesPrice) {
		super();
		this.name = name;
		this.barcode = barcode;
		this.description = description;
		this.quantity = quantity;
		this.salesPrice = salesPrice;
	}

}
