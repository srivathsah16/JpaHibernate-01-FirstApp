package com.srivath.dao;

import com.srivath.entity.ProductEntity;

public interface ProductDao {
	ProductEntity saveProduct(ProductEntity product);

	ProductEntity loadProductById(Integer productId);

	ProductEntity updateProduct(Integer productId, Double new_unit_price);

	void deleteProduct(Integer productId);

	void testEntityStates();
}
