package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Product extends Saleable implements Comparable<Product>{
	
	private static final long serialVersionUID = 1L;

	public static final String DEFAULT_SIZE = "Standard";
	
	private ArrayList<Ingredient> ingredients;
	private ArrayList<Size> sizes;
	private ProductType type;
	private double price;
	
	public Product(String name, String creatRef, String lastE, int lastCode, ArrayList<Ingredient> ingrdints, ProductType typ) {
		super(name, creatRef, lastE, lastCode);
		setIngredients(ingrdints);
		sizes = new ArrayList<Size>();
		addSize(DEFAULT_SIZE);
		type = typ;
		price = 0;
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
		calculatePrice();
		return price;
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

	public void setPrice(double price) {
		this.price = price;
	}

	public ArrayList<Size> getSizes() {
		return sizes;
	}

	public Size getSizeByName(String sizeString) {
		
		Size size = null;
		
		boolean found = false;
		for (int i = 0; i < sizes.size() && !found; i++) {
			if (sizes.get(i).getSize().equals(sizeString)) {
				size = sizes.get(i);
				found = true;
			}
		}
		
		return size;
	}
	
	public void calculatePrice() {
		
		double total = 0.0;
		for (Ingredient ingredient : ingredients) {
			total += ingredient.getPrice();
		}
		
		price = total;
	}
	
	@Override
	public int compareTo(Product otherProduct) {
		
		int compare = 0;
		if (getPrice() - otherProduct.getPrice() > 0) { //The product has a greater price than the otheProduct
			compare = 1;
		} else if(getPrice() - otherProduct.getPrice() < 0) { //Other product has a greater price
			compare = -1;
		}
		
		return compare;
	}
	
}
