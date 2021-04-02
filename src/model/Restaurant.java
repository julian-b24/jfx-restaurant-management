package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import java.io.ObjectOutputStream;

import java.io.ObjectInputStream;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Restaurant{

	
	public final static String INGREDIENTS_PATH = "data/csv/ingredients.csv";
	public final static String CLIENTS_PATH = "data/csv/clients.csv";
	public final static String PRODUCTS_PATH = "data/csv/products.csv";
	public final static String ORDERS_PATH = "data/csv/orders.csv";
	
	public final static String REPORT_EMPLOYEES_CONSOLIDATED_PATH = "data/reports/employees_consolidated_report.csv";
	public final static String REPORT_PRODUCTS_CONSOLIDATED_PATH = "data/reports/products_consolidated_report.csv";
	public final static String REPORT_ORDERS_CONSOLIDATED_PATH = "data/reports/orders_consolidated_report.csv";
	
	final String SAVE_PATH_PRODUCTS = "data/system-files/products-files.txr";
	final String SAVE_PATH_INGREDIENTS = "data/system-files/ingredients-files.txr";
	final String SAVE_PATH_ORDERS = "data/system-files/orders-files.txr";
	final String SAVE_PATH_CLIENTS = "data/system-files/clients-files.txr";
	final String SAVE_PATH_EMPLOYEES = "data/system-files/employees-files.txr";
	final String SAVE_PATH_SYSTEM_USERS = "data/system-files/system-users-files.txr";
	
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
		orders = new ArrayList<>();
	}
	
	public void createSystemUser(String nam, String lastN, String ccP, String user, String passw) throws IOException {
		
		int lastId = 0;
		if(systemUsers.size() > 0) {
			Employee emplX = employees.get(employees.size()-1);
			lastId = emplX.getEmployeeId();
			SystemUser newUser = new SystemUser(nam, lastN, ccP, user, passw, lastId);
			systemUsers.add(newUser);
		}
		
		SystemUser newUser = new SystemUser(nam, lastN, ccP, user, passw, lastId);
		systemUsers.add(newUser);
		
		saveSystemUser();
	}
	
	public void createEmployee(String nam, String lastN, String ccP) throws IOException {
		
		int lastId = 0;
		if(employees.size() > 0) {
			Employee emplX = employees.get(employees.size()-1);
			lastId = emplX.getEmployeeId();
		}

		Employee newEmployee = new Employee(nam, lastN, ccP, lastId);
		employees.add(newEmployee);
		
		saveEmployeeData();
	}
	
	public void createClient(String nam, String lastN, String ccP, String adrs, String phn, String obs) throws IOException{
		
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

		saveClientsData();
	}
	
	public boolean clientAlreadyExistByCC(String cc) {
		
		boolean found = false;
		for (int i = 0; i < clients.size() && !found; i++) {
			if(clients.get(i).getCc().equals(cc)) {
				found = true;
			}
		}
		
		return found;
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
	public void createProduct(String name, String creatorRef, String lastEditor, ArrayList<Integer> ingredientsCodes, String typ) throws IOException {
		
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
		product.generateIngredientsReferences();
		products.add(product);
		saveProductsData();
		saveIngredientData();
	}
	
	public ArrayList<Ingredient> getIngredientsByCode(ArrayList<Integer> codes) {
		
		ArrayList<Ingredient> tempIngredients = new ArrayList<Ingredient>();
		
		boolean stop = false;
		for (int i = 0; i < codes.size() && !stop; i++) {
			for (int k = 0; k < ingredients.size(); k++) {
				if(ingredients.get(k).getCode()== codes.get(i)) {
					tempIngredients.add(ingredients.get(k));
				}
			}
		}
		return tempIngredients;
	}
	
	public SystemUser getUserByUserName(String userName) {
		SystemUser user = null;
		
		boolean found = false;
		for (int i = 0; i < systemUsers.size() && !found; i++) {
			if (systemUsers.get(i).getUserName().equals(userName)) {
				user = systemUsers.get(i);
				found = true;
			}
		}
		
		return user;
	}
	
	//Req 1.2
	public void updateProduct(String name, String lastEditor, int code, ArrayList<Integer> ingredientsCodes, String typ, boolean available, ArrayList<String> sizes, ArrayList<Double> sizesFactors) throws IOException {
		
		Product product = getProductByCode(code);
		product.setName(name);
		product.setLastEditorRef(lastEditor);
		
		ProductType type = ProductType.valueOf(typ.toUpperCase().replace(" ", "_"));
		product.setType(type);
		product.setAvailable(available);
		
		ArrayList<Ingredient> tempIngredients = getIngredientsByCode(ingredientsCodes);
		product.setIngredients(tempIngredients);
		
		product.updateSizes(sizes, sizesFactors);
		
		product.generateIngredientsReferences();
		
		product.updateAmountIngredients();
		product.updateProductType();
		
		saveProductsData();
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
	public void deleteProduct(int code) throws IOException {
		
		boolean found = false;	
		int idx = 0;
		
		while (!found) {
			Product product = products.get(idx);
			if(product.getCode() == code) {
				found = true;
			}else {
				idx++;
			}
			
		}
		
		if (found) {
			products.remove(idx);
		}
		
		saveProductsData();
		saveIngredientData();
	}
	
	public void deleteIngredient(int code) throws IOException {
		
		boolean found = false;	
		int idx = 0;
		
		while (!found) {
			Ingredient ingredient = ingredients.get(idx);
			if(ingredient.getCode() == code) {
				found = true;
			}else {
				idx++;
			}
		}
		
		if (found && ingredients.get(idx).getReferences().isEmpty()) {
			ingredients.remove(idx);
		}
		
		saveIngredientData();
		saveProductsData();
	}
	
	public Ingredient getIngredientByCode(int code) {
		
		Ingredient ingredient = null;
		boolean found = false;
		
		for (int i = 0; i < ingredients.size() && !found; i++) {
			if(ingredients.get(i).getCode() == code) {
				
				ingredient = ingredients.get(i);
				found = true;
			}
		}
		
		return ingredient;
	}

	//Req 1.4
	public void diseableProduct(int code) throws IOException {
		
		Product product = getProductByCode(code);
		product.setAvailable(false);
		saveProductsData();
	}
	
	//Req 1.8
	public void createOrder(ArrayList<Integer> productsCodes, ArrayList<Integer> productsAmounts, ArrayList<String> productsSizes, String clientRef, 
							int employeeRef, LocalDateTime dateRequest, String obs, String state) throws IOException {
		
		ArrayList<Product> productsOrdered = new ArrayList<Product>();
		ArrayList<Size> sizes = new ArrayList<Size>();
		
		//Fill the lists with the products ordered according to the code and the sizes for each one
		for (int i = 0;  i < productsCodes.size(); i++) {
			Product tempProduct = getProductByCode(productsCodes.get(i));
			productsOrdered.add(tempProduct);
			
			if (productsSizes != null) {
				Size size = tempProduct.getSizeByName(productsSizes.get(i));
				sizes.add(size);
			}
		}
		
		//In case the list of sizes is null, the list will be filled with default sizes
		if (productsSizes == null) {
			for (int j = 0; j < productsOrdered.size(); j++) {
				sizes.add(new Size(Product.DEFAULT_SIZE));
			}
		}
		
		//Builds the code of the order
		int code = 0;
		if (orders.size() > 0) {
			code = orders.get(orders.size() - 1).getCode() + 1;
		}
		
		
		Order order;
		if (state == null) {
			order = new Order(code, obs, clientRef, employeeRef, dateRequest, productsOrdered, productsAmounts, sizes);
		} else {
			order = new Order(code, obs, clientRef, employeeRef, dateRequest, productsOrdered, productsAmounts, sizes, state);
		}
		
		order.generateProductsReferences();
		orders.add(order);
		saveOrdersData();
		
	}
	
	public void updateOrder(int orderToUpdtaeCode, ArrayList<Integer> productCodes, ArrayList<Integer> amountsPerEach, ArrayList<String> sizesStrings) throws IOException {
		
		Order orderX = getOrderByCode(orderToUpdtaeCode);
		
		ArrayList<Product> tempOrderProducts = new ArrayList<>();
		ArrayList<Size> tempOrderSizes = new ArrayList<>();
		
		for (int i = 0; i < productCodes.size(); i++) {
			tempOrderProducts.add(getProductByCode(productCodes.get(i)));
			tempOrderSizes.add(tempOrderProducts.get(i).getSizeByName(sizesStrings.get(i)));
		}
		
		orderX.setAmountPerEach(amountsPerEach);		
		orderX.setSizes(tempOrderSizes);
		orderX.setOrderProducts(tempOrderProducts);
		
		orderX.generateProductsReferences();
		orderX.updateStateString();
		
		saveOrdersData();
	}
	
	//Req 1.9
	public void updateStateOrder(int code) throws IOException {
		
		Order order = getOrderByCode(code);
		order.updateState();
		order.updateStateString();
		saveOrdersData();
	}
	
	public Order getOrderByCode(int code) {
		
		Order order = null;
		
		//binary search
		int low = 0;
		int top = orders.size() - 1;
		boolean found = false;
		
		while(low <= top && !found) {
			
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
	public void createIngredient(String name, String creatorRef, String lastE, String val) throws IOException{
		
		int lastCode = 0;
		double value = Double.parseDouble(val);
		Ingredient ingredientX; 
		
		sortIngredientsBycode();
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
		saveIngredientData();
	}
	
	//sort ingredients
	public void sortIngredientByName() {
		Comparator<Ingredient> ingredientNameComparator = new IngredientNameComparator();
		Collections.sort(ingredients, ingredientNameComparator);
	}
	
	/**
	 * Sorts the ingredients list based on their code, using Collections.sort().
	 * <b> pos: </b> The list of products is going to be sorted in decreasing order by the code of the products <br>
	 */
	public void sortIngredientsBycode() {
		Collections.sort(ingredients);
	}
	
	/**
	 * Sorts the products list based on their price, using Collections.sort().
	 * <b> pos: </b> The list of products is going to be sorted in decreasing price <br>
	 */
	public void sortPorductsByPrice() {
		Collections.sort(products);
	}
	
	
	//search ingredient by name
	public boolean searchIngredient(String name) {
		
		boolean found = false;
		for (int i = 0; i < ingredients.size() && !found; i++) {
			if(ingredients.get(i).getName().equalsIgnoreCase(name)) {
				found = true;
			}
		}
		
		return found;
	}
	
	/**
	 * Search a client in the list of clients according to his identification number and returns the position of it in the list.
	 * <b> pre: </b> The list of clients must be sorted by the identification of the clients <br>
	 * @param cc, the identification number of a client
	 * @return the position of the client in the list, if it is not on the list will return -1
	 */
	public int searchClientByCc(String cc) {
		
		boolean found = false;
		int position = -1;
		
		for (int i = 0; i < clients.size() && !found; i++) {
			if(clients.get(i).getCc().equals(cc)) {
				found = true;
				position = i;
			}
		}
		
		return position;
	}
	
	/**
	 * Search a client by his name and return his/her position in the list.
	 * <b> pre: </b> The list of clients must be sorted by name and last name in descending order <br>
	 * @param firstName, String, first name of the client to search
	 * @param lastName, String, last name of the client to search
	 * @return position, the position of the client in the list, if he/she is not in it, the return will be -1
	 */
	public int searchClientByName(String firstName, String lastName) {
		
		int position = -1;
		
		int low = 0;
		int top = clients.size() - 1;
		boolean found = false;
		
		while(low <= top && !found) {
			
			int mid = (low + top)/2;
			if (clients.get(mid).getName().equals(firstName) && clients.get(mid).getLastName().equals(lastName)) {
				position = mid;
				found = true;
			} else if((lastName + firstName).compareTo(clients.get(mid).getLastName() + clients.get(mid).getName()) < 0) {
				low = mid + 1;
			} else {
				top = mid - 1;
			}
		}
		
		return position;
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
			line = br.readLine();
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
			line = br.readLine();
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
			line = br.readLine();
		}
		
		br.close();
	}
	
	//Req 4.3
	public void importOrders() throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(ORDERS_PATH));
		br.readLine(); //Read first line

		String line = br.readLine();
		while (line != null) {
			String[] values = line.split(",");
			String obs = values[0];
			String ccClient = values[1];
			int employeeId = Integer.parseInt(values[2]);
			
			String dateString = values[3] + " " + values[4];
			LocalDateTime date = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern(Order.DATE_FORMAT));
			
			ArrayList<Integer> productsTemp = new ArrayList<Integer>();
			ArrayList<Integer> amounts = new ArrayList<Integer>();
			
			productsTemp.add(Integer.parseInt(values[5]));
			amounts.add(Integer.parseInt(values[6]));
			
			productsTemp.add(Integer.parseInt(values[7]));
			amounts.add(Integer.parseInt(values[8]));
			
			productsTemp.add(Integer.parseInt(values[9]));
			amounts.add(Integer.parseInt(values[10]));
			
			String state = values[11];
			createOrder(productsTemp, amounts, null, ccClient, employeeId, date, obs, state); //Add list of Strings
			line = br.readLine();
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
	
	public void sortOrdersByDate() {
		Collections.sort(orders);
	}
	
	public void sortEmployeesById() {
		
		//Bubble sort
		boolean changed = true;
		for (int i = 1; i < employees.size() - 1 && changed; i++) {
			changed = false;
			
			for (int j = 0; j < employees.size() - i; j++) {
				
				if (employees.get(j).compareById(employees.get(j + 1)) > 0) {
					
					Employee tempEmployee = employees.get(j);
					employees.set(j, employees.get(j + 1));
					employees.set(j + 1, tempEmployee);
					changed = true;
				}
			}
		}
	}
	
	/**
	 * Generates the info of the consolidates report of employees.
	 * <b> pre: </b> The list of employees must be sorted by id and the orders must be sorted by the employeeRef <br>
	 * @param low 
	 * @param top 
	 * @return A HashMap with the info. The keys are the employees and the values are ArrayLists of the orders that references the employee
	 */
	public Map<Employee, ArrayList<Order>> getInfoReportEmployeesConsolidated(LocalDateTime top, LocalDateTime low) {
		
		Map<Employee, ArrayList<Order>> report = new HashMap<Employee, ArrayList<Order>>();
		
		int j = 0;	//Index to iterate over the orders
		
		for (Employee employee : employees) {
			ArrayList<Order> tempOrders = new ArrayList<Order>();
			
			while (j < orders.size() && employee.getEmployeeId() == orders.get(j).getEmployeeRef()) {
				
				if (isBetweenDates(orders.get(j).getDate(), low, top) ) {
					tempOrders.add(orders.get(j));
				}
				j++;
			}
			
			report.put(employee, tempOrders);
		}
		
		return report;
	}
	
	
	public Map<Integer, Map<Integer, Double>> getInforReportProductsConsolidated(LocalDateTime top, LocalDateTime low) {
		
		//Principal Key: Product -> Value: Times Ordered
		//Second Key: Times Ordered -> Value: Total collected
		//Summary: Product code -> Times Ordered -> Total collected
		Map<Integer, Map<Integer, Double>> report = new HashMap<Integer, Map<Integer, Double>>();
		
		//Generates the HashMap with all the products as keys and zeros in any other value or key
		for (Product product : products) {
			
			Map<Integer, Double> collected = new HashMap<Integer, Double>();
			Integer count = 0;
			Double total_collected = 0.0;
			
			collected.put(count, total_collected);
			report.put(product.getCode(), collected);
		}
		
		//Iterate over all orders getting the amount and the total collected for each product
		for (Order order : orders) {
			
			if (isBetweenDates(order.getDate(), low, top) ) {
				
				for (int i = 0; i < order.getOrderProducts().size(); i++) {
					
					//Calculating the total of  product in a specific order
					Product tempProduct = order.getOrderProducts().get(i);

					Double tempSizeFactor = order.getSizes().get(i).getPriceFactor();
					int tempAmount = order.getAmountPerEach().get(i);
					Double tempTotal = tempProduct.getPrice() * tempSizeFactor * tempAmount;
					
					
					Map<Integer, Double> tempCollected = report.get(tempProduct.getCode());
					
					//Getting the old data
					//This loop will repeat just once, lastAmount = key of the map
					Integer oldAmount = 0;
					for (Map.Entry<Integer, Double> entry : report.get(tempProduct.getCode()).entrySet()) {
						oldAmount = entry.getKey();
					}
					
					Double oldTotal = tempCollected.get(oldAmount);
					
					//Replace of the old values and adding the new ones
					Integer newAmount = tempAmount + oldAmount;
					Double newTotal = tempTotal + oldTotal;
					tempCollected.remove(oldAmount);
					tempCollected.put(newAmount, newTotal);
					report.put(tempProduct.getCode(), tempCollected);
				}
			}
		}
		
		return report;
	}
	
	public boolean isBetweenDates(LocalDateTime date, LocalDateTime low, LocalDateTime top) {
		
		boolean between = false;
		int underDate = date.compareTo(top);
		int overDate = low.compareTo(date);
		between = underDate <= 0 && overDate <= 0;
		
		return between;
	}
	
	public void generateReportEmployeesConsolidated(LocalDateTime low, LocalDateTime top, String separator) throws IOException {
		
		PrintWriter pw = new PrintWriter(REPORT_EMPLOYEES_CONSOLIDATED_PATH);
		sortOrdersByEmployeesId();
		
		Map<Employee, ArrayList<Order>> info = getInfoReportEmployeesConsolidated(top, low);
		
		pw.println("employeeId;employeeName;totalSales;numSales".replace(";", separator));
		
		for (Map.Entry<Employee, ArrayList<Order>> entry : info.entrySet()) {
			
			double totalSales = 0;
			for (Order order : entry.getValue()) {
				totalSales += order.getTotalPrice();
			}
			
			int numSales = entry.getValue().size();
			pw.println(	(entry.getKey().getEmployeeId() + ";" + entry.getKey().getName() + ";" +
						totalSales + ";" + numSales).replace(";", separator));
		}
		
		
		pw.close();
	}
	
	public void generateReportProductsConsolidated(LocalDateTime low, LocalDateTime top, String separator) throws FileNotFoundException {
		
		PrintWriter pw = new PrintWriter(REPORT_PRODUCTS_CONSOLIDATED_PATH);
		
		pw.println("productName;timerOrdered;totalMoney".replace(";", separator));
		
		Map<Integer, Map<Integer, Double>> info = getInforReportProductsConsolidated(top, low);
		double totalSales = 0.0;
		int totalAmount = 0;
		
		for (Map.Entry<Integer, Map<Integer, Double>> entryProduct: info.entrySet()) {
			
			Map<Integer, Double> values = entryProduct.getValue();
			String tempProductName = getProductByCode(entryProduct.getKey()).getName();			
			int tempAmount = 0;
			double tempSales = 0.0;
			
			for (Map.Entry<Integer, Double> entryValues: values.entrySet()) {
				
				tempAmount = entryValues.getKey();
				tempSales = entryValues.getValue();
			}
			
			totalSales += tempSales;
			totalAmount += tempAmount;
			
			pw.println((tempProductName + ";" + tempAmount + ";" + tempSales).replace(";", separator));
		}
		
		pw.println((" ;" + totalAmount + ";" + totalSales).replace(";", separator));
		pw.close();
	}
	
	public void generateReportOrdersConsolidated(LocalDateTime low, LocalDateTime top, String separator) throws FileNotFoundException {
		
		PrintWriter pw = new PrintWriter(REPORT_ORDERS_CONSOLIDATED_PATH);
		
		pw.println("clientName;clientAddress;clientPhone;employeeName;orderDate;observations;productName;productValue;productAmount".replace(";", separator));
		
		for (Order order : orders) {
			
			if (isBetweenDates(order.getDate(), low, top)) {
				
				//Client info
				Client client = clients.get(searchClientByCc(order.getClientRef()));
				String clientName = client.getName();
				String clientAddress = client.getAdress();
				String clientPhone = client.getPhone();
				
				//Employee info
				sortEmployeesById();
				Employee employee = employees.get(searchEmployeeById(order.getEmployeeRef()));
				
				String employeeName = employee.getName();
				
				//Date info
				String date = order.getDate().format(DateTimeFormatter.ofPattern(Order.DATE_FORMAT));
				
				String line = clientName + ";" + clientAddress + ";" +  clientPhone + ";" + employeeName
							  + ";" + date + ";";
				
				//Products info
				String podructsLine = "";
				for (int i = 0; i < order.getOrderProducts().size(); i++) {
					
					Product product = order.getOrderProducts().get(i);
					Size size = order.getSizes().get(i);
					//Name of the product will include the size of it in the order, it's useful to difference it.
					String productName = product.getName() + " - " + size.getSize();
					String amount = Integer.toString(order.getAmountPerEach().get(i));
					String value = Double.toString(product.getPrice() * size.getPriceFactor());
					podructsLine += (productName + ";" + amount + ";" + value + ";");
				}
				
				line += (podructsLine);
				line.replace(";", separator);
				pw.println(line);
			}
		}
		
		pw.close();
	}
	
	public int searchEmployeeById(int id) {
		
		int position = -1;
		
		int low = 0;
		int top = employees.size() - 1;
		boolean found = false;
		
		while(low <= top && !found) {
			
			int mid = (low + top)/2;
			if (employees.get(mid).getEmployeeId() == id) {
				position = mid;
				found = true;
				
			} else if (employees.get(mid).getEmployeeId() - id < 0) {
				low = mid + 1;
			} else {
				top = mid - 1;
			}
		}
		return position;
	}
	
	public int searchEmployeeByCC(String cc) {
		
		int pos = -1;
		boolean found = false;
		
		for (int i = 0; i < employees.size() && !found; i++) {
			if(employees.get(i).getCc().equals(cc)) {
				pos = i;
				found = true;
			}
		}
		return pos;
	}
	
	
	public int searchEmployeeByUserName(String userN) {
		
		boolean found = false;
		int id=-1;
		for (int i = 0; i < systemUsers.size() && !found; i++) {
			if(systemUsers.get(i).getUserName().equals(userN)) {
				found = true;
				id = systemUsers.get(i).getEmployeeId();
			}
		}
		return id;
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

	public void updateIngredient(String referenceIngredient, String newName, String newValue, boolean newAvailable) throws IOException {
		
		int index = binarySearchIng(referenceIngredient, ingredients);
		ingredients.get(index).setName(newName);
		ingredients.get(index).setPrice(Double.parseDouble(newValue));
		ingredients.get(index).setAvailable(newAvailable);
		
		saveIngredientData();
	}
	
	public int binarySearchIng(String fullName, ArrayList<Ingredient> ings) {
		
		int pos = -1;
		int in = 0;
		int fin = ings.size()-1;
		
		while(in<=fin && pos==-1) {
			int middle = (in+fin)/2;
			
			if((ings.get(middle).getName().equalsIgnoreCase(fullName))) {
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
	
	public boolean producIsAvailable(int code) {
		
		Product product = getProductByCode(code);
		boolean available = product.isAvailable();
		return available;
	}
	
	public boolean ingredientIsAvailable(int code) {
		
		Ingredient ingredient = getIngredientByCode(code);
		boolean available = ingredient.isAvailable();
		return available;
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

	public void saveData() throws FileNotFoundException, IOException {
		saveIngredientData();
		saveProductsData();
		saveOrdersData();
		saveClientsData();
		saveSystemUser();
		saveEmployeeData();
	}
	
	public void saveIngredientData() throws IOException {
		ObjectOutputStream oosI =  new ObjectOutputStream(new FileOutputStream(SAVE_PATH_INGREDIENTS));
		oosI.writeObject(ingredients);
		oosI.close();
	}
	
	public void saveProductsData() throws IOException {
		ObjectOutputStream oosP =  new ObjectOutputStream(new FileOutputStream(SAVE_PATH_PRODUCTS));
		oosP.writeObject(products);
		oosP.close();
	}
	
	public void saveOrdersData() throws IOException {
		ObjectOutputStream oosO =  new ObjectOutputStream(new FileOutputStream(SAVE_PATH_ORDERS));
		oosO.writeObject(orders);
		oosO.close();
	}
	
	public void saveClientsData() throws IOException {
		ObjectOutputStream oosC =  new ObjectOutputStream(new FileOutputStream(SAVE_PATH_CLIENTS));
		oosC.writeObject(clients);
		oosC.close();
	}
	
	public void saveSystemUser() throws IOException {
		ObjectOutputStream oosS =  new ObjectOutputStream(new FileOutputStream(SAVE_PATH_SYSTEM_USERS));
		oosS.writeObject(systemUsers);
		oosS.close();
	}
	
	public void saveEmployeeData() throws IOException {
		ObjectOutputStream oosE =  new ObjectOutputStream(new FileOutputStream(SAVE_PATH_EMPLOYEES));
		oosE.writeObject(employees);
		oosE.close();
	}
	
	@SuppressWarnings("unchecked")
	public void loadData() throws IOException, ClassNotFoundException{
		
		File fileProducts = new File(SAVE_PATH_PRODUCTS);
		File fileIngredients = new File(SAVE_PATH_INGREDIENTS);
		File fileClients = new File(SAVE_PATH_CLIENTS);
		File fileEmployees = new File(SAVE_PATH_EMPLOYEES);
		File fileSystemUsers = new File(SAVE_PATH_SYSTEM_USERS);
		File fileOrders = new File(SAVE_PATH_ORDERS);

		if (fileProducts.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileProducts));
			products = (ArrayList<Product>) ois.readObject();
			ois.close();
		}
		
		
		if (fileIngredients.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileIngredients));
			ingredients = (ArrayList<Ingredient>) ois.readObject();
			ois.close();
		}
		
		if (fileClients.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileClients));
			clients = (ArrayList<Client>) ois.readObject();
			ois.close();
		}
		
		if (fileEmployees.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileEmployees));
			employees = (ArrayList<Employee>) ois.readObject();
			ois.close();
		}

		if (fileSystemUsers.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileSystemUsers));
			systemUsers = (ArrayList<SystemUser>) ois.readObject();
			ois.close();
		}
		
		
		if (fileOrders.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileOrders));
			orders = (ArrayList<Order>) ois.readObject();
			ois.close();
		}

	}
	
	public String getOrderFormat() {
		String format="";
		
		format=Order.DATE_FORMAT;
		
		return format;
	}

	public void updateClient(String clientCC, String name, String lastName, String adress, String phone, String obsField) throws IOException {
		int posClient = searchClientByCc(clientCC);
		
		Client client = clients.get(posClient);
		client.setName(name);
		client.setLastName(lastName);
		client.setAdress(adress);
		client.setPhone(phone);
		client.setObsField(obsField);
		
		saveClientsData();
	}

	public void updateEmployee(String employeeCC, String name, String lastName) throws IOException {
		
		int posEmployee = searchEmployeeByCC(employeeCC);
		
		Employee employee = employees.get(posEmployee);
		employee.setName(name);
		employee.setLastName(lastName);
		
		saveEmployeeData();
	}
	
	public int searchSystemUser(String username) {
		
		int pos = -1;
		boolean found = false;
		for (int i = 0; i < systemUsers.size() && !found; i++) {
			if(systemUsers.get(i).getUserName().equals(username)) {
				pos = i;
				found = true;
			}
			
		}
		return pos;		
	}

	public void updateSystemUser(String username, String name, String lastName, String newUserName, String password) throws IOException {
		
		int posSystemU = searchSystemUser(username);
		
		SystemUser systemUser = systemUsers.get(posSystemU);
		systemUser.setName(name);
		systemUser.setLastName(lastName);
		systemUser.setUserName(newUserName);
		systemUser.setPassword(password);
		
		saveSystemUser();
	}
	
}
