package com.kupalatu.store.init;

import java.math.BigDecimal;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.kupalatu.store.model.Address;
import com.kupalatu.store.model.Product;
import com.kupalatu.store.model.Supplier;
import com.kupalatu.store.repository.ProductRepository;
import com.kupalatu.store.repository.SupplierRepository;

@Component
public class DBInit implements ApplicationListener<ContextRefreshedEvent> {

	// @Autowired
	private ProductRepository productRepository;
	private SupplierRepository supplierRepository;

	public DBInit(ProductRepository productRepository, SupplierRepository supplierRepository) {
		this.productRepository = productRepository;
		this.supplierRepository = supplierRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		initData();
	}

	private void initData() {
		initProduct();
		initSupplier();
	}

	private void initSupplier() {
		Address supplierAddr = new Address("Jl. Karpan 1", "", "Ambon", "Maluku", "12345");
		Supplier SU001 = new Supplier("SU001", "0911-1239874", "tokoindah@yahoo.co.id", "Toko Indah", supplierAddr);

		supplierRepository.save(SU001);
	}

	private void initProduct() {
		Product dji = new Product("DJI SAM SOE KRETEK 12", "8999909028234", "Rokok DJI SAM SOE KRETEK 12", 20,
				BigDecimal.valueOf(12000));
		productRepository.save(dji);

		Product sampoerna = new Product("SAMPOERNA MILD FILTER 16", "8999909096004", "Rokok SAMPOERNA MILD FILTER 16",
				20, BigDecimal.valueOf(12000));
		productRepository.save(sampoerna);
	}
}
