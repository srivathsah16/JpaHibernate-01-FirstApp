package com.srivath.client;

import com.srivath.dao.ProductDao;
import com.srivath.dao.impl.ProductDaoImpl;
import com.srivath.entity.ProductEntity;

public class Test {
	public static void main(String[] args) {
		ProductDao dao = new ProductDaoImpl();
//		ProductEntity product1 = new ProductEntity();
//		product1.setProductId(3);
//		product1.setProductName("Eraser");
//		product1.setUnitPrice(30d);
//		product1.setQuantity(8);
//		dao.saveProduct(product1);
		
		
	ProductEntity product2=	dao.loadProductById(2);
	System.out.println(product2);
		
//	ProductEntity product=	dao.updateProduct(1, 76.0);
//	System.out.println(product);
		
//		dao.deleteProduct(3);
		
//		dao.testEntityStates();
		
	}
}
