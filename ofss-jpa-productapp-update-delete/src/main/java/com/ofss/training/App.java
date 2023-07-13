package com.ofss.training;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ofss.training.model.Product;
import com.ofss.training.util.EntityManagerUtil;

/*use case 2: How we can update and remove data from database using jpa
 * 
 */
public class App {
	private EntityManager entityManager = EntityManagerUtil.getEntityManager();

	public static void main(String[] args) {
		App app = new App();
		/*
		 * Product product = new Product();
		 * 
		 * product.setProductId(3);
		 * 
		 * //new details product.setProductName("Lenovo");
		 * product.setQuantityOnHand(130); product.setPrice(120);
		 * 
		 * System.out.println("Updating product information using jpa");
		 * app.updateProduct(product); System.out.println("Product updated");
		 */

		/*
		 * int productId = 2;
		 * System.out.println("Deleting product information using jpa");
		 * app.deleteProduct(productId); System.out.println("Product deleted");
		 */
		Product product = new Product();
		product.setProductName("HPLaptop");
		product.setQuantityOnHand(30);
		product.setPrice(1);
		app.saveProduct(product);
		System.out.println("Product saved");
		System.out.println("######## Printing all the products");
		app.viewProducts();
		System.out.println("######## Printing details about product id : 3");
		app.viewProductByProductId(3);
		System.out.println("######## Printing details about product id : 1 (using find method) ");
		app.viewProductByProductId(1);
		String productName = "HPLaptop";
        System.out.println("Viewing product information for productName: " + productName);
        app.viewProductByProductName2(productName);
        
        System.out.println("==================Named  Query");
        app.viewProductByProductNameNamedQuery("HPLaptop");
	}

	// fetch all the products records from database
	public void viewProducts() {
		try {
			entityManager.getTransaction().begin();
			List<Product> products = entityManager.createQuery("from Product").getResultList();
			Iterator<Product> iterator = products.iterator();

			while (iterator.hasNext()) {
				System.out.println(iterator.next()); // toString in product
			}
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			System.out.println("Exception occurred " + e);
		}
	}

	// fetch all the products records from database
	public void viewProductByProductId(int productId) {
		// To do
		try {
			entityManager.getTransaction().begin();
			List<Product> products = entityManager.createQuery("from Product where productId = " + productId)
					.getResultList();
			Iterator<Product> iterator = products.iterator();

			while (iterator.hasNext()) {
				System.out.println(iterator.next()); // toString in product
			}
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			System.out.println("Exception occurred " + e);
		}
	}

	// fetch the product information using find method
	public void viewProductByProductIdUsingFind(int productId) {
		try {
			entityManager.getTransaction().begin();
			Product product = entityManager.find(Product.class, productId);
			System.out.println(product);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			System.out.println("Exception occurred " + e);
		}
	}
	
	// fetch the product information by productName
	public void viewProductByProductName(String productName){
		try {
			entityManager.getTransaction().begin();
			List<Product> products = entityManager.createQuery("from Product where productName= :productName",Product.class).setParameter("productName" , productName).getResultList();
			Iterator<Product> iterator = products.iterator();

			while (iterator.hasNext()) {
				System.out.println(iterator.next()); // toString in product
			}
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			System.out.println("Exception occurred " + e);
		}
	}
	
	// fetch the product information by productName using +
	//select * from products where productName = 'HpLaptop'
	public void viewProductByProductName2(String productName){
		try {
			entityManager.getTransaction().begin();
			List<Product> products=entityManager.createQuery("from Product where productName= '"+productName+"'").getResultList();
			Iterator<Product> iterator = products.iterator();

			while (iterator.hasNext()) {
				System.out.println(iterator.next()); // toString in product
			}
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			System.out.println("Exception occurred " + e);
		}
	}
	
	// fetch the product information by productName using named query
	//select * from products where productName = 'HpLaptop'
	public void viewProductByProductNameNamedQuery(String productName){
		try {
			entityManager.getTransaction().begin();
			
			Query query =entityManager.createNamedQuery("Product_findByProductName",Product.class);
			query.setParameter("pName", productName);
			List<Product> products = query.getResultList();
			
			Iterator<Product> iterator = products.iterator();

			while (iterator.hasNext()) {
				System.out.println(iterator.next()); // toString in product
			}
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			System.out.println("Exception occurred " + e);
		}
	}

	// fetch the product information by productName using named query
	//select * from products where productName = 'HpLaptop'
	public void viewProductByProductNameNamedQuery(String productName,int price){
		try {
			entityManager.getTransaction().begin();
			//To do 
			Query query =entityManager.createNamedQuery("Product_findByProductName",Product.class);
			query.setParameter("pName", productName);
			List<Product> products = query.getResultList();
			
			Iterator<Product> iterator = products.iterator();

			while (iterator.hasNext()) {
				System.out.println(iterator.next()); // toString in product
			}
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			System.out.println("Exception occurred " + e);
		}
	}
	
	

	public void deleteProduct(int productId) {
		try {
			entityManager.getTransaction().begin();
			Product oldProduct = entityManager.find(Product.class, productId); // fetching the product with the product
																				// id
			entityManager.remove(oldProduct); // removing from database
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			System.out.println("Exception occurred " + e);
		}
	}

	public void updateProduct(Product newProduct) {
		try {
			entityManager.getTransaction().begin();
			Product oldProduct = entityManager.find(Product.class, newProduct.getProductId()); // old details of the
																								// product

			oldProduct.setProductName(newProduct.getProductName());
			oldProduct.setQuantityOnHand(newProduct.getQuantityOnHand());
			oldProduct.setPrice(newProduct.getPrice());

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			System.out.println("Exception occurred " + e);
		}
	}

	public void saveProduct(Product product) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(product);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			System.out.println("Exception occurred " + e);
		}
	}
}
