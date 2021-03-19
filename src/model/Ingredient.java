package model;

import java.util.ArrayList;

public class Ingredient extends Saleable {

	private double price;
	private ArrayList<String> references;
	
	public Ingredient(String nm, String lastE, int lastCode, double value) {
		super(nm, lastE, lastCode);
		price = value;
		references = new ArrayList<String>();
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isReferenced() {
		return (references.size() == 0)?true:false;
	}

	public ArrayList<String> getReferences() {
		return references;
	}
	
	public void setReferences(ArrayList<String> references) {
		this.references = references;
	}
}
