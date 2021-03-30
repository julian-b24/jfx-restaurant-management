package ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Client;
import model.Employee;
import model.Ingredient;
import model.Order;
import model.Product;
import model.Restaurant;
import model.Size;
import model.SystemUser;

public class RestaurantGUI {

	//other attributes
	private String actualUser;						//A reference to the system user that is currently logged in
	private String referenceIngredient;				//A reference to an ingredient selected in the ingredients table, that allows the program to recognize which ingredient has to be edited 
	private ArrayList<Integer> tempIngrsCodes;		//Temporal list with the indexes of the ingredients that will be added to the product
	private Product referenceProduct;
	private Order referenceOrder;
	private int orderCodeReference;
	private ArrayList<Integer> tempProductCodes;
	private ArrayList<Integer> tempProductsAmounts;
	private ArrayList<String> tempProductsSizes;
	private String clientRef;						//cc from client in order
	private long searchTime;						//binary search time of client by name
	
	//MainAnchorPane
	@FXML
    private AnchorPane mainPane;
	
	//Log in window
	@FXML
	private JFXTextField usernametxf;

	@FXML
	private JFXTextField passwordtxf;

	//Register window
	@FXML
    private JFXTextField rNametxf;

    @FXML
    private JFXTextField rLastNametfx;

    @FXML
    private JFXTextField rCctfx;

    @FXML
    private JFXTextField rUsernametxf;

    @FXML
    private JFXTextField rPasswordtxf;
	
    //ingredients pane
    @FXML
    private JFXTextField txtIngredientName;

    @FXML
    private JFXTextField txtIngredientValue;

    @FXML
    private TableView<Ingredient> tableIngr;

    @FXML
    private TableColumn<Ingredient, String> colIngr;

    @FXML
    private TableColumn<Ingredient, String> colCreator;

    @FXML
    private TableColumn<Ingredient, String> colLastE;

    @FXML
    private TableColumn<Ingredient, String> colCode;

    @FXML
    private TableColumn<Ingredient, String> colValue;
    
    @FXML
    private TableColumn<Ingredient, Button> colEdit;

    //edit ingredient
    @FXML
    private JFXTextField txtNewIngName;

    @FXML
    private JFXTextField txtNewIngVal;
    
    @FXML
    private JFXRadioButton radioBtnIngAvailable;

    @FXML
    private ToggleGroup ingState;

    @FXML
    private JFXRadioButton radioBtnIngUnavailable;

    //admin products
    @FXML
    private JFXTextField txtPName;

    @FXML
    private JFXRadioButton rbMainDish;

    @FXML
    private ToggleGroup types;

    @FXML
    private JFXRadioButton rbAdition;

    @FXML
    private JFXRadioButton rbDrink;
    //prodcut table
    @FXML
    private TableView<Product> tvProducts;

    @FXML
    private TableColumn<Product, String> colPname;

    @FXML
    private TableColumn<Product, String> colPIngs;

    @FXML
    private TableColumn<Product, String> colLEP;

    @FXML
    private TableColumn<Product, String> colPCode;

    @FXML
    private TableColumn<Product, String> coPPrice;

    @FXML
    private TableColumn<Product, String> colPav;

    @FXML
    private TableColumn<Product, String> colPType;
   
    //admin products
    @FXML
    private TableView<Ingredient> tvIngP;

    @FXML
    private TableColumn<Ingredient, String> colNip;

    @FXML
    private TableColumn<Ingredient, String> colIngcreP;

    @FXML
    private TableColumn<Ingredient, String> colIngLastEP;

    @FXML
    private TableColumn<Ingredient, String> colIngCodP;

    @FXML
    private TableColumn<Ingredient, String> colIngVal;
    
    //edit products
    @FXML
    private JFXTextField txtNewProductName;

    @FXML
    private JFXRadioButton rbProductAvailable;

    @FXML
    private ToggleGroup state;

    @FXML
    private JFXRadioButton rbProductUnavailable;

    @FXML
    private JFXTextField actionIngtxt;

    @FXML
    private TableView<Ingredient> tvallInings;

    @FXML
    private TableColumn<Ingredient, String> colAlIngsNames;

    @FXML
    private TableColumn<Ingredient, String> colAllIngsCodes;

    @FXML
    private TableColumn<Ingredient, String> colAllINgPrices;

    @FXML
    private TableView<Ingredient> tvIdp;

    @FXML
    private TableColumn<Ingredient, String> colIngInProduct;

    @FXML
    private TableColumn<Ingredient, String> colIngInPCode;

    @FXML
    private TableColumn<Ingredient, String> colIngInPPrice;
    
    @FXML
    private TableView<Size> tvProdSizes;

    @FXML
    private TableColumn<Size, String> colProdSizeName;

    @FXML
    private TableColumn<Size, String> colProdSizePrice;
    //
    private Restaurant restaurant;
    
    @FXML
    private JFXTextField txtAddNameSize;

    @FXML
    private JFXTextField txtMultiply;
    
    @FXML
    private ToggleGroup pState;
    
    @FXML
    private JFXRadioButton rbPMainDish;

    @FXML
    private ToggleGroup ptype;

    @FXML
    private JFXRadioButton rbPAd;

    @FXML
    private JFXRadioButton rbPDrink;
    
    //Create order
    
    @FXML
    private JFXTextField txtSearchClientName;

    @FXML
    private JFXTextField txtSearhClientLastName;
    
    @FXML
    private JFXTextField txtClientName;

    @FXML
    private JFXTextField txtClientLastName;

    @FXML
    private JFXTextField txtClientCC;

    @FXML
    private JFXTextField txtClientAdress;

    @FXML
    private JFXTextField txtClientPhone;

    @FXML
    private JFXTextField txtClientObsField;

    @FXML
    private JFXTextField txtOrderProductCode;

    @FXML
    private JFXTextField txtOrderProductAmount;

    @FXML
    private JFXTextField txtOrderProductSize;

    @FXML
    private TableView<Product> tvProductsList;

    @FXML
    private TableColumn<Product, String> colProductsNames;

    @FXML
    private TableColumn<Product, String> colProductsCodes;

    @FXML
    private TableColumn<Product, String> colProductsPrices;

    @FXML
    private TableView<Order> tvOrders;

    @FXML
    private TableColumn<Order, String> colOrdersClients;

    @FXML
    private TableColumn<Order, String> colOrdersCodes;

    @FXML
    private TableColumn<Order, String> colOrdersStates;

    @FXML
    private TableColumn<Order, String> colOrdersPrices;
    
    @FXML
    private JFXTextField txtOrderOBs;
    
    @FXML
    private Label labelSearchTimeClient;
    
    //edit order
    @FXML
    private JFXRadioButton radioBtnState1;

    @FXML
    private ToggleGroup orderState;

    @FXML
    private JFXRadioButton radioBtnState2;

    @FXML
    private JFXRadioButton radioBtnState3;

    @FXML
    private JFXRadioButton radioBtnState4;

    @FXML
    private JFXTextField txtEOProductName;

    @FXML
    private JFXTextField actionIngtxt11;

    @FXML
    private TableView<Product> tvPIO;

    @FXML
    private TableColumn<Product, String> colEOProductName;

    @FXML
    private TableColumn<Product, String> colEOProductCode;

    @FXML
    private TableColumn<Product, String> colEOProductPrice;

    @FXML
    private TableView<Product> tvregisteredProducts;

    @FXML
    private TableColumn<Product, String> colEOOrderName;

    @FXML
    private TableColumn<Product, String> colEOCode;

    @FXML
    private TableColumn<Product, String> colEOPrice;

    @FXML
    private JFXTextField txtEOAmount;

    @FXML
    private JFXTextField txtEOSize;
    
    //Reports menu
    @FXML
    private JFXTextField txtSeparator;

    @FXML
    private JFXDatePicker txtInitialDate;

    @FXML
    private JFXDatePicker txtFinalDate;
    
    //CLIENT
    @FXML
    private JFXTextField txtCCToEdit;
    
    //update client
    @FXML
    private JFXTextField txtNewClientName;

    @FXML
    private JFXTextField txtNewClientLastName;

    @FXML
    private JFXTextField txtUpdateClientCC;

    @FXML
    private JFXTextField txtNewClientAdress;

    @FXML
    private JFXTextField txtNewClientPhone;

    @FXML
    private JFXTextField txtNewClientObsField;
    
    //createEmployee
    @FXML
    private JFXTextField txtEmployeeName;

    @FXML
    private JFXTextField txtEmployeeLastName;

    @FXML
    private JFXTextField txtEmployeeCc;

    @FXML
    private JFXTextField txtCCEmployeeToEdit;
    
    //edit employee
    @FXML
    private JFXTextField txtNewEmploName;

    @FXML
    private JFXTextField txtNewEmploLastName;

    @FXML
    private JFXTextField txtUpdateEmploCC;
    
    //update sysUser
    @FXML
    private JFXTextField txtEidtUserName;

    @FXML
    private JFXTextField txtEditUserLatName;

    @FXML
    private JFXTextField txtEditUsername;

    @FXML
    private JFXTextField txtEditUserPassword;

    @FXML
    private JFXTextField txtEdituserCC;
    
    //showClients
    @FXML
    private TableView<Client> tvShowClients;

    @FXML
    private TableColumn<Client, String> colSClientName;

    @FXML
    private TableColumn<Client, String> colSClientLastName;

    @FXML
    private TableColumn<Client, String> colSClientCc;

    @FXML
    private TableColumn<Client, String> colSClientAdress;

    @FXML
    private TableColumn<Client, String> colSClientPhone;
    
    //showEmployee
    @FXML
    private TableView<Employee> tvShowEmployees;

    @FXML
    private TableColumn<Employee, String> colSEmployeeName;

    @FXML
    private TableColumn<Employee, String> colSEmployeeLastName;

    @FXML
    private TableColumn<Employee, String> colSEmployeeCc;

    @FXML
    private TableColumn<Employee, String> colSEmployeeId;

    //showUsers
    @FXML
    private TableView<SystemUser> tvShowUsers;

    @FXML
    private TableColumn<SystemUser, String> colSUserName;

    @FXML
    private TableColumn<SystemUser, String> colSUserLastName;

    @FXML
    private TableColumn<SystemUser, String> colSUserCc;

    @FXML
    private TableColumn<SystemUser, String> colSUserAdress;

    @FXML
    private TableColumn<SystemUser, String> colSUserPhone;
    
    //show ingredients
    @FXML
    private TableView<Ingredient> tvShowIngredients;

    @FXML
    private TableColumn<Ingredient, String> colSIngredientName;

    @FXML
    private TableColumn<Ingredient, String> colSIngredientLastName;

    @FXML
    private TableColumn<Ingredient, String> colSIngredientCc;

    @FXML
    private TableColumn<Ingredient, String> colSIngredientAdress;

    @FXML
    private TableColumn<Ingredient, String> colSIngredientPhone;
    
    //show products
    @FXML
    private TableView<Product> tvShowProduct;

    @FXML
    private TableColumn<Product, String> colSProductName;

    @FXML
    private TableColumn<Product, String> colSProductCreator;

    @FXML
    private TableColumn<Product, String> colSProductCode;

    @FXML
    private TableColumn<Product, String> colSProductAmountIngredients;

    @FXML
    private TableColumn<Product, String> colSProductAvailabale;

    @FXML
    private TableColumn<Product, String> colSProductType;
    
    @FXML
    private TableColumn<Product, String> colSProductPrice;
    
    //Constructor
    public RestaurantGUI(Restaurant restaurant) {
		this.restaurant = restaurant;
		tempIngrsCodes = new ArrayList<>();
		tempProductCodes = new ArrayList<>();
		tempProductsAmounts = new ArrayList<>();
		tempProductsSizes = new ArrayList<>();
	}

	//Login methods
	@FXML
	public void loadRegister(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registerSysUser-pane.fxml"));
		fxmlLoader.setController(this); 	
		
		Parent addContactPane = null;
		try {
			addContactPane = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainPane.getChildren().clear();
		mainPane.getChildren().setAll(addContactPane);
	}
	
	@FXML
	public void loginUser(ActionEvent event) {
			if(usernametxf.getText().equals("") || passwordtxf.getText().equals("")) {

				usernametxf.getStyleClass().add("wrong-login");
				passwordtxf.getStyleClass().add("wrong-login");
				warningEmpyText();
				
			}else {
				boolean loged = false;
				for (int i = 0; i < restaurant.getSystemUsers().size(); i++) {
					if(usernametxf.getText().equals(restaurant.getSystemUsers().get(i).getUserName()) &&
							passwordtxf.getText().equals(restaurant.getSystemUsers().get(i).getPassword())) {
						actualUser = usernametxf.getText();
						loadMenu(null);
						loged = true;
					}
				}
				
				if (!loged) {
					usernametxf.getStyleClass().add("wrong-login");
					passwordtxf.getStyleClass().add("wrong-login");
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Valores incorrctos");
					alert.setContentText("Al menos uno de los dos valores es incorrecto!");
					alert.showAndWait();
				}
			}
		}
	
	
	//Register methods
	@FXML
    public void createSystemUser(ActionEvent event) {
		boolean valid = validateRegisterInput(rNametxf.getText(), rLastNametfx.getText(), rCctfx.getText(),
				rUsernametxf.getText(), rPasswordtxf.getText());
		
		if(valid) {
			try {
				restaurant.createEmployee(rNametxf.getText(), rLastNametfx.getText(), rCctfx.getText());
				restaurant.createSystemUser(rNametxf.getText(), rLastNametfx.getText(), rCctfx.getText(), rUsernametxf.getText(), rPasswordtxf.getText());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			loadLogin(null);
		}else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Valores inválidos");
			alert.setContentText("Al menos uno de los campos está vacío o el nombre de usuario ingresado ya existe.");
			alert.showAndWait();
		}
    }

    @FXML
    public void loadLogin(ActionEvent event) {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-pane.fxml"));
		fxmlLoader.setController(this); 	
		
		Parent addContactPane = null;
		try {
			addContactPane = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainPane.getChildren().clear();
		mainPane.getChildren().setAll(addContactPane);
    }
    
    @FXML
    public void loadMenu(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu-pane.fxml"));
		fxmlLoader.setController(this); 	
		
		Parent addContactPane = null;
		try {
			addContactPane = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainPane.getChildren().clear();
		mainPane.getChildren().setAll(addContactPane);
	}

    @FXML
    public void loadAdminProducts(ActionEvent event) {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminProducts-pane.fxml"));
		fxmlLoader.setController(this); 	
		
		Parent addContactPane = null;
		try {
			addContactPane = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainPane.getChildren().clear();
		mainPane.getChildren().setAll(addContactPane);
    }

    @FXML
    public void loadAdminUser(ActionEvent event) {
    	
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminUsers-pane.fxml"));
		fxmlLoader.setController(this); 	
		
		Parent addContactPane = null;
		try {
			addContactPane = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainPane.getChildren().clear();
		mainPane.getChildren().setAll(addContactPane);
    }
    
    @FXML
    public void loadImports(ActionEvent event) {
    	
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("imports-pane.fxml"));
		fxmlLoader.setController(this); 	
		
		Parent addContactPane = null;
		try {
			addContactPane = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainPane.getChildren().clear();
		String css = "styles/tableStyle.css";
		addContactPane.getStylesheets().add(css);
		mainPane.getChildren().setAll(addContactPane);
    }
    
    @FXML
    public void loadAdminIngredients(ActionEvent event) {	
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminIngredients-pane.fxml"));
		fxmlLoader.setController(this); 	
		
		
		Parent addContactPane = null;
		try {
			addContactPane = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainPane.getChildren().clear();
		String css = "styles/tableStyle.css";
		addContactPane.getStylesheets().add(css);
		mainPane.getChildren().setAll(addContactPane);
		initizalizeTableIngr();
    }
    
    //
    public boolean validateRegisterInput(String name, String lastN, String cc, String userN, String pass) {
    	boolean valid = true;
    	if(name.equals("") || lastN.equals("") || cc.equals("") || userN.equals("") || pass.equals("")) {
    		valid = false;
    	}
    	if(valid) {
    		for (int i = 0; i < restaurant.getSystemUsers().size(); i++) {
				if(restaurant.getSystemUsers().get(i).getUserName().equals(userN)) {
					valid = false;
				}
			}
    	}
    	
    	return valid;
    }
    
    @FXML
    public void createIngredient(ActionEvent event) {
    	
    	if(!txtIngredientName.getText().equals("") && !txtIngredientValue.getText().equals("")){
    		try {
				restaurant.createIngredient(txtIngredientName.getText(), actualUser, actualUser, txtIngredientValue.getText());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    
    @FXML
    public void LoadEditI(ActionEvent event) {
    	Ingredient ing = tableIngr.getSelectionModel().getSelectedItem();
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editIngr-pane.fxml"));
		fxmlLoader.setController(this); 	
		
		
		Parent addContactPane = null;
		try {
			addContactPane = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainPane.getChildren().clear();
		String css = "styles/tableStyle.css";
		addContactPane.getStylesheets().add(css);
		mainPane.getChildren().setAll(addContactPane);
		
		txtNewIngName.setText(ing.getName());
		txtNewIngVal.setText(""+ing.getPrice());
		referenceIngredient = ing.getName();
		
		
		if(ing.isAvailable()) {
			radioBtnIngAvailable.setSelected(true);
		}else {
			radioBtnIngUnavailable.setSelected(true);
		}
    }
    
    @FXML
    public void updateIngredient(ActionEvent event) {
    	if(!txtNewIngName.equals("") && !txtNewIngVal.equals("")) {
    		try {
    			boolean available= false;
    			if(radioBtnIngAvailable.isSelected()) {
    				available = true;
    			}
    			
				restaurant.updateIngredient(referenceIngredient, txtNewIngName.getText(), txtNewIngVal.getText(), available);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
    	}
    }
    
    public void initizalizeTableIngr() {
    		 ObservableList<Ingredient> ingredientArray = FXCollections.observableArrayList(restaurant.getIngredients());
    		 
    		 colIngr.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("name"));  		 
    		 
    		 colCreator.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("creatorRef"));
    		 
    		 colLastE.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("lastEditorRef"));
    		 
    		 colCode.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("code"));
    		 
    		 colValue.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("price"));    		
    		 
    		 tableIngr.setItems(ingredientArray);	 
    	 }
    
    public void initizalizeTableIngrProd() {
		 ObservableList<Ingredient> ingredientArrays = FXCollections.observableArrayList(restaurant.getIngredients());
		 
		 colNip.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("name"));  		 
		 
		 colIngcreP.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("creatorRef"));
		 
		 colIngLastEP.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("lastEditorRef"));
		 
		 colIngCodP.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("code"));
		 
		 colIngVal.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("price"));    		
		 
		 tvIngP.setItems(ingredientArrays);	 
	 }
    
    @FXML
    public void loadAProducts(ActionEvent event) {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminProduct-pane.fxml"));
		fxmlLoader.setController(this); 	
		
		Parent addContactPane = null;
		try {
			addContactPane = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainPane.getChildren().clear();
		String css = "styles/tableStyle.css";
		addContactPane.getStylesheets().add(css);
		mainPane.getChildren().setAll(addContactPane);
		initizalizeTableIngrProd();
		initizalizeTableProducts();
		
    }
    
    @FXML
    public void createProduct(ActionEvent event) {
    	String type="";
    	boolean valid = validateInputProduct();
    	System.out.println(valid);
    	if(valid) {
    		
    		if(rbMainDish.isSelected()) {
    			type = "MAIN_DISH";
    		}else if(rbAdition.isSelected()) {
    			type = "ADDITIONAL_DISH";
    		}else {
    			type = "DRINK";
    		}
    		
    		try {
				restaurant.createProduct(txtPName.getText(), actualUser, actualUser, tempIngrsCodes, type);
				tempIngrsCodes.clear();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		}
    }
    
    @FXML
    public void addIngredientToProduct(ActionEvent event) {
    	Ingredient ingrX = tvIngP.getSelectionModel().getSelectedItem();
    	
    	if(ingrX != null) {
    		if( restaurant.ingredientIsAvailable(ingrX.getCode())) {
    			if(tempIngrsCodes.size()>0) {
    				
            		boolean canAdd = searchTempIngr(ingrX.getCode());
            		if(canAdd) {
            			
        	    		tempIngrsCodes.add(ingrX.getCode());
        	    	}
            	}else {
            		tempIngrsCodes.add(ingrX.getCode());
            	}
    		}else {
    			//alerta ingrediente no disponible
    			Alert warning = new Alert(AlertType.WARNING);
    			warning.setTitle("Operación inválida");
    			warning.setContentText("El ingrediente seleccionado está deshabilitado! No se puede agregar a ningún" +
        							 "producto");
    			warning.showAndWait();
    		}
    			
    		
    	}else {
    		//alerta nada seleccionado
    		Alert error = new Alert(AlertType.ERROR);
    		error.setTitle("Error, ninguna selección");
    		error.setContentText("No se ha seleccionado ningún ingrediente de la tabla. Debe seleccionar un ingrediente para " +
    							 "poder añadirlo.");
    		error.showAndWait();
    	}
    	
    	
	    		
    }
    
    public boolean searchTempIngr(int codeI) {
    	boolean found=false;
    	for (int i = 0; i < tempIngrsCodes.size() && !found; i++) {
			if(tempIngrsCodes.get(i)==codeI) {
				found = true;
			}
		}
    	return found;
    }

	private boolean validateInputProduct() {
		
		boolean valid = true;

		if(txtPName.getText().equals("")) {
			valid = false;
		}

		if(!rbMainDish.isSelected() && !rbAdition.isSelected() && !rbDrink.isSelected()) {
			valid = false;
		}

		return valid;
	}
	
	 public void initizalizeTableProducts() {
		 ObservableList<Product> productArray = FXCollections.observableArrayList(restaurant.getProducts());
		 
		 colPname.setCellValueFactory(new PropertyValueFactory<Product,String>("name"));  		 
		 
		 colPIngs.setCellValueFactory(new PropertyValueFactory<Product,String>("ingredients"));
		 
		 colLEP.setCellValueFactory(new PropertyValueFactory<Product,String>("lastEditorRef"));
		 
		 colPCode.setCellValueFactory(new PropertyValueFactory<Product,String>("code"));
		 
		 coPPrice.setCellValueFactory(new PropertyValueFactory<Product,String>("price")); 
		 
		 coPPrice.setCellValueFactory(new PropertyValueFactory<Product,String>("price"));    	

		 colPav.setCellValueFactory(new PropertyValueFactory<Product,String>("available"));    	
		 
		 colPType.setCellValueFactory(new PropertyValueFactory<Product,String>("type"));    	
		 
		 tvProducts.setItems(productArray);	 
	 }
	 
	 @FXML
	 public void loadEditProduct(ActionEvent event) {
		 
		 Product productX = tvProducts.getSelectionModel().getSelectedItem();
		 referenceProduct = productX;
		 
		 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editProduct-pane.fxml"));
			fxmlLoader.setController(this); 	
			
			Parent addContactPane = null;
			try {
				addContactPane = fxmlLoader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			mainPane.getChildren().clear();
			String css = "styles/tableStyle.css";
			addContactPane.getStylesheets().add(css);
			mainPane.getChildren().setAll(addContactPane);
			
			//set Screen
			txtNewProductName.setPromptText(referenceProduct.getName());
			
			//available
			if(productX.isAvailable()) {
				rbProductAvailable.setSelected(true);
			}else {
				rbProductUnavailable.setSelected(true);
			}
			
			//type
			if(productX.getType().toString().equals("MAIN_DISH")){
				rbPMainDish.setSelected(true);
			}else if(productX.getType().toString().equals("ADDITIONAL_DISH")){
				rbPAd.setSelected(true);
			}else if(productX.getType().toString().equals("DRINK")){
				rbPDrink.setSelected(true);
			}
			
			initializeIngsInProduct(referenceProduct);
			initializeAllRegisIngs();
			initSizesProduct(referenceProduct);
			
	 }
	 
	 @FXML
	 public void addIngrToProduct(ActionEvent event) {
		 
		 Ingredient ingrToAdd = tvallInings.getSelectionModel().getSelectedItem();
		 
		 if(ingrToAdd!=null && actionIngtxt.getText().isEmpty()) {
				 
			actionIngtxt.setText(""+ingrToAdd.getCode());
		 }
		 	 
		 if(restaurant.ingredientIsAvailable(Integer.parseInt(actionIngtxt.getText()))) {
				 
			tempIngrsCodes.add(Integer.parseInt(actionIngtxt.getText()));
			referenceProduct.getIngredients().add(restaurant.getIngredientByCode(Integer.parseInt(actionIngtxt.getText())));
			loadEditProduct(null);
		 }else {
			//alerta no disponible
			Alert warning = new Alert(AlertType.WARNING);
 			warning.setTitle("Operación inválida");
 			warning.setContentText("El ingrediente seleccionado está deshabilitado! No se puede agregar a ningún" +
     							 "producto");
 			warning.showAndWait();
		 }
	 }

    @FXML
    public void removeIngrFromProduct(ActionEvent event) {
    	
    	Ingredient ingrToRemove = tvIdp.getSelectionModel().getSelectedItem();
    	
		if(ingrToRemove!=null) {
			
			int posToRem =searhIngInTempProduct(ingrToRemove.getCode());
			if(posToRem!=-1){
				referenceProduct.getIngredients().remove(posToRem);
				//System.out.println("");
				loadEditProduct(null);
			}		
			
		}else if(!actionIngtxt.getText().equals(actionIngtxt.getPromptText()) || !actionIngtxt.getText().isEmpty()) {
			
			int removeCod = restaurant.binarySearchIng(actionIngtxt.getText(), restaurant.getIngredients());
			int posToRemo = searhIngInTempProduct(restaurant.getIngredients().get(removeCod).getCode());

			if(posToRemo!=-1) {
				
				referenceProduct.getIngredients().remove(posToRemo);
				loadEditProduct(null);
			}	
		}

    }
    
    public int searhIngCode(int code) {
    	
    	int pos = -1;
    	boolean found = false;
		for (int i = 0; i < tempIngrsCodes.size() && !found; i++) {
			if(code == tempIngrsCodes.get(i)){
				pos = i;
				found = true;
			}
		}
		return pos;
    }
    
	 public int searhIngInTempProduct(int code) {
	    	
	    	int pos = -1;
	    	boolean found = false;
			for (int i = 0; i < referenceProduct.getIngredients().size() && !found; i++) {
				if(code == referenceProduct.getIngredients().get(i).getCode()){
					pos = i;
					found = true;
				}
			}
			return pos;
	    }
   
	 @FXML
	 public void addSizeToProduct(ActionEvent event) {
		 if(!txtAddNameSize.getText().isEmpty() && !txtMultiply.getText().isEmpty()) {
			 referenceProduct.addSize(txtAddNameSize.getText(), Double.parseDouble(txtMultiply.getText()));
			 loadEditProduct(null);
		 }
	 }
	 
	 @FXML
	 public void removeSizeFromProduct(ActionEvent event) {
		 boolean found = false;
		 int pos =-1;
		 
		 if(!txtAddNameSize.getText().isEmpty()) {
			 for (int i = 0; i < referenceProduct.getSizes().size() && !found; i++) {
				if(referenceProduct.getSizes().get(i).getSize().equalsIgnoreCase(txtAddNameSize.getText())) {
					found = true;
					pos = i;
				}
			}
		 }
		 if(pos!=-1) {
			 referenceProduct.getSizes().remove(pos);
			 loadEditProduct(null);
		 }
		 
     }
	 
	 
    @FXML
    public void updateProduct(ActionEvent event) {
    	
    	if(txtNewProductName.getText().isEmpty()) {
    		txtNewProductName.setText(referenceProduct.getName());
    	}
    	
    	//available
    	boolean isAvailable=true;
		if(rbProductUnavailable.isSelected()) {
			isAvailable=false;
		}
		
		//type
		String type="";
		if(rbPMainDish.isSelected()) {
			type = "MAIN_DISH";
		}else if(rbPAd.isSelected()) {
			type = "ADDIOTIONAL_DISH";
		}else {
			type = "DRINK";
		}
    	
    	tempIngrsCodes.clear();
    	System.out.println("INGS SIZE: "+ tempIngrsCodes.size());
    	
    	for (int i = 0; i < referenceProduct.getIngredients().size() ; i++) {
			tempIngrsCodes.add(referenceProduct.getIngredients().get(i).getCode());
			System.out.println("CODES INGS: "+referenceProduct.getIngredients().get(i).getCode());
		}
    	
    	ArrayList<String> sizesNames = new ArrayList<>();
    	ArrayList<Double> priceFactors = new ArrayList<>();
    	
    	for (int i = 0; i < referenceProduct.getSizes().size(); i++) {
			sizesNames.add(referenceProduct.getSizes().get(i).getSize());
			priceFactors.add(referenceProduct.getSizes().get(i).getPriceFactor());
		}
    	
    	
		try {
			restaurant.updateProduct(txtNewProductName.getText(), actualUser, referenceProduct.getCode(), tempIngrsCodes, type, isAvailable, sizesNames, priceFactors);
		} catch (IOException e) {
			
			e.printStackTrace();
		}	
   	}
    
    public void initializeIngsInProduct(Product px) {
    	
    	ObservableList<Ingredient> ingredientsInProduct = FXCollections.observableArrayList(px.getIngredients()); 
    	
		colIngInProduct.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("name"));  		 
		 
		colIngInPCode.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("code"));
		
		colIngInPPrice.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("price"));    		
		 
		tvIdp.setItems(ingredientsInProduct);
    }
    
    public void initializeAllRegisIngs() {
    	ObservableList<Ingredient> ingredientsInProduct = FXCollections.observableArrayList(restaurant.getIngredients());
		 
    	colAlIngsNames.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("name"));  		 
		 
    	colAllIngsCodes.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("code"));
		 
    	colAllINgPrices.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("price"));    		
		 
    	tvallInings.setItems(ingredientsInProduct);
    }
    
    public void initSizesProduct(Product px) {
    	ObservableList<Size> productSizes = FXCollections.observableArrayList(px.getSizes());
		 
    	colProdSizeName.setCellValueFactory(new PropertyValueFactory<Size,String>("size"));  		 
		 
    	colProdSizePrice.setCellValueFactory(new PropertyValueFactory<Size,String>("priceFactor"));   		
		
    	tvProdSizes.setItems(productSizes);
    }
    
    @FXML
    public void loadAdminOrders(ActionEvent event) {
    	
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createOrder-pane.fxml"));
		fxmlLoader.setController(this); 	
		
		Parent addContactPane = null;
		try {
			addContactPane = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainPane.getChildren().clear();
		String css = "styles/tableStyle.css";
		addContactPane.getStylesheets().add(css);
		mainPane.getChildren().setAll(addContactPane);
		initializePorudctsInCreateOrder();
		intializeOrders();
    }
    
    @FXML
    public void addProductToOrder(ActionEvent event) {
    	
    	if(!txtOrderProductCode.getText().isEmpty() && !txtOrderProductAmount.getText().isEmpty() && !txtOrderProductSize.getText().isEmpty()) {
    		
    		boolean productExists = restaurant.getProductByCode(Integer.parseInt(txtOrderProductCode.getText())) != null;
    		boolean sizeExists = false;
    		if (productExists) {
    			sizeExists = restaurant.getProductByCode(Integer.parseInt(txtOrderProductCode.getText())).getSizeByName(txtOrderProductSize.getText()) != null;
			}

    		try {
    			int numberAmount = Integer.parseInt(txtOrderProductAmount.getText());
    			boolean isPositive = numberAmount > 0;
    					
    			if(productExists && sizeExists && isPositive) {
        			
        			tempProductCodes.add(Integer.parseInt(txtOrderProductCode.getText()));
        			tempProductsAmounts.add(numberAmount);
        			tempProductsSizes.add(txtOrderProductSize.getText());
        		}else {
        			//alerta el producto o el tamaño no existen, o el numero es negativo
        			Alert warning = new Alert(AlertType.WARNING);
        			warning.setTitle("Error añadiendo el producto a la orden");
        			String warningString = "Se puede haber presentado alguno de los siguientes errores: \n";
        			if (!isPositive) {
						warningString += " - El número ingresado no es mayor que 0 \n";
					}
        			
        			if (!productExists) {
        				warningString += "- No existe un producto con el código ingresado \n";
					}
        			
        			if (!sizeExists) {
        				warningString += "- El producto no tiene el tamaño indicado \n";
					}
        			warning.setContentText(warningString);
        			warning.showAndWait();
        			
        		}
    		}catch(NumberFormatException e) {
    			//alerta el txt no es un numero
    			Alert error = new Alert(AlertType.ERROR);
    			error.setTitle("Cantidad inválida");
    			error.setContentText("La cantidad ingresada no es un número! Ingrese una cantidad válida");
    			error.showAndWait();
    		}
    				
    		
    	}else {
    		//alerta algun textfield vacio
    		warningEmpyText();
    	}
    }
    
    public void warningEmpyText() {
    	Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Campos Vacíos");
		alert.setContentText("Al menos uno de los dos campos está vacío!");
		alert.showAndWait();
    }

    @FXML
    public void fillProductTextFields(MouseEvent event) {
    	
    	Product productX = tvProductsList.getSelectionModel().getSelectedItem();	
    	
    	if(productX!=null) {
    		
    		txtOrderProductCode.setText(""+productX.getCode());
    		txtOrderProductAmount.setText(""+1);
    		txtOrderProductSize.setText("Standard");
    	}
    }
    
    @FXML
    public void createOrder(ActionEvent event) {
    		
		LocalDateTime orderTime = LocalDateTime.now();
		
		/*String formato = "yyyy-MM-dd HH:mm:ss";
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern(formato);
		LocalDateTime ahora = LocalDateTime.now();
		String rightNow = formateador.format(ahora);*/
		
		int id = restaurant.searchEmployeeByUserName(actualUser);
		String state ="REQUESTED";
		
		try {
			if(clientRef != null) {
				restaurant.createOrder(tempProductCodes, tempProductsAmounts, tempProductsSizes, clientRef, id, orderTime, txtOrderOBs.getText(), state);
			}else {
				//Alert no hay referenica al cliente 
				Alert warning = new Alert(AlertType.WARNING);
				warning.setTitle("Cliente no encontrado");
				warning.setContentText("No se ha ingresado un cliente válido.");
				warning.showAndWait();
			}
			
			clientRef = null;
			tempProductCodes.clear();
			tempProductsAmounts.clear();
			tempProductsSizes.clear();
			
		} catch (IOException e) {
			 	
			e.printStackTrace();
		}
    }

    @FXML
    public void loadEditOrder(ActionEvent event) {
    	    	
    	Order orderX = tvOrders.getSelectionModel().getSelectedItem();
    	referenceOrder = orderX;
    	orderCodeReference = orderX.getCode();
    	
    	for (int i = 0; i < orderX.getOrderProducts().size(); i++) {
    		if(tempProductCodes.size()<orderX.getOrderProducts().size()) {
    			
    			tempProductCodes.add(orderX.getOrderProducts().get(i).getCode());
    			tempProductsSizes.add(orderX.getSizes().get(i).getSize());
    			tempProductsAmounts.add(orderX.getAmountPerEach().get(i));
    		}
		}
    	
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editOrder-pane.fxml"));
		fxmlLoader.setController(this); 	
		
		Parent addContactPane = null;
		try {
			addContactPane = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainPane.getChildren().clear();
		String css = "styles/tableStyle.css";
		addContactPane.getStylesheets().add(css);
		mainPane.getChildren().setAll(addContactPane);
		
		//state
		if(orderX.getState().getState().equals("Solicitado")) {
			radioBtnState1.setSelected(true);
		}else if(orderX.getState().getState().equals("En proceso")){
			radioBtnState2.setSelected(true);
		}else if(orderX.getState().getState().equals("Enviado")){
			radioBtnState3.setSelected(true);
		}else if(orderX.getState().getState().equals("Entregado")){
			radioBtnState4.setSelected(true);
		}
		
		initializeProductsInOrder(referenceOrder);
		initializeProductsInEditingOrder();

    }
    
    public void initializePorudctsInCreateOrder() {
    	ObservableList<Product> allProducts = FXCollections.observableArrayList(restaurant.getProducts());
		 
    	colProductsNames.setCellValueFactory(new PropertyValueFactory<Product,String>("name"));  		 
		 
    	colProductsCodes.setCellValueFactory(new PropertyValueFactory<Product,String>("code"));
		 
    	colProductsPrices.setCellValueFactory(new PropertyValueFactory<Product,String>("price"));    		
		 
    	tvProductsList.setItems(allProducts);
    }
    
    public void intializeOrders() {
    	ObservableList<Order> allOrders = FXCollections.observableArrayList(restaurant.getOrders());
		 
    	colOrdersClients.setCellValueFactory(new PropertyValueFactory<Order,String>("clientRef"));  		 
		 
    	colOrdersCodes.setCellValueFactory(new PropertyValueFactory<Order,String>("code"));
		 
    	colOrdersStates.setCellValueFactory(new PropertyValueFactory<Order,String>("state"));

    	colOrdersPrices.setCellValueFactory(new PropertyValueFactory<Order,String>("totalPrice"));    		
		 
    	tvOrders.setItems(allOrders);
    }
    
    @FXML
    public void eOaddIngrToProduct(ActionEvent event) {
    	Product productX = tvregisteredProducts.getSelectionModel().getSelectedItem();
    	
    	//product added by selecting an item from the table
    	if(productX!=null) {
    		
    		tempProductCodes.add(productX.getCode());
    		referenceOrder.getOrderProducts().add(productX);
    		
    		if(txtEOAmount.getText().isEmpty() && txtEOSize.getText().isEmpty()) {
    			tempProductsAmounts.add(1);
    			tempProductsSizes.add(Product.DEFAULT_SIZE); 
    			loadEditOrder(null);
    			
    		}else if (!txtEOAmount.getText().isEmpty() && txtEOSize.getText().isEmpty()) {
    			
    			tempProductsAmounts.add(Integer.parseInt(txtEOAmount.getText()));
    			tempProductsSizes.add(Product.DEFAULT_SIZE); 
    			loadEditOrder(null);
    			
    		}else if(txtEOAmount.getText().isEmpty() && !txtEOSize.getText().isEmpty()) {
    			
    			tempProductsAmounts.add(1);
    			
    			Size sizeX =productX.getSizeByName(txtEOSize.getText());
    			
    			if(sizeX!=null) {
    				tempProductsSizes.add(txtEOSize.getText()); 
    				loadEditOrder(null);
    			}
    		}else {
    			tempProductsAmounts.add(Integer.parseInt(txtEOAmount.getText()));
    			Size sizeX =productX.getSizeByName(txtEOSize.getText());
    			
    			if(sizeX!=null) {
    				tempProductsSizes.add(txtEOSize.getText()); 
    				loadEditOrder(null);
    			}  			
    			
    		}
    	//Product added by searching the code
    	}else if (!txtEOProductName.getText().isEmpty()) {
    		Product temProduct = restaurant.getProductByCode(Integer.parseInt(txtEOProductName.getText()));
    		Size sizeX;
    		
    		if(temProduct!=null) {
    			
    			tempProductCodes.add(temProduct.getCode());
    			referenceOrder.getOrderProducts().add(temProduct);
    			
    			if(txtEOAmount.getText().isEmpty() && txtEOSize.getText().isEmpty()) {
        			tempProductsAmounts.add(1);
        			tempProductsSizes.add(Product.DEFAULT_SIZE); 
        			loadEditOrder(null);
        			
        		}else if (!txtEOAmount.getText().isEmpty() && txtEOSize.getText().isEmpty()) {
        			
        			tempProductsAmounts.add(Integer.parseInt(txtEOAmount.getText()));
        			tempProductsSizes.add(Product.DEFAULT_SIZE); 
        			loadEditOrder(null);
        			
        		}else if(txtEOAmount.getText().isEmpty() && !txtEOSize.getText().isEmpty()) {
        			
        			tempProductsAmounts.add(1);
        			
        			sizeX =temProduct.getSizeByName(txtEOSize.getText());
        			
        			if(sizeX!=null) {
        				tempProductsSizes.add(txtEOSize.getText()); 
        				loadEditOrder(null);
        			}
        			
        		}else {
        			tempProductsAmounts.add(Integer.parseInt(txtEOAmount.getText()));
        			 sizeX = temProduct.getSizeByName(txtEOSize.getText());
        			
        			if(sizeX!=null) {
        				tempProductsSizes.add(txtEOSize.getText()); 
        				loadEditOrder(null);
        			}  			
        		}
    		}
    	}
    	
    	System.out.println("SIZE AFTER ADDITION: "+tempProductCodes.size());
    	System.out.println("SIZE sizes: "+tempProductsSizes.size());
    	System.out.println("SIZE amount: "+tempProductsAmounts.size());
    }

    @FXML
    public void eOremoveIngrFromProduct(ActionEvent event) {
    	
    	Product productX = tvPIO.getSelectionModel().getSelectedItem();
    	
    	int posToRemoveInRef;
    	Size sizeX;

		//if any filed is empty it fills it with default values (1 for amounts, default size for sizes and the code of the selected item)
    	if(txtEOAmount.getText().isEmpty()) {
    		txtEOAmount.setText(""+1);
    	}
    	if(txtEOSize.getText().isEmpty()) {
    		txtEOSize.setText(Product.DEFAULT_SIZE);
    	}
    	
    	if(txtEOProductName.getText().isEmpty() && productX!=null) {
    		txtEOProductName.setText(""+productX.getCode());
    	}
    	
    	productX = restaurant.getProductByCode(Integer.parseInt(txtEOProductName.getText()));
    	sizeX = productX.getSizeByName(txtEOSize.getText());
		
		if(sizeX!=null) {
			posToRemoveInRef = searchProductByCodeInReferenceOrder(Integer.parseInt(txtEOProductName.getText()), txtEOSize.getText());
			
			if(posToRemoveInRef!=-1) {
				//checks if the amount number of products to remove is lower than the actual amount
				if(Integer.parseInt(txtEOAmount.getText())<tempProductsAmounts.get(posToRemoveInRef)) {
					
					tempProductsAmounts.set(posToRemoveInRef, tempProductsAmounts.get(posToRemoveInRef)-Integer.parseInt(txtEOAmount.getText()));
				//The amount of products to remove is equal or greater than the existing amount
				}else{
					tempProductCodes.remove(posToRemoveInRef);
    				tempProductsSizes.remove(posToRemoveInRef);
    				tempProductsAmounts.remove(posToRemoveInRef);
    				referenceOrder.getOrderProducts().remove(posToRemoveInRef);
				}
				loadEditOrder(null);
    		}	
		}
    }    	
    
    
    public int searchProductByCode(int code){
    	
    	int pos = -1;
    	boolean found = false;
    	
    	for (int i = 0; i < tempProductCodes.size() && !found; i++) {
 
			if(tempProductCodes.get(i) == code) {
				pos = i;
				found = true;
			}
		}
    	return pos;
    }
    
    public int searchProductByCodeInReferenceOrder(int code, String size){
    	
    	int pos = -1;
    	boolean found = false;
    	
    	for (int i = 0; i < tempProductCodes.size() && !found; i++) {
			if(tempProductCodes.get(i) == code && tempProductsSizes.get(i).equals(size)) {
				pos = i;
				found = true;
			}
		}
    	return pos;
    }    
    
    @FXML
    public void eOupdateProduct(ActionEvent event) {
    	try {
    		 			
			restaurant.updateOrder(orderCodeReference, tempProductCodes, tempProductsAmounts, tempProductsSizes);
			
		} catch (IOException e) {
		
			e.printStackTrace();
		}
    	loadEditOrder(null);
    }
    
    public void initializeProductsInOrder(Order orderX) {
    	
    	ObservableList<Product> productsInOrder = FXCollections.observableArrayList(orderX.getOrderProducts());
		 
    	colEOProductName.setCellValueFactory(new PropertyValueFactory<Product,String>("name"));  		 
		 
    	colEOProductCode.setCellValueFactory(new PropertyValueFactory<Product,String>("code"));
		 
    	colEOProductPrice.setCellValueFactory(new PropertyValueFactory<Product,String>("price"));
		 
    	tvPIO.setItems(productsInOrder);
    }
    
    public void initializeProductsInEditingOrder() {
    	
    	ObservableList<Product> productsRegistered = FXCollections.observableArrayList(restaurant.getProducts());
		 
    	colEOOrderName.setCellValueFactory(new PropertyValueFactory<Product,String>("name"));  		 
		 
    	colEOCode.setCellValueFactory(new PropertyValueFactory<Product,String>("code"));
		 
    	colEOPrice.setCellValueFactory(new PropertyValueFactory<Product,String>("price"));
		 
    	tvregisteredProducts.setItems(productsRegistered);
    }
    
    @FXML
    public void loadGenerateReports(ActionEvent event) {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("reports-pane.fxml"));
		fxmlLoader.setController(this);
		
		Parent addContactPane = null;
		try {
			addContactPane = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		mainPane.getChildren().clear();
		String css = "styles/tableStyle.css";
		addContactPane.getStylesheets().add(css);
		mainPane.getChildren().setAll(addContactPane);

		//dates default
		LocalDate localDate = LocalDate.now();
		//getStart of the day time
		LocalDateTime startOfDay = LocalDateTime.of(localDate, LocalTime.MIDNIGHT);
		//get end of the day time
		LocalDateTime endOfDay = LocalDateTime.of(localDate, LocalTime.MAX);
		//get format from products
		String formatString =restaurant.getOrderFormat();
		//set formatter
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(formatString);
		
		//
		if(txtInitialDate.getValue()==null) {
		
			dateFormat.format(startOfDay);
			txtInitialDate.setValue(startOfDay.toLocalDate());
		}		
		if(txtFinalDate.getValue()==null) {
			
			dateFormat.format(endOfDay);
			txtFinalDate.setValue(endOfDay.toLocalDate());
		}
    }
    
    @FXML
    public void generateProductReport(ActionEvent event) {
    	
    	LocalDateTime startOfDay = getLowDate();
    	LocalDateTime endOfDay = getTopDate();
    	
    	String separator = txtSeparator.getText();
    	
    	try {
			restaurant.generateReportProductsConsolidated(startOfDay, endOfDay, separator);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    }
    
    public LocalDateTime getLowDate() {
    	
    	LocalDate init = txtInitialDate.getValue();
    	
    	LocalDateTime startOfDay = LocalDateTime.of(init, LocalTime.MIDNIGHT); //00:00
    	
    	return startOfDay;
    }
    
    public LocalDateTime getTopDate() {
    	
    	//set formatter
    	LocalDate finit = txtFinalDate.getValue();
    	
    	LocalDateTime endOfDay = LocalDateTime.of(finit, LocalTime.MAX);	//23:59
    	
    	return endOfDay;
    }

    

    @FXML
    public void generateEmployeeReport(ActionEvent event) {
    	
    	LocalDateTime startOfDay = getLowDate();
    	LocalDateTime endOfDay = getTopDate();
    	
    	String separator = txtSeparator.getText();
    	
    	try {
			restaurant.generateReportEmployeesConsolidated(startOfDay, endOfDay, separator);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    }

    @FXML
    public void generateOrderReport(ActionEvent event) {
    	LocalDateTime startOfDay = getLowDate();
    	LocalDateTime endOfDay = getTopDate();
    	
    	String separator = txtSeparator.getText();
    	
    	try {
			restaurant.generateReportOrdersConsolidated(startOfDay, endOfDay, separator);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    }
    
    @FXML
    public void setStateForward(ActionEvent event) {
    	
		try {
			restaurant.updateStateOrder(orderCodeReference);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	loadEditOrder(null);
    }
    
    @FXML
    public void eliminateIngredient(ActionEvent event) {
    	
    	try {
    		
    		int ingredientPos = restaurant.binarySearchIng(referenceIngredient, restaurant.getIngredients());
    		
    		if(restaurant.getIngredients().get(ingredientPos).getReferences().isEmpty()) {
    			restaurant.deleteIngredient(restaurant.getIngredients().get(ingredientPos).getCode());
    			loadAdminIngredients(null);
    		}else {
    			//alert
    			Alert warning = new Alert(AlertType.WARNING);
    			warning.setTitle("Ingrediente referenciado!");
    			warning.setContentText("Al menos un producto tiene este ingrediente! No se puede eliminar un ingrediente referenciado");
    			warning.showAndWait();
    		}
						
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    }
    
    @FXML
    public void eliminateProduct(ActionEvent event) {
    	
    		try {
    			if(restaurant.getProductByCode(referenceProduct.getCode()).getRefCodes().isEmpty()) {
					restaurant.deleteProduct(referenceProduct.getCode());
					loadAdminProducts(null);
    			}else {
    	    		//alert product is referenced
    				Alert warning = new Alert(AlertType.WARNING);
    				warning.setTitle("Producto referenciado");
    				warning.setContentText( "Al menos una orden o ingrediente usa este producto! No se puede eliminar un" + 
    										" producto referenciado.");
    				warning.showAndWait();
    	    	}
			} catch (IOException e) {
				
				e.printStackTrace();
			}
    }
    
    @FXML
    public void imoprtProducts(ActionEvent event) {
    	
    	try {
			restaurant.importProducts();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    }

    @FXML
    public void importClients(ActionEvent event) {
    	try {
			restaurant.importClients();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    }

    @FXML
    public void importIngredients(ActionEvent event) {

    	try {
			restaurant.importIngredients();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    }

    @FXML
    public void importOrders(ActionEvent event) {

    	try {
			restaurant.importOrders();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    }
    
    @FXML
    public void loadAdminClients(ActionEvent event) {
    	
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createClient-pane.fxml"));
		fxmlLoader.setController(this); 	
		
		Parent addContactPane = null;
		try {
			addContactPane = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainPane.getChildren().clear();
		mainPane.getChildren().setAll(addContactPane);
    }

    @FXML
    public void loadAdminEmployees(ActionEvent event) {

    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createEmployee-pane.fxml"));
		fxmlLoader.setController(this); 	
		
		Parent addContactPane = null;
		try {
			addContactPane = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainPane.getChildren().clear();
		mainPane.getChildren().setAll(addContactPane);
    }

    @FXML
    public void loadAdminSystemUsers(ActionEvent event) {

    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("updateSystermUser-pane.fxml"));
		fxmlLoader.setController(this); 	
		
		Parent addContactPane = null;
		try {
			addContactPane = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainPane.getChildren().clear();
		mainPane.getChildren().setAll(addContactPane);
		
		int posSystemU = restaurant.searchSystemUser(actualUser);
		
		txtEidtUserName.setText(restaurant.getSystemUsers().get(posSystemU).getName());
		txtEditUserLatName.setText(restaurant.getSystemUsers().get(posSystemU).getLastName());
		txtEditUsername.setText(restaurant.getSystemUsers().get(posSystemU).getUserName());
		txtEditUserPassword.setText(restaurant.getSystemUsers().get(posSystemU).getPassword());
		txtEdituserCC.setText(restaurant.getSystemUsers().get(posSystemU).getCc());
		
		txtEdituserCC.setEditable(false);
    }
    
    @FXML
    public void createClient(ActionEvent event) {
    	
    	if(!txtClientName.getText().isEmpty() && !txtClientLastName.getText().isEmpty() && !txtClientCC.getText().isEmpty() && 
		!txtClientAdress.getText().isEmpty() && !txtClientPhone.getText().isEmpty()) {
		
			try {
				if(!restaurant.clientAlreadyExistByCC(txtClientCC.getText())) {
					restaurant.createClient(txtClientName.getText(), txtClientLastName.getText(), txtClientCC.getText(), txtClientAdress.getText(), txtClientPhone.getText(), txtClientObsField.getText());
				}else {
					//alert
					Alert error = new Alert(AlertType.ERROR);
					error.setTitle("El cliente ya existe");
					error.setContentText("Ya existe un cliente con el número de cédula indicado.");
					error.showAndWait();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }
    
    @FXML
    public void searchClient(ActionEvent event) {
    	
    	if(!txtSearchClientName.getText().isEmpty() && !txtSearhClientLastName.getText().isEmpty()) {
    		
    		long start = System.nanoTime();
    		int posClient = restaurant.searchClientByName(txtSearchClientName.getText(), txtSearhClientLastName.getText());
    		long end = System.nanoTime();
    		
    		searchTime = end-start;
    		labelSearchTimeClient.setText("" + searchTime + " ns");
    		
    		if(posClient != -1) {
    			clientRef = restaurant.getClients().get(posClient).getCc();
    			System.out.println(clientRef);
    		}else {
    			//alert cliente no encontrado
    			Alert warning = new Alert(AlertType.WARNING);
    			warning.setTitle("Cliente no encontrado");
    			warning.setContentText("No se ha encontrado un cliente con los nombres indicados");
    			warning.showAndWait();
    		}
    	}
    }
    
    @FXML
    public void editClient(ActionEvent event) {
    	
    	if(!txtCCToEdit.getText().isEmpty()) {
    		int posClient = restaurant.searchClientByCc(txtCCToEdit.getText());
    		
    		if(posClient != -1) {
    			
    			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editClient-pane.fxml"));
    			fxmlLoader.setController(this); 	
    			
    			Parent addContactPane = null;
    			try {
    				addContactPane = fxmlLoader.load();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    			mainPane.getChildren().clear();
    			mainPane.getChildren().setAll(addContactPane);
    			
    			txtNewClientName.setText(restaurant.getClients().get(posClient).getName());
    			txtNewClientLastName.setText(restaurant.getClients().get(posClient).getLastName());
    			txtUpdateClientCC.setText(restaurant.getClients().get(posClient).getCc());
    			txtNewClientAdress.setText(restaurant.getClients().get(posClient).getAdress());
    			txtNewClientPhone.setText(restaurant.getClients().get(posClient).getPhone());
    			txtNewClientObsField.setText(restaurant.getClients().get(posClient).getObsField());
    			
    			txtUpdateClientCC.setEditable(false);
    			
    		}else {
    			//alerta cedula no encontrada
    			Alert warning = new Alert(AlertType.WARNING);
    			warning.setTitle("Cédula no encontrada");
    			warning.setContentText("Ningun cliente de la lista de clientes tiene la cédula indicada");
    			warning.showAndWait();
    		}
    	}else {
    		//alerta textfield vacío
    		warningEmpyText();
    	}
    }
    
    @FXML
    public void updateClient(ActionEvent event) {
    	System.out.println("HM");
    	
    	System.out.println(txtNewClientLastName.getText().isEmpty());
    	
    	if(!txtNewClientName.getText().isEmpty() && !txtNewClientLastName.getText().isEmpty() && !txtNewClientAdress.getText().isEmpty() &&
    			!txtNewClientPhone.getText().isEmpty()) {
    		System.out.println("INSIDE IF");
       		
    		try {
				restaurant.updateClient(txtCCToEdit.getText(), txtNewClientName.getText(), txtNewClientLastName.getText(), txtNewClientAdress.getText(),
						txtNewClientPhone.getText(), txtNewClientObsField.getText());
				System.out.println("CLIENT UPDATED");
			} catch (IOException e) {

				e.printStackTrace();
			}
    	}else{
    		//alert empty fields
    		warningEmpyText();
    	}
    }
    
    @FXML
    public void createEmployee(ActionEvent event) {
   
    	if(!txtEmployeeName.getText().isEmpty() && !txtEmployeeLastName.getText().isEmpty() && !txtEmployeeCc.getText().isEmpty()) {
    		
    		try {

				restaurant.createEmployee(txtEmployeeName.getText(), txtEmployeeLastName.getText(), txtEmployeeCc.getText());

			} catch (IOException e) {
				
				e.printStackTrace();
			}
    	}else {
    		//alert empty fields
    		warningEmpyText();
    	}
    }

    @FXML
    public void editEmployee(ActionEvent event) {

    	if(!txtCCEmployeeToEdit.getText().isEmpty()) {
    		
    		int posEmployee = restaurant.searchEmployeeByCC(txtCCEmployeeToEdit.getText());
    		
    		if(posEmployee != -1) {
    			
    			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("updateEmployee-pane.fxml"));
    			fxmlLoader.setController(this); 	
    			
    			Parent addContactPane = null;
    			try {
    				addContactPane = fxmlLoader.load();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    			mainPane.getChildren().clear();
    			mainPane.getChildren().setAll(addContactPane);
    			
    			txtNewEmploName.setText(restaurant.getEmployees().get(posEmployee).getName());
    			txtNewEmploLastName.setText(restaurant.getEmployees().get(posEmployee).getLastName());
    			txtUpdateEmploCC.setText(restaurant.getEmployees().get(posEmployee).getCc());
    			
    			txtUpdateEmploCC.setEditable(false);
    		}
    	}else {
    		//alert texftfield vacio
    		warningEmpyText();
    	}
    }
    
    @FXML
    public void updateEmployeeX(ActionEvent event) {
    	
    	if(!txtNewEmploName.getText().isEmpty() && !txtNewEmploLastName.getText().isEmpty()) {
    		
    		try {
				restaurant.updateEmployee(txtUpdateEmploCC.getText(), txtNewEmploName.getText(), txtNewEmploLastName.getText());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}else {
    		//alert empty fields
    		warningEmpyText();
    	}
    }

    @FXML
    public void updateUser(ActionEvent event) {
    	
    	if(!txtEidtUserName.getText().isEmpty() && !txtEditUserLatName.getText().isEmpty() && !txtEditUsername.getText().isEmpty() && !txtEditUserPassword.getText().isEmpty()) {
    		try {
				restaurant.updateSystemUser(actualUser, txtEidtUserName.getText(), txtEditUserLatName.getText(), txtEditUsername.getText(), txtEditUserPassword.getText());
				System.out.println("ACTUALIZADO EL USUARIO");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
    	}else {
    		//alert empty fields
    		warningEmpyText();
    	}
    }
    
    @FXML
    public void loadVisualizeInfo(ActionEvent event) {
    	
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminVisualizations-pane.fxml"));
		fxmlLoader.setController(this); 	
		
		Parent addContactPane = null;
		try {
			addContactPane = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainPane.getChildren().clear();
		mainPane.getChildren().setAll(addContactPane);
    }
    
    @FXML
    void visualizeClients(ActionEvent event) {

    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("listClients-pane.fxml"));
		fxmlLoader.setController(this); 	
		
		Parent addContactPane = null;
		try {
			addContactPane = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainPane.getChildren().clear();
		mainPane.getChildren().setAll(addContactPane);
		initializeShowClients();
    }
    
    public void initializeShowClients() {
    	
    	ObservableList<Client> showClients = FXCollections.observableArrayList(restaurant.getClients());
		 
    	colSClientName.setCellValueFactory(new PropertyValueFactory<Client,String>("name"));  		 
		 
    	colSClientLastName.setCellValueFactory(new PropertyValueFactory<Client,String>("lastName"));
		 
    	colSClientCc.setCellValueFactory(new PropertyValueFactory<Client,String>("cc"));
    	
    	colSClientAdress.setCellValueFactory(new PropertyValueFactory<Client,String>("address"));
		
    	colSClientPhone.setCellValueFactory(new PropertyValueFactory<Client,String>("phone"));
		 
    	tvShowClients.setItems(showClients);
    }

    @FXML
    void visualizeEmployees(ActionEvent event) {

    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("listEmployees-pane.fxml"));
		fxmlLoader.setController(this); 	
		
		Parent addContactPane = null;
		try {
			addContactPane = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainPane.getChildren().clear();
		mainPane.getChildren().setAll(addContactPane);
		initializeShowEmployees();
    }
    
    public void initializeShowEmployees() {
    	
    	ObservableList<Employee> showEmployees = FXCollections.observableArrayList(restaurant.getEmployees());
		 
    	colSEmployeeName.setCellValueFactory(new PropertyValueFactory<Employee,String>("name"));  		 
		 
    	colSEmployeeLastName.setCellValueFactory(new PropertyValueFactory<Employee,String>("lastName"));
		 
    	colSEmployeeCc.setCellValueFactory(new PropertyValueFactory<Employee,String>("cc"));
    	
    	colSEmployeeId.setCellValueFactory(new PropertyValueFactory<Employee,String>("employeeId"));
		 
    	tvShowEmployees.setItems(showEmployees);
    }

    @FXML
    void visualizeUsers(ActionEvent event) {

    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("listUsers-pane.fxml"));
		fxmlLoader.setController(this); 	
		
		Parent addContactPane = null;
		try {
			addContactPane = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainPane.getChildren().clear();
		mainPane.getChildren().setAll(addContactPane);
		initializeShowUsers();
    }
    
    public void initializeShowUsers() {
    	
    	ObservableList<SystemUser> showUsers = FXCollections.observableArrayList(restaurant.getSystemUsers());
		 
    	colSUserName.setCellValueFactory(new PropertyValueFactory<SystemUser,String>("name"));  		 
		 
    	colSUserLastName.setCellValueFactory(new PropertyValueFactory<SystemUser,String>("lastName"));
		 
    	colSUserCc.setCellValueFactory(new PropertyValueFactory<SystemUser,String>("cc"));
    	
    	colSUserAdress.setCellValueFactory(new PropertyValueFactory<SystemUser,String>("username"));
    	
    	colSUserPhone.setCellValueFactory(new PropertyValueFactory<SystemUser,String>("password"));
		 
    	tvShowUsers.setItems(showUsers);
    }
    
    @FXML
    void visualizeIngredients(ActionEvent event) {

    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("listIngredients-pane.fxml"));
		fxmlLoader.setController(this); 	
		
		Parent addContactPane = null;
		try {
			addContactPane = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainPane.getChildren().clear();
		mainPane.getChildren().setAll(addContactPane);
		initializeShowIngredients();
    }
    
    public void initializeShowIngredients() {
    	
    	ObservableList<Ingredient> showIngredients = FXCollections.observableArrayList(restaurant.getIngredients());
		 
    	colSIngredientName.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("name"));  		 
		 
    	colSIngredientLastName.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("creatorRef"));
		 
    	colSIngredientCc.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("lastEditorRef"));
    	
    	colSIngredientAdress.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("code"));
    	
    	colSIngredientPhone.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("productTypeS"));
		 
    	tvShowIngredients.setItems(showIngredients);
    }

    @FXML
    void visualizeProducts(ActionEvent event) {

    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("listProducts-pane.fxml"));
		fxmlLoader.setController(this); 	
		
		Parent addContactPane = null;
		try {
			addContactPane = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainPane.getChildren().clear();
		mainPane.getChildren().setAll(addContactPane);
		initializeShowProducts();
    }
    
 public void initializeShowProducts() {
    	
    	ObservableList<Product> showProducts = FXCollections.observableArrayList(restaurant.getProducts());
		 
    	colSProductName.setCellValueFactory(new PropertyValueFactory<Product,String>("name"));  		 
		 
    	colSProductCreator.setCellValueFactory(new PropertyValueFactory<Product,String>("creatorRef"));
    	
    	colSProductCode.setCellValueFactory(new PropertyValueFactory<Product,String>("code"));
    	
    	colSProductAmountIngredients.setCellValueFactory(new PropertyValueFactory<Product,String>("amountIngredients"));
    	
    	colSProductAvailabale.setCellValueFactory(new PropertyValueFactory<Product,String>("availableS"));
    	
    	colSProductPrice.setCellValueFactory(new PropertyValueFactory<Product,String>("productTypeS"));
    	
    	colSProductType.setCellValueFactory(new PropertyValueFactory<Product,String>("price"));
		 
    	tvShowProduct.setItems(showProducts);
    }
    
    @FXML
    void visualizeOrders(ActionEvent event) {

    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("listOrders-pane.fxml"));
		fxmlLoader.setController(this); 	
		
		Parent addContactPane = null;
		try {
			addContactPane = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainPane.getChildren().clear();
		mainPane.getChildren().setAll(addContactPane);
    }


   
}
