package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Order implements Serializable{

	private static final long serialVersionUID = 1L;

	public final static String DATE_FORMAT = "MM/dd/yyyy HH:mm";
	
	private int code;
	private String observations;
	private String clientRef;
	private int employeeRef;
	
	private LocalDate date;
	
	private ArrayList<Product> orderProducts;
	private ArrayList<Integer> amountPerEach;
	
	private double totalPrice;
	
	private State state;
	private ArrayList<Size> sizes;
	
	public Order(int cod, String obs, String clntRef, int mployeRef, LocalDate dte, ArrayList<Product> products, ArrayList<Integer> amountEach, ArrayList<Size> sizs) {
		
		code = cod;
		observations = obs;
		clientRef = clntRef;
		employeeRef = mployeRef;
		date = dte;
		orderProducts = products;
		amountPerEach = amountEach;
		calculateTotalPrice();
		state = State.REQUESTED;
		sizes = sizs;
	}
	
	public Order(int cod, String obs, String clntRef, int mployeRef, LocalDate dte, ArrayList<Product> products, ArrayList<Integer> amountEach, ArrayList<Size> sizs, String stae) {
		
		this(cod, obs, clntRef, mployeRef, dte, products, amountEach, sizs);
		state = State.valueOf(stae);
	}

	private void calculateTotalPrice() {
		
		double total = 0;

		for (int i = 0; i < amountPerEach.size(); i++) {
			
			Product tempProduct = orderProducts.get(i);
			double productPrice = tempProduct.getPrice();
			total += productPrice * amountPerEach.get(i);
		}
		
		totalPrice = total;
	}
	
	//Getters and setters
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getClientRef() {
		return clientRef;
	}

	public void setClientRef(String clientRef) {
		this.clientRef = clientRef;
	}

	public int getEmployeeRef() {
		return employeeRef;
	}

	public void setEmployeeRef(int employeeRef) {
		this.employeeRef = employeeRef;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public ArrayList<Product> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(ArrayList<Product> orderProducts) {
		this.orderProducts = orderProducts;
	}

	public ArrayList<Integer> getAmountPerEach() {
		return amountPerEach;
	}

	public void setAmountPerEach(ArrayList<Integer> amountPerEach) {
		this.amountPerEach = amountPerEach;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public void updateState() {
		switch (state) {
		case REQUESTED:
			state = State.PROCESS;
			break;
			
		case PROCESS:
			state = State.DELIVERED;
			break;
			
		case DELIVERED:
			state = State.DELIVERED;
			break;
			
		default:
			break;
		}
		
	}
	
	public int compareEmployeeRef(Order order) {
		return employeeRef - order.getEmployeeRef();
	}

	public ArrayList<Size> getSizes() {
		return sizes;
	}

	public void setSizes(ArrayList<Size> sizes) {
		this.sizes = sizes;
	}
	
}
