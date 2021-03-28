package ui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import model.Order;
import model.Product;
import model.Restaurant;
import model.Size;

public class RestaurantGUI {

	//other attributes
	private String actualUser;						//A reference to the system user that is currently logged in
	private String referenceIngredient;				//A reference to an ingredient selected in the ingredients table, that allows the program to recognize which ingredient has to be edited 
	private ArrayList<Integer> tempIngrsCodes;		//Temporal list with the indexes of the ingredients that will be added to the product
	private Product referenceProduct;
	private ArrayList<Integer> tempProductCodes;
	private ArrayList<Integer> tempProductsAmounts;
	private ArrayList<String> tempProductsSizes;
	
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
    
    //edit products
    @FXML
    private JFXTextField txtNewProductName;

    @FXML
    private JFXTextField txtNewProductPrice;

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
    private JFXTextField txtOrderClientName;

    @FXML
    private JFXTextField txtOrderClientLastName;

    @FXML
    private JFXTextField txtOrderClientCC;

    @FXML
    private JFXTextField txtOrderClientAdress;

    @FXML
    private JFXTextField txtOrderClientPhone;

    @FXML
    private JFXTextField txtOrderClientObsField;

    @FXML
    private JFXTextField txtOrderProductName;

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
				restaurant.createProduct(txtPName.getText(), actualUser, actualUser, tempIngrsCodes, type);
				tempIngrsCodes.clear();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		}
    }
    
    @FXML
    void addIngredientToProduct(ActionEvent event) {
    	Ingredient ingrX = tvIngP.getSelectionModel().getSelectedItem();
    
    	if(tempIngrsCodes.size()>0) {
    		boolean canAdd = searchTempIngr(ingrX.getCode());
	    if(canAdd) {
	    		tempIngrsCodes.add(ingrX.getCode());

	    	}
    	}else {
    		tempIngrsCodes.add(ingrX.getCode());

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
	 void loadEditProduct(ActionEvent event) {
		 //add a colleciton 
		 
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
			txtNewProductPrice.setPromptText(""+referenceProduct.getPrice());
			
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
	 void addIngrToProduct(ActionEvent event) {
		
		 Ingredient ingrToAdd = tvallInings.getSelectionModel().getSelectedItem();
		 
		 if(ingrToAdd!=null) {
			 
			 tempIngrsCodes.add(ingrToAdd.getCode());
			 referenceProduct.getIngredients().add(ingrToAdd);
			 loadEditProduct(null);
			 
		 }
		 
		 if(!actionIngtxt.getText().isEmpty() || !actionIngtxt.getText().equals(actionIngtxt.getPromptText())) {
			 
			 boolean exists = restaurant.searchIngredient(actionIngtxt.getText());
			    
			 if(exists) {
				
				 int posToAdd = restaurant.binarySearchIng(actionIngtxt.getText(), restaurant.getIngredients());
				 referenceProduct.getIngredients().add(restaurant.getIngredients().get(posToAdd));
				 loadEditProduct(null);	
			 }
		 }
		 
    }

    @FXML
    void removeIngrFromProduct(ActionEvent event) {
    	 System.out.println(tempIngrsCodes.size());
    	
    	Ingredient ingrToRemove = tvIdp.getSelectionModel().getSelectedItem();
    	
		if(ingrToRemove!=null) {
			
			int posToRem =searhIngInTempProduct(ingrToRemove.getCode());
			if(posToRem!=-1){
				referenceProduct.getIngredients().remove(posToRem);
				loadEditProduct(null);
			}			
		}
		
		if(!actionIngtxt.getText().equals(actionIngtxt.getPromptText()) || !actionIngtxt.getText().isEmpty()) {
			
		
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
	    	System.out.println("DAMINT"+referenceProduct.getIngredients().size());
			for (int i = 0; i < referenceProduct.getIngredients().size() && !found; i++) {
				System.out.println("CODIGO DEL COSOOOOOOOOAAAAAAA"+referenceProduct.getIngredients().get(i).getCode());
				if(code == referenceProduct.getIngredients().get(i).getCode()){
					pos = i;
					found = true;
				}
			}
			return pos;
	    }
   
	 @FXML
	 void addSizeToProduct(ActionEvent event) {
		 if(!txtAddNameSize.getText().isEmpty() && !txtMultiply.getText().isEmpty()) {
			 referenceProduct.addSize(txtAddNameSize.getText(), Double.parseDouble(txtMultiply.getText()));
			 loadEditProduct(null);
		 }
	 }
	 
	 @FXML
	 void removeSizeFromProduct(ActionEvent event) {
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
    void updateProduct(ActionEvent event) {
    	
    	if(txtNewProductName.getText().isEmpty()) {
    		txtNewProductName.setText(referenceProduct.getName());
    	}
    	if(txtNewProductName.getText().isEmpty()) {
    		txtNewProductPrice.setText(""+referenceProduct.getPrice());
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
    	System.out.println("INGS SIZE: "+ tempIngrsCodes.size());
    	
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
    void loadAdminOrders(ActionEvent event) {
    	
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
    void addProductToOrder(ActionEvent event) {
    	
    	Product productX = tvProductsList.getSelectionModel().getSelectedItem();
    	
    	if(productX!=null) {
    		tempProductCodes.add(productX.getCode());
    		
    		if(txtOrderProductAmount.getText().equalsIgnoreCase(txtOrderProductAmount.getPromptText()) || txtOrderProductAmount.getText().equals("")) {
    			tempProductsAmounts.add(1);
    		}else {
    			tempProductsAmounts.add(Integer.parseInt(txtOrderProductAmount.getText()));
    		}
    		if(txtOrderProductSize.getText().equalsIgnoreCase(txtOrderProductSize.getPromptText()) || txtOrderProductSize.getText().equals("")) {
    			tempProductsSizes.add("Standard");
    		}else {
    			Size sizeX = productX.getSizeByName(txtOrderProductSize.getText());
    			if(sizeX!=null) {
    				tempProductsSizes.add(txtOrderProductSize.getText());
    			}
    			
    		}
    	}
    	
    	if(!txtOrderProductName.getText().equalsIgnoreCase(txtOrderProductName.getPromptText()) && !txtOrderProductName.getText().equals("")) {
    	
    		Product tempProduct = restaurant.getProductByCode(Integer.parseInt(	txtOrderProductName.getText()));
    		if(tempProduct!=null) {
    			tempProductCodes.add(tempProduct.getCode());
    		}
    	}
    }

    @FXML
    void createOrder(ActionEvent event) {
    	if(!txtOrderClientName.getText().isEmpty() && !txtOrderClientLastName.getText().isEmpty() && !txtOrderClientCC.getText().isEmpty() && 
    			!txtOrderClientAdress.getText().isEmpty() && !txtOrderClientPhone.getText().isEmpty() && tempProductCodes.size()>0) {
    		
    		try {
				restaurant.createClient(txtOrderClientName.getText(), txtOrderClientLastName.getText(), txtOrderClientCC.getText(), txtOrderClientAdress.getText(), txtOrderClientPhone.getText(), txtOrderClientObsField.getText());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		LocalDate orderTime = LocalDate.now();
    		
    		int id = restaurant.searchEmployeeByUserName(actualUser);
    		String state ="REQUESTED";
    		
    		try {
				restaurant.createOrder(tempProductCodes, tempProductsAmounts, tempProductsSizes, txtOrderClientName.getText(), id, orderTime, txtOrderOBs.getText(), state);
				System.out.println("AAAAAA");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
    	}
    }

    @FXML
    void loadEditOrder(ActionEvent event) {

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
		 
    	colOrdersClients.setCellValueFactory(new PropertyValueFactory<Order,String>("employeeRef"));  		 
		 
    	colOrdersCodes.setCellValueFactory(new PropertyValueFactory<Order,String>("code"));
		 
    	colOrdersStates.setCellValueFactory(new PropertyValueFactory<Order,String>("state"));

    	colOrdersPrices.setCellValueFactory(new PropertyValueFactory<Order,String>("totalPrice"));    		
		 
    	tvOrders.setItems(allOrders);
    }
}
