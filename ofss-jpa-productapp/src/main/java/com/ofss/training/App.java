package com.ofss.training;

import javax.persistence.EntityManager;

import com.ofss.training.model.Product;
import com.ofss.training.util.EntityManagerUtil;

public class App 
{
	private EntityManager  entityManager = EntityManagerUtil.getEntityManager();
	
    public static void main( String[] args )
    {
    	App app = new App();
       Product product = new Product();
       product.setProductName("HPLaptop");
       product.setQuantityOnHand(30);
       product.setPrice(99999);
       System.out.println("Saving product information using jpa");
       app.saveProduct(product);
       System.out.println("Product saved");

    }
    
    public void saveProduct(Product product) {
    	try {
			entityManager.getTransaction().begin();
			entityManager.merge(product);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			System.out.println("Exception occurred "+e);
		}
    }
}
