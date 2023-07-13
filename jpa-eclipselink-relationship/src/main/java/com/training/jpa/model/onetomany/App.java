package com.training.jpa.model.onetomany;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import com.training.jpa.util.EntityManagerUtil;

/**
 * In this approach third table would get created
 *
 */
public class App {
	private EntityManager entityManager = EntityManagerUtil.getEntityManager();

	public static void main(String[] args) {
		App appObject = new App();

		appObject.saveCart();
		System.out.println("After Sucessfully insertion - 1-M  ");

	}

	public Cart saveCart() {
		Cart cart2 = new Cart();
		Cart cart = new Cart();

		Item item1 = new Item(cart);
		Item item2 = new Item(cart);

		Set<Item> itemsSet = new HashSet<Item>();
		itemsSet.add(item1);
		itemsSet.add(item2);

		cart.setItems(itemsSet);

		try {
			entityManager.getTransaction().begin();
			entityManager.merge(cart);
			entityManager.merge(item1);
			entityManager.merge(item2);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}
		return cart2;
	}

	/*
	 * Student student2 = appObject.saveEmployee("Anant"); appObject.listStudent();
	 * 
	 * System.out.println("After Sucessfully modification ");
	 * appObject.updateStudent(student1.getStudentId(), "Mohammad Tufail");
	 * appObject.updateStudent(student2.getStudentId(), "Anant Sharma");
	 * appObject.listStudent(); System.out.println("After Sucessfully deletion ");
	 * 
	 * // example.deleteStudent(student2.getStudentId()); example.listStudent();
	 * 
	 * public void listStudent() { try { entityManager.getTransaction().begin();
	 * 
	 * @SuppressWarnings("unchecked") List<Student> Students =
	 * entityManager.createQuery("from Student").getResultList(); for
	 * (Iterator<Student> iterator = Students.iterator(); iterator.hasNext();) {
	 * Student student = (Student) iterator.next();
	 * System.out.println(student.getStudentName()); }
	 * entityManager.getTransaction().commit(); } catch (Exception e) {
	 * entityManager.getTransaction().rollback(); } }
	 * 
	 * public void updateStudent(Long studentId, String studentName) { try {
	 * entityManager.getTransaction().begin(); Student student = (Student)
	 * entityManager.find(Student.class, studentId);
	 * student.setStudentName(studentName); entityManager.getTransaction().commit();
	 * } catch (Exception e) { entityManager.getTransaction().rollback(); } }
	 * 
	 * public void deleteStudent(Long studentId) { try {
	 * entityManager.getTransaction().begin(); Student student = (Student)
	 * entityManager.find(Student.class, studentId); entityManager.remove(student);
	 * entityManager.getTransaction().commit(); } catch (Exception e) {
	 * entityManager.getTransaction().rollback(); } }
	 * 
	 */
}
