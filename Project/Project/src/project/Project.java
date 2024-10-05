/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
/**
 *
 * @author Walton
 */
public class Project extends Application {
    @Override
    public void start(Stage primaryStage) {
//        try {FXMLLoader loader = new FXMLLoader(getClass().getResource("signin.fxml"));
//        Parent root = loader.load();
//
//        Scene scene = new Scene(root);
//        primaryStage.setTitle("Signup Page");
//        primaryStage.setScene(scene);
//        primaryStage.setResizable(false); // জানালাটি আকার পরিবর্তন না করার জন্য
//        primaryStage.show(); // জানালাটি দেখান
//
//        // সাইন আপ পেজের জন্য controller কে Stage পাস করুন
//        SignupController signinController = loader.getController();
//        signinController.setPrimaryStage(primaryStage);
//        } catch (IOException e) {
//            e.printStackTrace(); 
//            System.out.println("Error loading the signup page."); 
             try {
//            // Load the signup page
            Parent root = FXMLLoader.load(getClass().getResource("Signup.fxml"));
            primaryStage.setTitle("Stopwatch App");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    


    public static void main(String[] args) {
        launch(args);
}
}