package com.kupalatu.store.product.repository;

import org.springframework.data.repository.CrudRepository;

import com.kupalatu.store.product.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
