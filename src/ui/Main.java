package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Restaurant;

public class Main extends Application{

	private Restaurant restaurant;
	private RestaurantGUI restaurantGUI;
	
	public Main() {
		restaurant = new Restaurant();
		restaurantGUI = new RestaurantGUI(restaurant);
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-pane.fxml"));
		
		fxmlLoader.setController(restaurantGUI);
		Parent root = fxmlLoader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Casa Dorada");
		primaryStage.show();
		restaurantGUI.loadLogin(null);
		restaurant.loadData();
	}

}
