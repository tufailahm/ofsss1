package com.training.jpa.criteria;

import javax.persistence.EntityManager;

import com.training.jpa.util.EntityManagerUtil;

public class CriteriaAppSave {
	private EntityManager entityManager = EntityManagerUtil.getEntityManager();

	public static void main(String[] args) {

		CriteriaAppSave appSave = new CriteriaAppSave();
		appSave.saveItems();
		System.out.println("Item saved");
	}

	public void saveItems() {

		Item item1 = new Item();
		item1.setName("Laptop");
		item1.setGrade("A");
		item1.setColor("blue");

		Item item2 = new Item();
		item2.setName("Pendrive");
		item2.setGrade("A");
		item2.setColor("green");

		Item item3 = new Item();
		item3.setName("Mobile");
		item3.setGrade("B");
		item3.setColor("yellow");

		try {
			entityManager.getTransaction().begin();
			entityManager.merge(item1);
			entityManager.merge(item2);
			entityManager.merge(item3);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}

	}
}
