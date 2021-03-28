package model;

public enum ProductType {
	MAIN_DISH ("Plato principal"), DRINK ("Bebida"), ADDITIONAL_DISH ("Adici�n");

	private String type;
	
	private ProductType(String typ) {
		type = typ;
	}
	
	public String getType() {
		return type;
	}

}
