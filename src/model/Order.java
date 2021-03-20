package model;

import java.util.ArrayList;
import java.util.Date;

public class Order {

	private int code;
	private String observations;
	private String clientRef;
	private int employeeRef;
	
	private Date date;
	private Date time;
	
	private ArrayList<Product> orderProducts;
	private ArrayList<Integer> amountPerEach;
	
	private double totalPrice;
	
	private State state;
	
	public Order(int cod, String obs, String clntRef, int mployeRef, Date dte, Date tme, ArrayList<Product> products, ArrayList<Integer> amountEach) {
		
		code = cod;
		observations = obs;
		clientRef = clntRef;
		employeeRef = mployeRef;
		date = dte;
		time = tme;
		orderProducts = products;
		amountPerEach = amountEach;
		calculateTotalPrice();
		state = State.REQUESTED;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
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
	
}
