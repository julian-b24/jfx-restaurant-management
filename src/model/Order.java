package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order implements Comparable<Order>, Serializable{

	private static final long serialVersionUID = 1L;

	public final static String DATE_FORMAT = "MM/dd/yyyy HH:mm";
	
	private int code;
	private String observations;
	private String clientRef;
	private String stateString;
	private int employeeRef;
	
	private LocalDateTime date;
	
	private ArrayList<Product> orderProducts;
	private ArrayList<Integer> amountPerEach;
	
	private double totalPrice;
	
	private State state;
	private ArrayList<Size> sizes;
	
	public Order(int cod, String obs, String clntRef, int mployeRef, LocalDateTime dte, ArrayList<Product> products, ArrayList<Integer> amountEach, ArrayList<Size> sizs) {
		
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
		stateString = getState().getState();
		
	}
	
	public Order(int cod, String obs, String clntRef, int mployeRef, LocalDateTime dte, ArrayList<Product> products, ArrayList<Integer> amountEach, ArrayList<Size> sizs, String stae) {
		
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
	
	public String getProductNames() {
		
		String line="";
		
		for (int i = 0; i < orderProducts.size() ; i++) {
			line += orderProducts.get(i).getName()+",";
		}
		
		return line;
	}
	
	
	//Getters and setters
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void updateStateString() {
		stateString = state.getState();
	}
	
	public String getStateString() {
		return stateString;
	}

	public void setStateString(String stateString) {
		this.stateString = stateString;
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

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
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
		calculateTotalPrice();
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
			state = State.RECIEVED;
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
	
	public void generateProductsReferences() {
		
		for (Product product : orderProducts) {
			product.addReference(getCode());
		}
		
	}

	@Override
	public int compareTo(Order otherOrdr) {
		return date.compareTo(otherOrdr.getDate());
	}
	
	public String toString() {
		
		String stringO = "order\n"+ 
						"Prodcuts names: "+getProductNames()+"\n"+
						"amount per each: "+ getAmounts()+"\n"+
						"sizes: "+ getSizesString()+"\n"+
						"total price: "+ totalPrice;
		
		return stringO;
	}
	
	public String getAmounts() {
		String amountsP = "";
		for (Integer integer : amountPerEach) {
			amountsP+=integer+",";
		}
		
		return amountsP;
	}
	
	public 	String getSizesString() {
		String lineX = "";
		
		for (Size size : sizes) {
			lineX += size.getSize()+",";
		}
		
		return lineX;
	}
}
