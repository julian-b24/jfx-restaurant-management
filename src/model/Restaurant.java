package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
	
	public final static String REPORT_EMPLOYEES_CONSOLIDATED_PATH = "data/reports/consolidated_report.csv";
	
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
							int employeeRef, LocalDate dateRequest, String obs, String state) {
		
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
		
		Order order;
		if (state == null) {
			order = new Order(code, obs, clientRef, employeeRef, dateRequest, productsOrdered, productsAmounts);
		} else {
			order = new Order(code, obs, clientRef, employeeRef, dateRequest, productsOrdered, productsAmounts, state);
		}
		
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
	
	//Req 4.3
	public void importOrders() throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(PRODUCTS_PATH));
		br.readLine(); //Read first line

		String line = br.readLine();
		while (line != null) {
			String[] values = line.split(",");
			String obs = values[0];
			String ccClient = values[1];
			int employeeId = Integer.parseInt(values[2]);
			String dateString = values[3] + " " + values[4];
			LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(Order.DATE_FORMAT));
			ArrayList<Integer> productsTemp = new ArrayList<Integer>();
			productsTemp.add(Integer.parseInt(values[5]));
			productsTemp.add(Integer.parseInt(values[6]));
			productsTemp.add(Integer.parseInt(values[7]));
			ArrayList<Integer> amounts = new ArrayList<Integer>();
			amounts.add(Integer.parseInt(values[8]));
			amounts.add(Integer.parseInt(values[9]));
			amounts.add(Integer.parseInt(values[10]));
			String state = values[11];
			createOrder(productsTemp, amounts, ccClient, employeeId, date, obs, state);
		}
		
		br.close();
	}
	
	//Process Req 2.2
	public void sortOrdersByEmployeesId() {
		
		//Insertion sort
		for (int i = 0; i < orders.size(); i++) {
			int posMin = i;
			for (int j = i + 1; j < orders.size(); j++) {
				
				if (orders.get(j).compareEmployeeRef(orders.get(i)) < 0) {
					posMin = j;
				}
			}
			Order aux = orders.get(i);
			orders.set(i, orders.get(posMin));
			orders.set(posMin, aux);
		}
	}
	
	/**
	 * Generates the info of the consolidates report of employees.
	 * <b> pre: </b> The list of employees must be sorted by id and the orders must be sorted by the employeeRef <br>
	 * @param low 
	 * @param top 
	 * @return A HashMap with the info. The keys are the employees and the values are ArrayLists of the orders that references the employee
	 */
	public Map<Employee, ArrayList<Order>> getInfoReportEmployeesConsolidated(LocalDate top, LocalDate low) {
		
		Map<Employee, ArrayList<Order>> report = new HashMap<Employee, ArrayList<Order>>();
		
		int j = 0;	//Index to iterate over the orders
		
		for (Employee employee : employees) {
			ArrayList<Order> tempOrders = new ArrayList<Order>();
			
			boolean stop = false;
			while (j < orders.size() && !stop) {
				
				if (employee.getEmployeeId() == orders.get(j).getEmployeeRef()) {
					if (isBetweenDates(orders.get(j).getDate(), low, top) ) {
						tempOrders.add(orders.get(j));
					}
					
					j++;
				} else {
					stop = true;
				}
			}
			
			report.put(employee, tempOrders);
		}
		
		return report;
	}
	
	public boolean isBetweenDates(LocalDate date, LocalDate low, LocalDate top) {
		
		boolean between = false;
		boolean greaterOrEqual = date.isAfter(low) || date.isEqual(low);
		boolean lowerOrEqual = date.isBefore(top) || date.isEqual(top);
		between = greaterOrEqual && lowerOrEqual;
		
		return between;
	}
	
	public void generateReportEmployeesConsolidated(String lowDate, String topDate) throws IOException {
		
		PrintWriter pw = new PrintWriter(REPORT_EMPLOYEES_CONSOLIDATED_PATH);
		sortOrdersByEmployeesId();
		
		LocalDate top = LocalDate.parse(topDate, DateTimeFormatter.ofPattern(Order.DATE_FORMAT));
		LocalDate low = LocalDate.parse(lowDate, DateTimeFormatter.ofPattern(Order.DATE_FORMAT));
		
		Map<Employee, ArrayList<Order>> info = getInfoReportEmployeesConsolidated(top, low);
		
		pw.println("employeeId;employeeName;totalSales;numSales");
		
		for (Map.Entry<Employee, ArrayList<Order>> entry : info.entrySet()) {
			
			double totalSales = 0;
			for (Order order : entry.getValue()) {
				totalSales += order.getTotalPrice();
			}
			
			int numSales = entry.getValue().size();
			pw.println(	entry.getKey().getEmployeeId() + ";" + entry.getKey().getName() + ";" +
						totalSales + ";" + numSales);
		}
		
		
		pw.close();
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

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}
	
	
	
}
