package ui;

import java.io.IOException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.sun.corba.se.spi.orbutil.fsm.Action;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Ingredient;
import model.Product;
import model.Restaurant;

public class RestaurantGUI {

	//other attributes
	private String actualUser;						//A reference to the system user that is currently logged in
	private String referenceIngredient;				//A reference to an ingredient selected in the ingredients table, that allows the program to recognize which ingredient has to be edited 
	private ArrayList<Integer> tempIngrsIndex;		//Temporal list with the indexes of the ingredients that will be added
	private String referenceProduct;
	
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
    
    //    
    private Restaurant restaurant;
    
    //Constructor
    public RestaurantGUI(Restaurant restaurant) {
		this.restaurant = restaurant;
		tempIngrsIndex = new ArrayList<>();
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
						loadMenu(null);
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
			try {
				restaurant.createEmployee(rNametxf.getText(), rLastNametfx.getText(), rCctfx.getText());
				restaurant.createSystemUser(rNametxf.getText(), rLastNametfx.getText(), rCctfx.getText(),
						rUsernametxf.getText(), rPasswordtxf.getText());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
    
    @FXML
    void loadMenu(ActionEvent event) {
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
    void reLoadAdminProducts(ActionEvent event) {
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
    void createIngredient(ActionEvent event) {
    	
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
    void LoadEditI(ActionEvent event) {
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
    }
    
    @FXML
    void updateIngredient(ActionEvent event) {
    	if(!txtNewIngName.equals("") && !txtNewIngVal.equals("")) {
    		try {
				restaurant.updateIngredient(referenceIngredient, txtNewIngName.getText(), txtNewIngVal.getText());
			} catch (IOException e) {
				// TODO Auto-generated catch block
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
    void loadAProducts(ActionEvent event) {
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
    void createProduct(ActionEvent event) {
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
				restaurant.createProduct(txtPName.getText(), actualUser, actualUser, tempIngrsIndex, type);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		}
    }
    
    @FXML
    void addIngredientToProduct(ActionEvent event) {
    	Ingredient ingrX = tvIngP.getSelectionModel().getSelectedItem();
    
    	if(tempIngrsIndex.size()>0) {
    		boolean canAdd = searchTempIngr(ingrX.getCode());
	    if(canAdd) {
	    		tempIngrsIndex.add(ingrX.getCode());

	    	}
    	}else {
    		tempIngrsIndex.add(ingrX.getCode());

    	}
	    		
    }
    
    public boolean searchTempIngr(int codeI) {
    	boolean found=false;
    	for (int i = 0; i < tempIngrsIndex.size() && !found; i++) {
			if(tempIngrsIndex.get(i)==codeI) {
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
	 void loadEditProduct(ActionEvent event) {
		 Product productX = tvProducts.getSelectionModel().getSelectedItem();
		 
	 }
    
}
