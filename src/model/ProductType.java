package model;

public enum ProductType {
	MAIN_DISH ("Plato principal"), DRINK ("Bebida"), ADDIOTIONAL_DISH ("Adición");

	private String type;
	
	private ProductType(String typ) {
		type = typ;
	}
	
	public String getType() {
		return type;
	}

}
