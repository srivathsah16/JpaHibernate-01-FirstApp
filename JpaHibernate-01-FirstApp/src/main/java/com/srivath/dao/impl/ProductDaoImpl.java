package com.srivath.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.srivath.dao.ProductDao;
import com.srivath.entity.ProductEntity;

public class ProductDaoImpl implements ProductDao {

	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");

	public ProductEntity saveProduct(ProductEntity product) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction txn = manager.getTransaction();
		txn.begin();
		try {
			manager.persist(product);
			txn.commit();
			System.out.println("Product inserted into DB..");
		} catch (Exception e) {
			txn.rollback();
			System.out.println("Error while inserting product..." + e);
		} finally {
			manager.close();
		}
		return product;
	}

	public ProductEntity loadProductById(Integer productId) {
		/*
		 * find() -> early loading and getReference() -> lazy Load
		 */
		EntityManager manager = factory.createEntityManager();
		ProductEntity product = manager.find(ProductEntity.class, productId);
		manager.close();
		return product;
	}

	public ProductEntity updateProduct(Integer productId, Double new_unit_price) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction txn = manager.getTransaction();
		ProductEntity product = manager.getReference(ProductEntity.class, productId);
		txn.begin();
		product.setUnitPrice(new_unit_price);
		txn.commit();
		System.out.println("Product updated in DB...");
		manager.close();
		return product;
	}

	public void deleteProduct(Integer productId) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction txn = manager.getTransaction();
		ProductEntity product = manager.getReference(ProductEntity.class, productId);
		txn.begin();
		try {	
			manager.remove(product);
			txn.commit();
			System.out.println("Product deleted from DB...");
		} catch (Exception e) {
			txn.rollback();
			System.out.println("Error while deleting product from db..." + e);
		} finally {
			manager.close();
		}
	}

	public void testEntityStates() {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction txn = manager.getTransaction();

		/*
		 * creating new product
		 */
//		ProductEntity product = new ProductEntity(); // transient state
//		product.setProductId(123);
//		product.setProductName("Charger");
//		product.setQuantity(5);
//		product.setUnitPrice(1000.0);
//
//		txn.begin();
//		manager.persist(product); // persistent state
//		txn.commit();
		
		/*
		 * loading object from database
		 */

		ProductEntity product = manager.find(ProductEntity.class, 123); // obj loaded from db ->persistent state

		manager.detach(product); // detached state

		product.setUnitPrice(2500.0); // changes made to the object while in detached state, will not effect
										// in database
		manager.getTransaction().begin();
		manager.merge(product); // moving back to persistent state. Note: merger() should be called within
								// transaction.
		manager.getTransaction().commit();
		manager.close();

	}

}
