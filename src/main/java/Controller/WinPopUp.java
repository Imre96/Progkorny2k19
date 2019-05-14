package Controller;

import javafx.event.ActionEvent;
import java.awt.event.InputEvent;
import org.pmw.tinylog.Logger;


import org.pmw.tinylog.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.control.*;;

public class WinPopUp {
	
	static boolean New;
	static Stage window = new Stage();
	Button GOContiue;
	
	public void init() {
		try {
			window.initModality(Modality.APPLICATION_MODAL);
	        Parent root = FXMLLoader.load(getClass().getResource("winscene.fxml"));
	        window.setScene(new Scene(root));
			}catch(Exception e){
				Logger.warn(e.getMessage());
				Logger.warn(e.toString());
				Logger.warn(e.getCause());
			}

	}
	public boolean display(){
        window.showAndWait();
	
		return New;
	}
	
	public void winC(ActionEvent e) {
		New=false;
	    ((Stage)(((Button)e.getSource()).getScene().getWindow())).close();
	    
	}
	
	public void winN(ActionEvent e) {
		New = true;
	    ((Stage)(((Button)e.getSource()).getScene().getWindow())).close();
	}
	
}
