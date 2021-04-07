package gui_test;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Controller {

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
    private TableView<?> tableIngr;

    @FXML
    private TableColumn<?, ?> colIngr;

    @FXML
    private TableColumn<?, ?> colCreator;

    @FXML
    private TableColumn<?, ?> colLastE;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colValue;
    
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
    private TableView<?> tvIngP;

    @FXML
    private TableColumn<?, ?> colNip;

    @FXML
    private TableColumn<?, ?> colIngcreP;

    @FXML
    private TableColumn<?, ?> colIngLastEP;

    @FXML
    private TableColumn<?, ?> colIngCodP;

    @FXML
    private TableColumn<?, ?> colIngVal;
    
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
    private TableView<?> tvIdp;

    @FXML
    private TableColumn<?, ?> colIngInProduct;

    @FXML
    private TableColumn<?, ?> colIngInPCode;

    @FXML
    private TableColumn<?, ?> colIngInPPrice;

    @FXML
    private TableView<?> tvallInings;

    @FXML
    private TableColumn<?, ?> colAlIngsNames;

    @FXML
    private TableColumn<?, ?> colAllIngsCodes;

    @FXML
    private TableColumn<?, ?> colAllINgPrices;

    @FXML
    private TableView<?> tvProdSizes;

    @FXML
    private TableColumn<?, ?> colProdSizeName;

    @FXML
    private TableColumn<?, ?> colProdSizePrice;
    
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
    private TableView<?> tvProductsList;

    @FXML
    private TableColumn<?, ?> colProductsNames;

    @FXML
    private TableColumn<?, ?> colProductsCodes;

    @FXML
    private TableColumn<?, ?> colProductsPrices;

    @FXML
    private Label labelSearchTimeClient;

    @FXML
    private JFXTextField txtSearchClientName;

    @FXML
    private JFXTextField txtSearhClientLastName;

    //edit order table pane fields
    @FXML
    private TableView<?> tvOrders;

    @FXML
    private TableColumn<?, ?> colOrdersClients;

    @FXML
    private TableColumn<?, ?> colOrdersCodes;

    @FXML
    private TableColumn<?, ?> colOrdersStates;

    @FXML
    private TableColumn<?, ?> colOrdersPrices;
    
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
    private TableView<?> tvPIO;

    @FXML
    private TableColumn<?, ?> colEOProductName;

    @FXML
    private TableColumn<?, ?> colEOProductCode;

    @FXML
    private TableColumn<?, ?> colEOProductPrice;

    @FXML
    private TableView<?> tvregisteredProducts;

    @FXML
    private TableColumn<?, ?> colEOOrderName;

    @FXML
    private TableColumn<?, ?> colEOCode;

    @FXML
    private TableColumn<?, ?> colEOPrice;
    
    public Controller() {
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
