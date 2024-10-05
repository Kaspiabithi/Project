/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package project;

import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Walton
 */


public class LoginController { 

    @FXML
    private Label nameLabel;
    @FXML
    private TextField nameField;
    @FXML
    private Label passwordLabel;
    @FXML
    private TextField passwordField;
    @FXML
    private Button loginButton;
    
        private UserModel usermodel;
        public void setUserModel(UserModel userModel) {
        this.usermodel = userModel;
        nameField.setText(userModel.getName()); // Auto-fill the name field
    }

    private void navigateToStopwatchPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Stopwatch.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        }                
            catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error loading the stopwatch page.");
            }
            }

    @FXML
    private void handleLogin(ActionEvent event) {
        String name = nameField.getText();
        String password = passwordField.getText();
        
        if (name.equals(usermodel.getName()) && password.equals(usermodel.getPassword())) {
            System.out.println("Login Successful!");
            navigateToStopwatchPage(); // Navigate to Stopwatch page
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
        System.out.println("Name: " + name);
        System.out.println("Password: " + password);
    }
           }
