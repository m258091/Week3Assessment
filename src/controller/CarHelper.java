package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Car;

public class CarHelper {
	
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("CarDealer"); 

	public void insertCar(Car car) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(car);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Car> showAllItems() {
		EntityManager em = emfactory.createEntityManager();
		List<Car> allItems = em.createQuery("SELECT car FROM Car car").getResultList();
		return allItems;
	}
	
	public void deleteCar(Car toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Car> typedQuery = em.createQuery("select car from Car car where car.make = :selectedMake and car.model = :selectedModel and car.year = :selectedYear", Car.class);
		
		typedQuery.setParameter("selectedMake", toDelete.getMake());
		typedQuery.setParameter("selectedModel", toDelete.getModel());
		typedQuery.setParameter("selectedYear", toDelete.getYear());
		
		typedQuery.setMaxResults(1);
		
		Car result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	public Car searchForCarById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		Car found = em.find(Car.class, idToEdit);
		em.close();
		return found;
	}

	public void updateCar(Car toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

	public List<Car> searchForCarByMake(String make) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Car> typedQuery = em.createQuery("select car from Car car where car.make = :selectedMake", Car.class);
		typedQuery.setParameter("selectedMake", make);
		
		List<Car> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	

	public List<Car> searchForCarByModel(String model) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Car> typedQuery = em.createQuery("select car from Car car where car.model = :selectedModel", Car.class);
		typedQuery.setParameter("selectedModel", model);
		
		List<Car> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public List<Car> searchForCarByYear(int year) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Car> typedQuery = em.createQuery("select car from Car car where car.year = :selectedYear", Car.class);
		typedQuery.setParameter("selectedYear", year);
		
		List<Car> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public void cleanUp() {
		emfactory.close();
	}
}
