package model;

import java.util.ArrayList;

public class Restaurant {

	private ArrayList<Product> products;
	private ArrayList<Ingredient> ingredients;
	private ArrayList<Client> clients;
	private ArrayList<Employee> employees;
	private ArrayList<SystemUser> systemUsers;
	
	public Restaurant() {
		
		systemUsers = new ArrayList<>();
		products = new ArrayList<>();
		ingredients = new ArrayList<>();
		clients = new ArrayList<>();
		employees = new ArrayList<>();
	}
	
	public void createSystemUser(String nam, String lastN, String ccP, String user, String passw) {
		
		SystemUser newUser = new SystemUser(nam, lastN, ccP, user, passw);
		systemUsers.add(newUser);
		
	}
	
	public void createEmployee(String nam, String lastN, String ccP) {
		
		Employee newEmployee = new Employee(nam, lastN, ccP);
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
		ArrayList<Ingredient> tempIngredients = getIngredientsByIdx(ingredientsIdx);
		
		//Cast of type
		ProductType type = ProductType.valueOf(typ.toUpperCase().replace(" ", "_"));
		
		int lastCode;
		if(products.size() > 0) {
			Product lastProduct = products.get(products.size() -1);
			lastCode = lastProduct.getCode();
			
		} else {
			lastCode = 0;
		}
		
		Product product = new Product(name, lastEditor, lastCode, tempIngredients, type);
		products.add(product);
	}
	
	public ArrayList<Ingredient> getIngredientsByIdx(ArrayList<Integer> ingredientsIdx) {
		
		ArrayList<Ingredient> tempIngredients = new ArrayList<Ingredient>();
		for (int i = 0; i < ingredientsIdx.size(); i++) {
			Ingredient tempIngredient = ingredients.get(ingredientsIdx.get(i));
			tempIngredients.add(tempIngredient);
		}
		
		return tempIngredients;
	}
	
	//Req 1.2
	public void updateProduct(String name, String lastEditor, int code, ArrayList<Integer> ingredientsIdx, String typ, boolean available, ArrayList<String> sizes, ArrayList<Double> sizesFactors) {
		
		Product product = getProductByCode(code);
		product.setName(name);
		product.setLastEditorRef(lastEditor);
		
		ProductType type = ProductType.valueOf(typ.toUpperCase().replace(" ", "_"));
		product.setType(type);
		product.setAvailable(available);
		
		ArrayList<Ingredient> tempIngredients = getIngredientsByIdx(ingredientsIdx);
		product.setIngredients(tempIngredients);
		
		product.updateSizes(sizes, sizesFactors);
	}
	
	public Product getProductByCode(int code) {
		
		Product product = null;
		
		for (int i = 0; i < products.size(); i++) {
			
			if (products.get(i).getCode() == code) {
				product = products.get(i);
			}
		}
		
		return  product;
	}
	
	//Req 1.3
	public void deleteProduct(int code) {
		
		boolean found = false;	
		int idx = 0;
		
		while (!found) {
			Product product = products.get(idx);
			if(product.getCode() == code) {
				found = true;
			}
			idx++;
		}
		
		if (found) {
			removeProductReferences(code);
			products.remove(idx);
		}
	}

	public void removeProductReferences(int code) {

		for (Ingredient ingredient : ingredients) {
			
			if (ingredient.isReferenced()) {
				
				ArrayList<String> references = ingredient.getReferences();
				boolean found = false;
				
				for (int i = 0; i < references.size() && !found; i++) {
					
					int tempReference = Integer.parseInt(references.get(i));
					
					if (tempReference == code) {
						ingredient.getReferences().remove(i);
						found = true;
					}
				}
			}
		}
	}

	//Req 1.4
	public void diseableProduct(int code) {
		
		Product product = getProductByCode(code);
		product.setAvailable(false);
	}
	
	public ArrayList<Client> getClients() {
		return clients;
	}

	public void setClients(ArrayList<Client> clients) {
		this.clients = clients;
	}

	public ArrayList<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(ArrayList<Employee> employees) {
		this.employees = employees;
	}

	public ArrayList<SystemUser> getSystemUsers() {
		return systemUsers;
	}

	public void setSystemUsers(ArrayList<SystemUser> systemUsers) {
		this.systemUsers = systemUsers;
	}
	
	
	
}
