package gui_test;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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
	
	public Controller() {
	}
	
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
	
	public void loadSideBar(ActionEvent event) {
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bar-menu-pane.fxml"));
			fxmlLoader.setController(this);
			Parent menu_bar = fxmlLoader.load();
			
			mainPane.getChildren().clear();
			mainPane.getChildren().setAll(menu_bar);
			
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
		//loadCreate("create-ingredient.fxml");
		//loadEditIngredient("edit-ingredient.fxml");
		loadVisualize("list-ingredients.fxml");
	}
    
    @FXML
    public void loadProductOptions(ActionEvent event) {
    	loadTabPaneOptions(null);
		//loadCreateProduct();
		//loadEditProduct();
		loadVisualize("list-products.fxml");
    }
    
    @FXML
    public void loadOrderOptions(ActionEvent event) {
    	loadTabPaneOptions(null);
		//loadCreateProduct();
		//loadEditProduct();
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
		//loadCreateProduct();
		//loadEditProduct();
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
    void editClient(ActionEvent event) {

    }

    @FXML
    void updateClient(ActionEvent event) {

    }
	
}
