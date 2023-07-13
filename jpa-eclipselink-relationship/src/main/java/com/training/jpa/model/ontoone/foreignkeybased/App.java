package com.training.jpa.model.ontoone.foreignkeybased;

import javax.persistence.EntityManager;

import com.training.jpa.util.EntityManagerUtil;

/**
 * Hello world!
 *
 */
public class App {
	private EntityManager entityManager = EntityManagerUtil.getEntityManager();

	public static void main(String[] args) {
		App appObject = new App();
		Address address = new Address();
		address.setCity("Bangalore");
		address.setStreet("Richmondstreet");

		User user = new User();
		user.setUserName("Tufail");
		user.setAddress(address);

		User user1 = appObject.saveUser(user);
		System.out.println("After Sucessfully insertion - 1-1 (FK Based) ");

	}

	public User saveUser(User user) {
		User u = new User();
		try {
			entityManager.getTransaction().begin();

			u = entityManager.merge(user);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}
		return u;
	}

	/*
	 * Student student2 = appObject.saveEmployee("Anant"); appObject.listStudent();
	 * 
	 * System.out.println("After Sucessfully modification ");
	 * appObject.updateStudent(student1.getStudentId(), "Mohammad Tufail");
	 * appObject.updateStudent(student2.getStudentId(), "Anant Sharma");
	 * appObject.listStudent(); System.out.println("After Sucessfully deletion ");
	 
	// example.deleteStudent(student2.getStudentId()); example.listStudent();

	public void listStudent() {
		try {
			entityManager.getTransaction().begin();
			@SuppressWarnings("unchecked")
			List<Student> Students = entityManager.createQuery("from Student").getResultList();
			for (Iterator<Student> iterator = Students.iterator(); iterator.hasNext();) {
				Student student = (Student) iterator.next();
				System.out.println(student.getStudentName());
			}
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}
	}

	public void updateStudent(Long studentId, String studentName) {
		try {
			entityManager.getTransaction().begin();
			Student student = (Student) entityManager.find(Student.class, studentId);
			student.setStudentName(studentName);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}
	}

	public void deleteStudent(Long studentId) {
		try {
			entityManager.getTransaction().begin();
			Student student = (Student) entityManager.find(Student.class, studentId);
			entityManager.remove(student);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}
	}
	
	*/
}
