package com.training.jpa.namedquery;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.training.jpa.criteria.Item;
import com.training.jpa.util.EntityManagerUtil;

public class CriteriaApp {
	private static EntityManager entityManager = EntityManagerUtil.getEntityManager();

	public static void main(String[] args) {

		Query query = entityManager.createNamedQuery("Item_findByName", Item.class);
		query.setParameter("itemName", "Laptop");
		Item result = (Item) query.getSingleResult();

		System.out.println(result);
	}

}
