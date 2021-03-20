package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Restaurant {

	public final static String INGREDIENTS_PATH = "data/csv/ingredients.csv";
	public final static String CLIENTS_PATH = "data/csv/clients.csv";
	public final static String PRODUCTS_PATH = "data/csv/products.csv";
	public final static String ORDERS_PATH = "data/csv/orders.csv";
	
	private ArrayList<Product> products;
	private ArrayList<Ingredient> ingredients;
	private ArrayList<Client> clients;
	private ArrayList<Employee> employees;
	private ArrayList<SystemUser> systemUsers;
	private ArrayList<Order> orders;
	
	public Restaurant() {
		
		systemUsers = new ArrayList<>();
		products = new ArrayList<>();
		ingredients = new ArrayList<>();
		clients = new ArrayList<>();
		employees = new ArrayList<>();
	}
	
	public void createSystemUser(String nam, String lastN, String ccP, String user, String passw) {
		
		int lastId = 0;
		if(systemUsers.size()>0) {
			Employee emplX = employees.get(employees.size()-1);
			lastId = emplX.getEmployeeId();
			SystemUser newUser = new SystemUser(nam, lastN, ccP, user, passw, lastId);
		}else {
			SystemUser newUser = new SystemUser(nam, lastN, ccP, user, passw, lastId);
			systemUsers.add(newUser);
		}		
	}
	
	public void createEmployee(String nam, String lastN, String ccP) {
		
		int lastId = 0;
		if(employees.size()>0) {
			Employee emplX = employees.get(employees.size()-1);
			lastId = emplX.getEmployeeId();
			Employee newEmployee = new Employee(nam, lastN, ccP, lastId);
		}else {
			Employee newEmployee = new Employee(nam, lastN, ccP, lastId);
			employees.add(newEmployee);
		}
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
	public void createProduct(String name, String creatorRef, String lastEditor, ArrayList<Integer> ingredientsCodes, String typ) {
		
		//Getting ingredients from list of ingredients
		ArrayList<Ingredient> tempIngredients = getIngredientsByCode(ingredientsCodes);
		
		//Cast of type
		ProductType type = ProductType.valueOf(typ.toUpperCase().replace(" ", "_"));
		
		int lastCode;
		if(products.size() > 0) {		//Change if statement for method getLastCode(ArrayList)
			Product lastProduct = products.get(products.size() -1);
			lastCode = lastProduct.getCode();
			
		} else {
			lastCode = 0;
		}
		
		Product product = new Product(name, creatorRef, lastEditor, lastCode, tempIngredients, type);
		products.add(product);
	}
	
	public ArrayList<Ingredient> getIngredientsByCode(ArrayList<Integer> codes) {
		
		ArrayList<Ingredient> tempIngredients = new ArrayList<Ingredient>();
		
		boolean stop = false;
		int j = 0;
		for (int i = 0; i < ingredients.size() && !stop; i++) {
			
			if (ingredients.get(i).getCode() == codes.get(j)) {
				Ingredient tempIngredient = ingredients.get(i);
				tempIngredients.add(tempIngredient);
				j++;
			}
			
			if (codes.size() == tempIngredients.size()) {
				stop = true;
			}
		}
		return tempIngredients;
	}
	
	//Req 1.2
	public void updateProduct(String name, String lastEditor, int code, ArrayList<Integer> ingredientsCodes, String typ, boolean available, ArrayList<String> sizes, ArrayList<Double> sizesFactors) {
		
		Product product = getProductByCode(code);
		product.setName(name);
		product.setLastEditorRef(lastEditor);
		
		ProductType type = ProductType.valueOf(typ.toUpperCase().replace(" ", "_"));
		product.setType(type);
		product.setAvailable(available);
		
		ArrayList<Ingredient> tempIngredients = getIngredientsByCode(ingredientsCodes);
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
	
	//Req 1.8
	public void createOrder(ArrayList<Integer> productsCodes, ArrayList<Integer> productsAmounts, String clientRef, 
							int employeeRef, Date dateRequest, Date timeRequest, String obs) {
		
		/*
		Note: Orders could be implemented with a HashMap<Product, Integer>
		
		Map <Product, Integer> productsMap = new HashMap<Product, Integer>();
		for (int i = 0; i < productsCodes.size(); i++) {
			Product tempProduct = getProductByCode(productsCodes.get(i));
			Integer tempAmount = productsAmounts.get(i);
			productsMap.put(tempProduct, tempAmount);
		}
		 */
		ArrayList<Product> productsOrdered = new ArrayList<Product>();
		for (Integer code : productsCodes) {
			Product tempProduct = getProductByCode(code);
			productsOrdered.add(tempProduct);
		}
		
		//Builds the code of the order
		int code = 0;
		if (orders.size() > 0) {
			code = orders.get(orders.size() - 1).getCode() + 1;
		}
		
		Order order = new Order(code, obs, clientRef, employeeRef, dateRequest, timeRequest, productsOrdered, productsAmounts);
		orders.add(order);
	}
	
	//Req 1.9
	public void updateStateOrder(int code) {
		
		Order order = getOrderByCode(code);
		order.updateState();
	}
	
	public Order getOrderByCode(int code) {
		
		Order order = null;
		
		//binary search
		int low = 0;
		int top = orders.size() - 1;
		boolean found = false;
		
		while(low < top && !found) {
			
			int mid = (low + top)/2;
			if (orders.get(mid).getCode() == code) {
				order = orders.get(mid);
				found = true;
			
			} else if (orders.get(mid).getCode() < code) {
				low = mid + 1;
				
			} else {
				top = mid - 1;
			}
		}
		
		return order;
	}

	//create ingredient
	public void createIngredient(String name, String creatorRef, String lastE, String val){
		
		int lastCode = 0;
		double value = Double.parseDouble(val);
		Ingredient ingredientX; 
		
		if(ingredients.size()>0) {
			lastCode = ingredients.get(ingredients.size() - 1).getCode();
			ingredientX = new Ingredient(name, creatorRef, lastE, lastCode, value);
			boolean alreadyAdded = searchIngredient(name);
			if(!alreadyAdded) {
				ingredients.add(ingredientX);
			}
		}else {			
			ingredientX = new Ingredient(name, creatorRef, lastE, lastCode, value);
			ingredients.add(ingredientX);
		}
		sortIngredientByName();
		
	}
	
	//sort ingredients
	public void sortIngredientByName() {
		Comparator<Ingredient> ingredientNameComparator = new IngredientNameComparator();
		Collections.sort(ingredients, ingredientNameComparator);
	}
	
	//search ingredient by name
	public boolean searchIngredient(String name) {
		
		int low = 0;
		int top = ingredients.size() - 1;
		boolean found = false;
		
		while(low < top && !found) {
			
			int mid = (low + top)/2;
			if (ingredients.get(mid).getName().equalsIgnoreCase(name)) {
				found = true;
			} else if (ingredients.get(mid).getName().compareTo(name) < 0) {
				low = mid + 1;
			} else {
				top = mid - 1;
			}
		}
		return found;
	}
	
	//Import Ingredients
	public void importIngredients() throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(INGREDIENTS_PATH));
		br.readLine(); //Read first line
		
		String line = br.readLine();
		while (line != null) {
			String[] values = line.split(",");
			String name = values[0];
			String creatorRef = values[1];		//Add field to constructor of Saleable
			String lastEditorRef = values[2];
			String price = values[3];
			createIngredient(name, creatorRef, lastEditorRef, price);
		}
		
		br.close();
	}
	
	//Req 4.1
	public void importClients() throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(CLIENTS_PATH));
		br.readLine(); //Read first line
		
		String line = br.readLine();
		while (line != null) {
			String[] values = line.split(",");
			String firstName = values[0];
			String lastName = values[1];
			String cc = values[2];
			String address = values[3];
			String phone = values[4];
			String obs = values[5];
			createClient(firstName, lastName, cc, address, phone, obs);
		}
		
		br.close();
	}
	
	//Req 4.2
	public void importProducts() throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(PRODUCTS_PATH));
		br.readLine(); //Read first line
		
		String line = br.readLine();
		while (line != null) {
			String[] values = line.split(",");
			String name = values[0];
			String creatorRef = values[1];
			String lastEditorRef = values[2];
			ArrayList<Integer> codes = new ArrayList<Integer>();
			codes.add(Integer.parseInt(values[3]));
			codes.add(Integer.parseInt(values[4]));
			codes.add(Integer.parseInt(values[5]));
			String type = values[6];
			createProduct(name, creatorRef, lastEditorRef, codes, type);
		}
		
		br.close();
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

	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public void updateIngredient(String referenceIngredient, String newName, String newValue) {
		System.out.println(referenceIngredient+","+newName);
		
		int index = binarySearchIng(referenceIngredient, ingredients);
		ingredients.get(index).setName(newName);
		ingredients.get(index).setPrice(Double.parseDouble(newValue));
	}
	
	public int binarySearchIng(String fullName, ArrayList<Ingredient> ings) {
		
		int pos = -2;
		int in = 0;
		int fin = ings.size()-1;
		
		while(in<=fin && pos==-2) {
			int middle = (in+fin)/2;
			
			if((ings.get(middle).getName()+ings.get(middle).getName()).equalsIgnoreCase(fullName)) {
				pos = middle;
			}
			
			if(in!=fin) {
				if(fullName.compareTo(ings.get(middle).getName()+ings.get(middle).getName())>0) {
					fin = middle-1;
				}else {
					in = middle+1;
				}
			}else {
				if(fullName.compareTo(ings.get(middle).getName()+ings.get(middle).getName())>0) {
					pos = middle;
					}else {
						pos = middle+1;
					}
			}				
		}
		if(pos<0) {
			pos=0;
		}else if(pos>ings.size()-1) {
			pos = ings.size()-1;
		}
		
		return pos;
	}
	
	
	
}
