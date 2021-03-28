package model;

import java.io.Serializable;

public class Size implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private static final double DEFAULT_PRICE_FACTOR = 1;
	
	private String size;
	private double priceFactor;

	public Size(String sze) {
		size = sze;
		priceFactor = DEFAULT_PRICE_FACTOR;
	}
	
	public Size(String sze, double priceFact) {
		size = sze;
		priceFactor = priceFact;
	}

	//Getters and setters
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public double getPriceFactor() {
		return priceFactor;
	}

	public void setPriceFactor(double priceFactor) {
		this.priceFactor = priceFactor;
	}
	
}
