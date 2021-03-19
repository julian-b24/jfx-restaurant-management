package model;

import java.util.Comparator;

public class IngredientNameComparator implements Comparator <Ingredient>{

	public int compare(Ingredient ingr1, Ingredient ingr2) {
		return ingr1.getName().compareToIgnoreCase(ingr2.getName());
	}
}
