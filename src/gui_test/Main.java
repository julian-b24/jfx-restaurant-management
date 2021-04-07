package gui_test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Restaurant;

public class Main extends Application{

	private Restaurant restaurant;
	private Controller controller;
	
	public Main() {
		restaurant = new Restaurant();
		controller = new Controller(restaurant);
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-pane.fxml"));
		
		fxmlLoader.setController(controller);
		Parent root = fxmlLoader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Prueba");
		primaryStage.show();
		controller.loadLogIn(null);
		restaurant.loadData();
	}

}
