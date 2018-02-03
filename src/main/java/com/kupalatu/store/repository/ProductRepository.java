package com.kupalatu.store.repository;

import org.springframework.data.repository.CrudRepository;

import com.kupalatu.store.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
