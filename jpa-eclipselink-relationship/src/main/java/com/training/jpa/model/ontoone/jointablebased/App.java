package com.training.jpa.model.ontoone.jointablebased;

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
		WorkStation workStation = new WorkStation();
		workStation.setFloor("9");
		workStation.setWorkstationNumber(191717);
		
		Employee employee = new Employee();
		employee.setName("Tufail");
		employee.setWorkStation(workStation);

		workStation.setEmployee(employee);
		
		
		Employee employee2 = appObject.saveEmployee(employee);
		System.out.println("After Sucessfully insertion - 1-1 (Join Table Based) ");

	}

	public Employee saveEmployee(Employee employee) {
		Employee employee2 = new Employee();
		try {
			entityManager.getTransaction().begin();

			employee2 = entityManager.merge(employee);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}
		return employee2;
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
