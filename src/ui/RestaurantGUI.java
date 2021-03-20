package ui;

import java.io.IOException;

import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import model.Ingredient;
import model.Restaurant;

public class RestaurantGUI {

	//other attributes
	private String actualUser;
	
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

       
    //    
    private Restaurant restaurant;
    
    //Constructor
    public RestaurantGUI(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	//Login methods
	@FXML
	void loadRegister(ActionEvent event) {
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
	void loginUser(ActionEvent event) {
			if(usernametxf.getText().equals("") || passwordtxf.getText().equals("")) {
				//alert
			}else {
				for (int i = 0; i < restaurant.getSystemUsers().size(); i++) {
					if(usernametxf.getText().equals(restaurant.getSystemUsers().get(i).getUserName()) &&
							passwordtxf.getText().equals(restaurant.getSystemUsers().get(i).getPassword())) {
						actualUser = usernametxf.getText();
						loadMenu();
					}else {
						//alert
					}
				}
			}
		}
	
	
	//Register methods
	@FXML
    void createSystemUser(ActionEvent event) {
		boolean valid = validateRegisterInput(rNametxf.getText(), rLastNametfx.getText(), rCctfx.getText(),
				rUsernametxf.getText(), rPasswordtxf.getText());
		
		if(valid) {
			restaurant.createEmployee(rNametxf.getText(), rLastNametfx.getText(), rCctfx.getText());
			restaurant.createSystemUser(rNametxf.getText(), rLastNametfx.getText(), rCctfx.getText(),
					rUsernametxf.getText(), rPasswordtxf.getText());
			loadLogin(null);
		}else {
			//alert
		}
    }

    @FXML
    void loadLogin(ActionEvent event) {
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
    
    void loadMenu() {
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
    void loadAdminOrders(ActionEvent event) {
    	
    }

    @FXML
    void loadAdminProducts(ActionEvent event) {
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
    void loadAdminUser(ActionEvent event) {
    	
    }

    @FXML
    void loadGenerateReports(ActionEvent event) {

    }

    @FXML
    void loadImports(ActionEvent event) {

    }
    
    @FXML
    void loadAdminIngredients(ActionEvent event) {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminIngredients-pane.fxml"));
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
    void createIngredient(ActionEvent event) {
    	
    	if(!txtIngredientName.getText().equals("") && !txtIngredientValue.getText().equals("")){
    		restaurant.createIngredient(txtIngredientName.getText(), actualUser, actualUser, txtIngredientValue.getText());
    		System.out.println("YEAH");
    	}
    }
    
    
}
