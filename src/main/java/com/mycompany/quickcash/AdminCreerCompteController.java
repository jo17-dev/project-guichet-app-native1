/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.quickcash;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AdminCreerCompteController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Page de creation de compte montée");
    }
    
    @FXML
    public void annuler(){
        System.out.println("Annulation des choses");
        App.toggleStage("adminCreerCompte");
    }    
    
    
    public void valider(){
        System.out.println("Creation d'un nouveau compte pour un client");
    }
    
}
