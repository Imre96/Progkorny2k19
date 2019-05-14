package controller;

import javafx.event.ActionEvent;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.control.*;
import org.pmw.tinylog.Logger;

public class LosePopUp {
	Stage window = new Stage();
	public void init() {
		try {
			Stage window = new Stage();
			window.initModality(Modality.APPLICATION_MODAL);
	        Parent root = FXMLLoader.load(getClass().getResource("LoseScene.fxml"));
	        window.setScene(new Scene(root));
			}catch(Exception e){
		        Logger.warn(e.getMessage());
		        Logger.warn(e.toString());
		        Logger.warn(e.getCause());
			}

	}
	public void display(){
        window.showAndWait();
	}
	
	public void OKButton(ActionEvent e) {
	    ((Stage)(((Button)e.getSource()).getScene().getWindow())).close();
	}

}
