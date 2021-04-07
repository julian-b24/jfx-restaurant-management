package gui_test;

import java.io.IOException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.Client;
import model.Employee;
import model.Ingredient;
import model.Order;
import model.Product;
import model.Restaurant;
import model.Size;
import model.SystemUser;

public class Controller {

	private Restaurant restaurant;
	
	//other fields
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

	@FXML
    private AnchorPane mainPane;
    
    //Side bar fields
    @FXML
    private JFXButton sample;
    
    @FXML
    private Label nameLbl;

    @FXML
    private Label codeLbl;
    
    @FXML
    private Label timeLbl;
    
    @FXML
    private AnchorPane secondaryPane;
    
    //Tab pane options (Ingredient, Product, Order, Employee, Client) fields
    @FXML
    private AnchorPane createPane;

    @FXML
    private AnchorPane editPane;

    @FXML
    private AnchorPane visualizePane;
    
    //login pane fields
    @FXML
    private JFXTextField usernametxf;

    @FXML
    private JFXTextField passwordtxf;
    
    //register pane fields
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
    
    //Reports pane fields
    @FXML
    private JFXTextField txtSeparator;

    @FXML
    private JFXDatePicker txtInitialDate;

    @FXML
    private JFXDatePicker txtFinalDate;
    
    //Update user fields
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
	
    //create client pane fields
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
	
	//edit client pane fields
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

    @FXML
    private JFXTextField txtCCToEdit;
	
    //create employee fields
    @FXML
    private JFXTextField txtEmployeeName;

    @FXML
    private JFXTextField txtEmployeeLastName;

    @FXML
    private JFXTextField txtEmployeeCc;

    @FXML
    private JFXTextField txtCCEmployeeToEdit;
    
    //edit employee pane fields
    @FXML
    private JFXTextField txtNewEmploName;

    @FXML
    private JFXTextField txtNewEmploLastName;

    @FXML
    private JFXTextField txtUpdateEmploCC;
    
    //create ingredient pane fields
    @FXML
    private JFXTextField txtIngredientName;

    @FXML
    private JFXTextField txtIngredientValue;
    
    //edit ingredient fields
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

    @FXML
    private TableView<Ingredient> tableIngr;

    @FXML
    private TableColumn<Ingredient, String> colIngr;

    @FXML
    private TableColumn<Ingredient, String> colCreator;

    @FXML
    private TableColumn<Ingredient, String> colLastE;

    @FXML
    private TableColumn<Ingredient, Integer> colCode;

    @FXML
    private TableColumn<Ingredient, String> colValue;
    
    @FXML
    private TableColumn<Ingredient, Button> colEdit;

    
    //create product pane fields
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
    
    //edit product pane field
    @FXML
    private JFXTextField txtNewProductName;

    @FXML
    private JFXRadioButton rbProductUnavailable;

    @FXML
    private ToggleGroup pState;

    @FXML
    private JFXRadioButton rbProductAvailable;

    @FXML
    private VBox rbPMD;

    @FXML
    private JFXRadioButton rbPMainDish;

    @FXML
    private ToggleGroup ptype;

    @FXML
    private JFXRadioButton rbPAd;

    @FXML
    private JFXRadioButton rbPDrink;

    @FXML
    private JFXTextField actionIngtxt;

    @FXML
    private JFXTextField txtAddNameSize;

    @FXML
    private JFXTextField txtMultiply;

    @FXML
    private TableView<Ingredient> tvIdp;

    @FXML
    private TableColumn<Ingredient, String> colIngInProduct;

    @FXML
    private TableColumn<Ingredient, String> colIngInPCode;

    @FXML
    private TableColumn<Ingredient, String> colIngInPPrice;

    @FXML
    private TableView<Ingredient> tvallInings;

    @FXML
    private TableColumn<Ingredient, String> colAlIngsNames;

    @FXML
    private TableColumn<Ingredient, String> colAllIngsCodes;

    @FXML
    private TableColumn<Ingredient, String> colAllINgPrices;

    @FXML
    private TableView<Size> tvProdSizes;

    @FXML
    private TableColumn<Size, String> colProdSizeName;

    @FXML
    private TableColumn<Size, String> colProdSizePrice;
    
    //create order pane fields
    @FXML
    private JFXTextField txtOrderProductCode;

    @FXML
    private JFXTextField txtOrderProductAmount;

    @FXML
    private JFXTextField txtOrderProductSize;

    @FXML
    private JFXTextField txtOrderOBs;

    @FXML
    private TableView<Product> tvProductsList;

    @FXML
    private TableColumn<Product, String> colProductsNames;

    @FXML
    private TableColumn<Product, String> colProductsCodes;

    @FXML
    private TableColumn<Product, String> colProductsPrices;

    @FXML
    private Label labelSearchTimeClient;

    @FXML
    private JFXTextField txtSearchClientName;

    @FXML
    private JFXTextField txtSearhClientLastName;

    //edit order table pane fields
    @FXML
    private TableView<Order> tvOrders;

    @FXML
    private TableColumn<Order, String> colOrdersClients;

    @FXML
    private TableColumn<Order, String> colOrdersCodes;

    @FXML
    private TableColumn<Order, String> colOrdersStates;

    @FXML
    private TableColumn<Order, Double> colOrdersPrices;
    
    //edit order process pane fields
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
    private JFXTextField txtEOProductCode;

    @FXML
    private JFXTextField txtEOAmount;

    @FXML
    private JFXTextField txtEOSize;

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
    
    @FXML
    private TableView<Order> tvShowOrder;

    @FXML
    private TableColumn<Order, String> colSOrderCcClient;

    @FXML
    private TableColumn<Order, String> colSOrderCode;

    @FXML
    private TableColumn<Order, String> colSOrderDate;

    @FXML
    private TableColumn<Order, String> colSOrderState;
    
    public Controller(Restaurant restaurant) {
    	this.restaurant = restaurant;
		tempIngrsCodes = new ArrayList<>();
		tempProductCodes = new ArrayList<>();
		tempProductsAmounts = new ArrayList<>();
		tempProductsSizes = new ArrayList<>();
	}
    
    @FXML
    public void loadLogIn(ActionEvent event) {
    	
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-pane.fxml"));
			fxmlLoader.setController(this);
			Parent login = fxmlLoader.load();
			
			mainPane.getChildren().clear();
			mainPane.getChildren().setAll(login);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
	public void loadSideBar(ActionEvent event) {
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bar-menu-pane.fxml"));
			fxmlLoader.setController(this);
			Parent menuBar = fxmlLoader.load();
			
			mainPane.getChildren().clear();
			mainPane.getChildren().setAll(menuBar);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadTabPaneOptions(ActionEvent event) {
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tab-pane-options.fxml"));
			fxmlLoader.setController(this);
			Parent options = fxmlLoader.load();
			
			secondaryPane.getChildren().clear();
			secondaryPane.getChildren().setAll(options);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void loadCreate(String path) {
    	
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
			fxmlLoader.setController(this);
			Parent create = fxmlLoader.load();
			
			createPane.getChildren().clear();
			createPane.getChildren().setAll(create);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void loadEdit(String path) {
    	
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
			fxmlLoader.setController(this);
			Parent edit = fxmlLoader.load();
			
			editPane.getChildren().clear();
			editPane.getChildren().setAll(edit);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void loadVisualize(String path) {
    	
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
			fxmlLoader.setController(this);
			Parent visual = fxmlLoader.load();
			
			visualizePane.getChildren().clear();
			visualizePane.getChildren().setAll(visual);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
	public void loadIngredientOptions(ActionEvent event) {
		loadTabPaneOptions(null);
		loadCreate("create-ingredient.fxml");
		loadEdit("edit-ingredient.fxml");
		loadVisualize("list-ingredients.fxml");
	}
    
    @FXML
    public void loadProductOptions(ActionEvent event) {
    	loadTabPaneOptions(null);
		loadCreate("create-product.fxml");
		loadEdit("edit-product.fxml");
		loadVisualize("list-products.fxml");
    }
    
    @FXML
    public void loadOrderOptions(ActionEvent event) {
    	loadTabPaneOptions(null);
		loadCreate("create-order.fxml");
		loadEditOrderTable(null);
		loadVisualize("list-orders.fxml");
    }
    
    @FXML
    public void loadClientOptions(ActionEvent event) {
    	loadTabPaneOptions(null);
		loadCreate("create-client.fxml");
		loadEdit("edit-client.fxml");
		loadVisualize("list-clients.fxml");
    }
    
    
    @FXML
    public void loadEmployeeOptions(ActionEvent event) {
    	loadTabPaneOptions(null);
		loadCreate("create-employee.fxml");
		loadEdit("update-employee.fxml");
		loadVisualize("list-employees.fxml");
    }
    
    @FXML
    public void loadUserOptions() {
    	
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("list-users.fxml"));
			fxmlLoader.setController(this);
			Parent visual = fxmlLoader.load();
			
			secondaryPane.getChildren().clear();
			secondaryPane.getChildren().setAll(visual);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@FXML
	public void loadGenerateReports(ActionEvent event) {
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("reports-pane.fxml"));
			fxmlLoader.setController(this);
			Parent reports = fxmlLoader.load();
			
			secondaryPane.getChildren().clear();
			secondaryPane.getChildren().setAll(reports);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void loadImports(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("imports-pane.fxml"));
			fxmlLoader.setController(this);
			Parent imports = fxmlLoader.load();
			
			secondaryPane.getChildren().clear();
			secondaryPane.getChildren().setAll(imports);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
    public void loadEditUser(MouseEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("update-systerm-user.fxml"));
			fxmlLoader.setController(this);
			Parent editUser = fxmlLoader.load();
			
			secondaryPane.getChildren().clear();
			secondaryPane.getChildren().setAll(editUser);
			
			/*
			int posSystemU = restaurant.searchSystemUser(actualUser);
			
			txtEidtUserName.setText(restaurant.getSystemUsers().get(posSystemU).getName());
			txtEditUserLatName.setText(restaurant.getSystemUsers().get(posSystemU).getLastName());
			txtEditUsername.setText(restaurant.getSystemUsers().get(posSystemU).getUserName());
			txtEditUserPassword.setText(restaurant.getSystemUsers().get(posSystemU).getPassword());
			txtEdituserCC.setText(restaurant.getSystemUsers().get(posSystemU).getCc());
			
			txtEdituserCC.setEditable(false);
			*/
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@FXML
    public void loadEditOrder(ActionEvent event) {
		loadEdit("edit-order-process.fxml");
    }
	
	@FXML
    public void loadEditOrderTable(ActionEvent event) {
    	loadEdit("edit-order-table.fxml");
    }
	
	@FXML
	public void loadRegister(ActionEvent event) {
		
		try {
			
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register-user.fxml"));
			fxmlLoader.setController(this);
			Parent register = fxmlLoader.load();
			
			mainPane.getChildren().clear();
			mainPane.getChildren().setAll(register);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    public void loginUser(ActionEvent event) {

    }
	
	@FXML
    public void updateUser(ActionEvent event) {

    }
	
	//Report methods
	@FXML
    public void generateEmployeeReport(ActionEvent event) {

    }

    @FXML
    public void generateOrderReport(ActionEvent event) {

    }

    @FXML
    public void generateProductReport(ActionEvent event) {

    }
    
    //Import methods
    @FXML
    public void importProducts(ActionEvent event) {

    }

    @FXML
    public void importClients(ActionEvent event) {

    }

    @FXML
    public void importIngredients(ActionEvent event) {

    }

    @FXML
    public void importOrders(ActionEvent event) {

    }
    
    @FXML
    public void createClient(ActionEvent event) {
    	
    }
    
    @FXML
    public void editClient(ActionEvent event) {

    }

    @FXML
    public void updateClient(ActionEvent event) {

    }
    
    @FXML
    public void createEmployee(ActionEvent event) {

    }

    @FXML
    public void editEmployee(ActionEvent event) {

    }
    
    @FXML
    public void updateEmployeeX(ActionEvent event) {

    }
    
    @FXML
    public void createIngredient(ActionEvent event) {

    }
    
    @FXML
    public void eliminateIngredient(ActionEvent event) {

    }

    @FXML
    public void updateIngredient(ActionEvent event) {

    }
    
    @FXML
    public void addIngredientToProduct(ActionEvent event) {

    }
    
    @FXML
    public void addIngrToProduct(ActionEvent event) {

    }
    
    @FXML
    public void addSizeToProduct(ActionEvent event) {

    }

    @FXML
    public void createProduct(ActionEvent event) {

    }

    @FXML
    public void eliminateProduct(ActionEvent event) {

    }

    @FXML
    public void removeIngrFromProduct(ActionEvent event) {

    }

    @FXML
    public void removeSizeFromProduct(ActionEvent event) {

    }

    @FXML
    public void updateProduct(ActionEvent event) {

    }
    
    @FXML
    public void addProductToOrder(ActionEvent event) {

    }

    @FXML
    public void createOrder(ActionEvent event) {

    }

    @FXML
    public void fillProductTextFields(MouseEvent event) {

    }

    @FXML
    public void loadAdminClients(ActionEvent event) {

    }

    @FXML
    public void searchClient(ActionEvent event) {

    }
    
    @FXML
    public void eOaddProductToOrder(ActionEvent event) {

    }

    @FXML
    public void eOremoveIngrFromProduct(ActionEvent event) {

    }

    @FXML
    public void eOupdateProduct(ActionEvent event) {

    }

    @FXML
    public void fillEditOrderFields(MouseEvent event) {

    }

    @FXML
    public void setStateForward(ActionEvent event) {

    }
    
    @FXML
    public void createSystemUser(ActionEvent event) {

    }
    
}
