/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * FXML Controller class
 *
 * @author Walton
 */
public class SignupController {

    @FXML
    private Label gmailLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private TextField gmailField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button signupButton;
    private UserModel usermodel;
    private Stage primaryStage; // Declare primaryStage
        
    @FXML
    private void handleSignup(ActionEvent event) {
        System.out.println("Signup button clicked."); 
        String email = gmailField.getText();
        String name = nameField.getText();
        String password = passwordField.getText();
        
        usermodel = new UserModel(email, name, password);
        System.out.println("Signup Successful!");
        System.out.println("Gmail: " + email);
        System.out.println("Name: " + name);
        System.out.println("Password: " + password);
        
       // Navigate to login page
        navigateToLoginPage();
    }

    private void navigateToLoginPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();

            // Pass userModel to LoginController
            LoginController loginController = loader.getController();
            loginController.setUserModel(usermodel);

            Stage stage = (Stage) signupButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
     catch (IOException e) {
        e.printStackTrace();
        System.out.println("Error loading the login page.");
    }
    }
      public void setPrimaryStage(Stage stage) {
                this.primaryStage = stage;
    }    
}
