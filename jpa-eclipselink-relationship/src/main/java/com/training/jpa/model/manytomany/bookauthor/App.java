package com.training.jpa.model.manytomany.bookauthor;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import com.training.jpa.util.EntityManagerUtil;

/**
 * In this approach three tables would get created
 *
 */
public class App {
	private EntityManager entityManager = EntityManagerUtil.getEntityManager();

	public static void main(String[] args) {
		App appObject = new App();

		appObject.saveBookAndAuthor();
		System.out.println("After Sucessfully insertion - M-M  ");

	}

	public void saveBookAndAuthor() {
	Book book1 = new Book();
	book1.setTitle("Java");

	Book book2 = new Book();
	book2.setTitle("Angular");
	
	Book book3 = new Book();
	book3.setTitle("Devops");
	
	Author author1 = new Author();
	author1.setName("Mohammad");
	
	
	Author author2 = new Author();
	author2.setName("Tufail");
	
	
		Set<Author> authorsForBooks = new HashSet<Author>();
		authorsForBooks.add(author1);
		authorsForBooks.add(author2);
		
		Set<Book> booksWrittenByAuthors= new HashSet<Book>();
		booksWrittenByAuthors.add(book1);
		booksWrittenByAuthors.add(book2);

		author1.setBooks(booksWrittenByAuthors);
		book1.setAuthors(authorsForBooks);
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(book1);
			entityManager.merge(book2);
			entityManager.merge(book3);
			entityManager.merge(author1);
			entityManager.merge(author2);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}
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
