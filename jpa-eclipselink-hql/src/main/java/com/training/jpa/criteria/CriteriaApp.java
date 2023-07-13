package com.training.jpa.criteria;

import javax.persistence.EntityManager;

import com.training.jpa.util.EntityManagerUtil;

public class CriteriaApp {
	private EntityManager entityManager = EntityManagerUtil.getEntityManager();

	public static void main(String[] args) {

		CustomItemRepositoryImpl impl = new CustomItemRepositoryImpl();
		System.out.println("Item displayed");
		
		System.out.println(impl.findItemsByColorAndGrade());
	}

	
}
