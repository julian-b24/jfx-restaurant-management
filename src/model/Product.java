package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Product extends Saleable{
	
	public static final String DEFAULT_SIZE = "Standard";
	
	private ArrayList<Ingredient> ingredients;
	private ArrayList<Size> sizes;
	private ProductType type;
	
	public Product(String name, String creatRef, String lastE, int lastCode, ArrayList<Ingredient> ingrdints, ProductType typ) {
		super(name, creatRef, lastE, lastCode);
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

	public void updateSizes(ArrayList<String> sizs, ArrayList<Double> sizesFactors) {
		
		Map<String, Double> mapSizes = new HashMap<String, Double>();
		
		//Build the dictionary/map with the size as the key and the factor as the value
		for (int i = 0; i < sizs.size(); i++) {
			mapSizes.put(sizs.get(i), sizesFactors.get(i));
		}
		
		//Creates the new list of sizes
		ArrayList<Size> tempSizes = new ArrayList<Size>();
		for (Map.Entry<String, Double> entry : mapSizes.entrySet()) {
			Size size = new Size(entry.getKey(), entry.getValue());
			tempSizes.add(size);
		}
		
		sizes = tempSizes;
	}

}
