package model;

import java.util.ArrayList;

public class Restaurant {

	private ArrayList<Product> products;
	private ArrayList<Ingredient> ingredients;
	private ArrayList<Client> clients;
	private ArrayList<Employee> employees;
	private ArrayList<SystemUser> systemUsers;
	
	public Restaurant() {
		
		products = new ArrayList<>();
		ingredients = new ArrayList<>();
		clients = new ArrayList<>();
		employees = new ArrayList<>();
	}
	
	public void createSystemUser(String nam, String lastN, String ccP, String emplId, String user, String passw) {
		
		SystemUser newUser = new SystemUser(nam, lastN, ccP, emplId, user, passw);
		systemUsers.add(newUser);
		
	}
	
	public void createEmployee(String nam, String lastN, String ccP, String emplId) {
		
		Employee newEmployee = new Employee(nam, lastN, ccP, emplId);
		employees.add(newEmployee);
	}
	
	public void createClient(String nam, String lastN, String ccP, String adrs, String phn, String obs){
		
		int pos = 0;
		Client clientX = new Client(nam, lastN, ccP, adrs, phn, obs);
		if(clients.size()>0) {
			
			pos = binarySearch(lastN+nam, clients);
			if(pos==-1) {
				clients.add(clientX);
			}else {
				clients.add(pos, clientX);
			}
		}else {
			clients.add(clientX);
		}
	}
	
	public int binarySearch(String fullName, ArrayList<Client> clients) {
		
		int pos = -2;
		int in = 0;
		int fin = clients.size()-1;
		
		while(in<=fin && pos==-2) {
			int middle = (in+fin)/2;
			
			if((clients.get(middle).getLastName()+clients.get(middle).getName()).equalsIgnoreCase(fullName)) {
				pos = middle;
			}
			
			if(in!=fin) {
				if(fullName.compareTo(clients.get(middle).getLastName()+clients.get(middle).getName())>0) {
					fin = middle-1;
				}else {
					in = middle+1;
				}
			}else {
				if(fullName.compareTo(clients.get(middle).getLastName()+clients.get(middle).getName())>0) {
					pos = middle;
					}else {
						pos = middle+1;
					}
			}				
		}
		if(pos<0) {
			pos=0;
		}else if(pos>clients.size()-1) {
			pos = -1;
		}
		
		return pos;
	}
	
	//Req 1.1
	public void createProduct(String name, String lastEditor, ArrayList<Integer> ingredientsIdx, String typ) {
		
		//Getting ingredients from list of ingredients
		ArrayList<Ingredient> tempIngredients = new ArrayList<Ingredient>();
		for (int i = 0; i < ingredientsIdx.size(); i++) {
			Ingredient tempIngredient = ingredients.get(ingredientsIdx.get(i));
			tempIngredients.add(tempIngredient);
		}
		
		//Cast of type
		ProductType type = ProductType.valueOf(typ.toUpperCase().replace(" ", "_"));
		
		Product lastProduct = products.get(products.size() -1);
		int lastCode = Integer.parseInt(lastProduct.getCode());
		
		Product product = new Product(name, lastEditor, lastCode, tempIngredients, type);
		products.add(product);
	}
	
}
