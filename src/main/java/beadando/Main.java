package beadando;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.pmw.tinylog.Logger;

/**
 * Main Class, extends Application
 * @author Szabó Imre
 *
 */
public class Main extends Application {
	/**
	 * main method of the application
	 * @param args command line arguments
	 */	
	public static void main(String[] args)  {
				launch(args);
	}
		/**
		 * Sets up and launches primary stage.
		 */
	@Override
	public void start(Stage primaryStage) throws Exception {      
		Logger.info("Aplication started!");

        Parent root = FXMLLoader.load(getClass().getResource("Proba.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 450));
        primaryStage.show();
	}

}
