package com.ofss.training.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {

	private static final EntityManagerFactory entityManagerFactory;
	
	static {
		try {
			entityManagerFactory =  Persistence.createEntityManagerFactory("ahmed");
		}
		catch(Throwable throwable) {
			throw new ExceptionInInitializerError(throwable);
		}
	}
	
	public static EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
}
