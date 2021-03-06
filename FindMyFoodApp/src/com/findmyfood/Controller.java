package com.findmyfood;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class Controller {
	
	@FXML 
	private ListView<String> listView; 
	
	@FXML
	private TextField locationField; 
	
	@FXML
	private ChoiceBox<String> typeBox; 
	
	@FXML
	private ChoiceBox<String> cuisineBox; 
	
	@FXML
	private Slider rating; 
	
	@FXML
	private ChoiceBox<String> dishBox; 
	
	@FXML 
	private Button search;
	
	@FXML
	private ComboBox<String> sortBox; 
	
	public Controller()  {
		
	}
	
	public void initialize() { 
		//locationBox.setItems(FXCollections.observableArrayList(MainApp.findDistinctLocations()));
		typeBox.setItems(FXCollections.observableArrayList(MainApp.findDistinctRestaurantTypes())); 
		typeBox.setValue("Any");
		cuisineBox.setItems(FXCollections.observableArrayList(MainApp.findDistinctCuisines())); 
		cuisineBox.setValue("Any");
		dishBox.setItems(FXCollections.observableArrayList(MainApp.findDistinctDishTypes()));
		dishBox.setValue("Any");
		sortBox.setItems(FXCollections.observableArrayList("None", "Price Category", "Rating"));
		sortBox.setValue("None"); 
	}
	
	public String getLocation() { 
		return locationField.getText().toLowerCase(); 
	}
	
	public String getRestaurantType() {
		return typeBox.getValue(); 
	}
	
	public String getCuisine() { 
		return cuisineBox.getValue(); 
	}
	
	public double getRating() { 
		return rating.getValue(); 
	}
	
	public String getDishType() {
		return dishBox.getValue(); 
	}
	
	public String getSortBy() { 
		return sortBox.getValue(); 
	}
	@FXML
	private void handleSearch(ActionEvent event) {
		listView.getItems().clear(); 
		ArrayList<String> results = MainApp.submit(getLocation(), getRestaurantType(), getCuisine(), getRating()
				,getDishType(), getSortBy()); 
		listView.setItems(FXCollections.observableArrayList(results));
		//outputArea.appendText(getLocation());
	
	}
	
	
}
