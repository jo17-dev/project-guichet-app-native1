/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.quickcash;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AdminLoginController implements Initializable {
    @FXML private TextField codeClient;
    @FXML private TextField nipEntry;
    @FXML private Button btnValider;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void login(){
        if("1000".equals(codeClient.getText()) && "admin".equals(nipEntry.getText())){
            System.out.println("adminitrateur loggé");
            
            try {
                
                
                App.setRoot("adminListeClients", btnValider.getScene());
            } catch (IOException ex) {
                // TODO faire un popup qui affiche que le systeme vas s'arreter
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur interne");
                alert.setContentText("Nous notons une erreur interne. Revenez plus tard !");
                
                
                
                alert.showAndWait();
                System.out.println("IOException. arret du systeme");
                System.out.println(ex.getMessage());
                System.exit(1);
            }
            
        }else{
            System.out.println("idcs non valides");
        }
    }
    
}
