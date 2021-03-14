package model;

import java.util.ArrayList;

public class Restaurant {

	private ArrayList<Product> products;
	private ArrayList<Ingredient> ingredients;
	private ArrayList<Client> clients;
	private ArrayList<Employee> employees;
	private ArrayList<SystemUser> systemUsers;
	
	public Restaurant() {
		
		products = new ArrayList<>();
		ingredients = new ArrayList<>();
		clients = new ArrayList<>();
		employees = new ArrayList<>();
	}
	
	public void createSystemUser(String nam, String lastN, String ccP, String emplId, String user, String passw) {
		
		SystemUser newUser = new SystemUser(nam, lastN, ccP, emplId, user, passw);
		systemUsers.add(newUser);
		
	}
	
	public void createEmployee(String nam, String lastN, String ccP, String emplId) {
		
		Employee newEmployee = new Employee(nam, lastN, ccP, emplId);
		employees.add(newEmployee);
	}
	
	public void crateClient(String nam, String lastN, String ccP, String adrs, String phn, String obs) {
		
	}
	
	public void insertOrginzedClient() {
		
	}
	
}
