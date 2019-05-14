package Controller;

import Model.Fibonacci;
import Model.fibonacci_impl;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;
import Controller.WinPopUp;
import Controller.LosePopUp;
import org.pmw.tinylog.Logger;


public class Controller {
	Fibonacci Game = new fibonacci_impl();
	StringProperty feliratok[][] = new SimpleStringProperty[4][4]; 
	boolean alreadywon=false;
	WinPopUp az = new WinPopUp();;
	
	//@FXML
	public Label L00,L01,L02,L03,L10,L11,L12,L13,L20,L21,L22,L23,L30,L31,L32,L33;

	void bindLabel() {
		L00.textProperty().bind(feliratok[0][0]);
		L01.textProperty().bind(feliratok[0][1]);
		L02.textProperty().bind(feliratok[0][2]);
		L03.textProperty().bind(feliratok[0][3]);
		L10.textProperty().bind(feliratok[1][0]);
		L11.textProperty().bind(feliratok[1][1]);
		L12.textProperty().bind(feliratok[1][2]);
		L13.textProperty().bind(feliratok[1][3]);
		L20.textProperty().bind(feliratok[2][0]);
		L21.textProperty().bind(feliratok[2][1]);
		L22.textProperty().bind(feliratok[2][2]);
		L23.textProperty().bind(feliratok[2][3]);
		L30.textProperty().bind(feliratok[3][0]);
		L31.textProperty().bind(feliratok[3][1]);
		L32.textProperty().bind(feliratok[3][2]);
		L33.textProperty().bind(feliratok[3][3]);
	}
	
	void refreshlabels() {
		for(int i =0; i<4; i++)
			for(int j=0; j<4; j++) {
				feliratok[i][j]= new SimpleStringProperty(String.valueOf(fibonacci(Game.getCell(i, j)))) ;
			}
	}
	
	void colorCells() {
		
		L00.setStyle("-fx-background-color: " + (getColor(Game.getCell(0,0))) + ";" +"-fx-text-fill: #fff;");
		L01.setStyle("-fx-background-color: " + (getColor(Game.getCell(0,1))) + ";"+"-fx-text-fill: #fff;");
		L02.setStyle("-fx-background-color: " + (getColor(Game.getCell(0,2))) + ";"+"-fx-text-fill: #fff;");
		L03.setStyle("-fx-background-color: " + (getColor(Game.getCell(0,3))) + ";"+"-fx-text-fill: #fff;");
		L10.setStyle("-fx-background-color: " + (getColor(Game.getCell(1,0))) + ";"+"-fx-text-fill: #fff;");
		L11.setStyle("-fx-background-color: " + (getColor(Game.getCell(1,1))) + ";"+"-fx-text-fill: #fff;");
		L12.setStyle("-fx-background-color: " + (getColor(Game.getCell(1,2))) + ";"+"-fx-text-fill: #fff;");
		L13.setStyle("-fx-background-color: " + (getColor(Game.getCell(1,3))) + ";"+"-fx-text-fill: #fff;");
		L20.setStyle("-fx-background-color: " + (getColor(Game.getCell(2,0))) + ";"+"-fx-text-fill: #fff;");
		L21.setStyle("-fx-background-color: " + (getColor(Game.getCell(2,1))) + ";"+"-fx-text-fill: #fff;");
		L22.setStyle("-fx-background-color: " + (getColor(Game.getCell(2,2))) + ";"+"-fx-text-fill: #fff;");
		L23.setStyle("-fx-background-color: " + (getColor(Game.getCell(2,3))) + ";"+"-fx-text-fill: #fff;");
		L30.setStyle("-fx-background-color: " + (getColor(Game.getCell(3,0))) + ";"+"-fx-text-fill: #fff;");
		L31.setStyle("-fx-background-color: " + (getColor(Game.getCell(3,1))) + ";"+"-fx-text-fill: #fff;");
		L32.setStyle("-fx-background-color: " + (getColor(Game.getCell(3,2))) + ";"+"-fx-text-fill: #fff;");
		L33.setStyle("-fx-background-color: " + (getColor(Game.getCell(3,3))) + ";"+"-fx-text-fill: #fff;");
		
		
		

	}
	
	String getColor(int ertek) {
		String color = "white";
		switch (ertek) {
			case 1:
				color= "#00A";
				break;
			case 2:
				color= "#050";
				break;
			case 3:
				color= "#500";
				break;
			case 4:
				color= "#505";
				break;
			case 5:
				color= "#550";
				break;
			case 6:
				color= "#055";
				break;
			case 7:
				color= "#00A";
				break;
			case 8:
				color= "#0A0";
				break;
			case 9:
				color= "#A00";
				break;
			case 10:
				color= "#A0A";
				break;
			case 11:
				color= "#AA0";
				break;
			case 13:
				color= "#0AA";
				break;
		}
		return color;
	}
	
	private int fibonacci(int n)  {
	    if(n == 0)
	        return 0;
	    else if(n == 1)
	      return 1;
	   else if(n==2)
		   return 2;
	   else
	      return fibonacci(n - 1) + fibonacci(n - 2);
	}
	
	private void checkgamestate() {
		if(Game.getwin() && !alreadywon) {
			alreadywon=true;
			az.init();
			if(az.display()) {
				Game.newGame();
				refreshlabels();
				bindLabel();
				colorCells(); 
				alreadywon=false;
			}
		}
		if(Game.getLose()) {
			LosePopUp ez = new LosePopUp();
			ez.display();
			Game.newGame();
			refreshlabels();
			bindLabel();
			colorCells(); 
			alreadywon=false;
		}
	}
	
	
	
	public void Newgame() {
		Logger.info("Starting new game");
		Game.newGame();
		refreshlabels();
		bindLabel();
		colorCells(); 
		alreadywon=false;
	}
	public void UpMove() {
		Logger.info("Executing Up move");
		Game.upMove();
		refreshlabels();
		bindLabel();
		colorCells(); 
		checkgamestate();

	}
	public void LeftMove() {
		Logger.info("Executing Left move");
		Game.leftMove();
		refreshlabels();
		bindLabel();
		colorCells(); 
		checkgamestate();

	}
	public void RightMove() {
		Logger.info("Executing Right move");
		Game.rightMove();
		refreshlabels();
		bindLabel();
		colorCells(); 
		checkgamestate();

	}
	public void DownMove() {
		Logger.info("Executing Down move");
		Game.downMove();
		refreshlabels();
		bindLabel();
		colorCells(); 
		checkgamestate();

	}
}
