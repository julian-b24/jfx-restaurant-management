package model;

public class Ingredient extends Saleable {

	private double price;
	
	public Ingredient(String nm, String lastE, int lastCode, double value) {
		super(nm, lastE, lastCode);
		price = value;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
