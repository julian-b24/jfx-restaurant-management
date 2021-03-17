package model;

import java.util.ArrayList;

public class Product extends Saleable{
	
	public static final String DEFAULT_SIZE = "Standard";
	
	private ArrayList<Ingredient> ingredients;
	private ArrayList<Size> sizes;
	private ProductType type;

	public Product(String name, String lastE, int lastCode, ArrayList<Ingredient> ingrdints, ProductType typ) {
		super(name, lastE, lastCode);
		setIngredients(ingrdints);
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

	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
}
