package com.galaxe.training;

import javax.persistence.EntityManager;

import com.training.jpa.model.Contract_Employee;
import com.training.jpa.model.Regular_Employee;
import com.training.jpa.util.EntityManagerUtil;


public class App {
	private EntityManager entityManager = EntityManagerUtil.getEntityManager();

	public static void main(String[] args) {
		App appObject = new App();
		Contract_Employee contract_Employee = appObject.saveContractEmployee("Isha", 9000, 80);
		System.out.println("After Sucessfully insertion ");
		 appObject.saveRegularEmployee("Mohammad", 120000, 877);
		System.out.println("After Sucessfully insertion ");

	}

	public Contract_Employee saveContractEmployee(String employeeName, int payPerHour, int duration) {
		Contract_Employee contract_Employee = new Contract_Employee();

		try {
			entityManager.getTransaction().begin();
			contract_Employee.setName(employeeName);
			contract_Employee.setPay_per_hour(payPerHour);
			contract_Employee.setContract_duration(duration);
			contract_Employee = entityManager.merge(contract_Employee);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}
		return contract_Employee;
	}

	public void saveRegularEmployee(String employeeName, int salary, int bonus) {
		Regular_Employee regular_Employee = new Regular_Employee();

		try {
			entityManager.getTransaction().begin();
			regular_Employee.setName(employeeName);
			regular_Employee.setBonus(bonus);
			regular_Employee.setSalary(salary);
			entityManager.merge(regular_Employee);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}
		
	}
}
