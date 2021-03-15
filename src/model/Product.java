package model;

import java.util.ArrayList;

public class Product {
	
	public static final String DEFAULT_SIZE = "Standard";
	
	private ArrayList<Ingredient> ingredients;
	private ArrayList<Size> sizes;
	private ProductType type;

	public Product(ArrayList<Ingredient> ingrdints, ProductType typ) {
		ingredients = ingrdints;
		sizes = new ArrayList<Size>();
		addSize(DEFAULT_SIZE);
		type = typ;
	}
	
	public void addSize(String sizeTxt) {
		Size size = new Size(sizeTxt);
		sizes.add(size);
	}
	
	public void addSize(String sizeTxt, double priceFactor) {
		Size size = new Size(sizeTxt, priceFactor);
		sizes.add(size);
	}

	public double getPrice() {
		
		return 0;
	}
}
