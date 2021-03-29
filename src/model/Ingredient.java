package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Ingredient extends Saleable implements Comparable<Ingredient>, Serializable{

	private static final long serialVersionUID = 1L;
	
	private double price;
	private ArrayList<Integer> references;
	
	public Ingredient(String nm, String creatRef, String lastE, int lastCode, double value) {
		super(nm, creatRef, lastE, lastCode);
		price = value;
		references = new ArrayList<Integer>();
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

	public ArrayList<Integer> getReferences() {
		return references;
	}
	
	public void setReferences(ArrayList<Integer> references) {
		this.references = references;
	}

	public void addReference(int code) {
		
		boolean isIn = false;
		for (int i = 0; i < references.size() && !isIn; i++) {
			if (references.get(i) == code) {
				isIn = true;
			}
		}
		
		if (!isIn) {
			references.add(code);
		}
	}

	//Compares by code
	@Override
	public int compareTo(Ingredient otherIngredient) {
		return getCode() - otherIngredient.getCode();
	}
	
}
