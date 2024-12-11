/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.quickcash;

import backend.Client;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AdminCreerCompteController implements Initializable {
    @FXML private TextField emailEntry;
    @FXML private ComboBox typeCompteEntry;
    @FXML private Label generatedCompteCode;
    @FXML private Label errorText;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        generatedCompteCode.setText(String.valueOf(backend.Compte.getNbreComptes()));
        System.out.println("Page de creation de compte montée");
       
        typeCompteEntry.getItems().addAll("Cheque", "Epargne", "Hypothecaire", "Marge");
        
    }
    
    @FXML
    public void annuler(){
        System.out.println("Annulation des choses");
        App.toggleStage("adminCreerCompte");
    }    
    
    /**
     * Creer un compte à un utilisateur
     * 1er etape: a validation des infos
     * 2e etape: recherche d'un client par son email( jai pensé que ce serait mieux que son id ;)
     * 3e etape: creation proprement dite via gestionnaire
     * pup-up creation reussie
     * fermer la fenêtre.
     * 
     * 
     * */
    @FXML
    public void valider(){
        System.out.println("Creation d'un nouveau compte pour un client");
        boolean isError = false;
        String courriel = emailEntry.getText();
        Client clientCible = App.gestionnaire.chercherClientParEmail(courriel);
        
        if(courriel.length() == 0){
            errorText.setText("- Tous les champs sont obligatoires");
            isError = true;
        }
        
        if(clientCible == null){
            errorText.setText("\n- Aucun utilisateur avec cet email n'existe");
            isError = true;
        }
        
        if(typeCompteEntry.getValue() == null){
            errorText.setText("\n- Vous devez selectionner un type de Compte");
            isError = true;
        }
        

        if(isError){
            return;
        }
        
        // normalement il n'y pas d'erreur à partir d'ici
        System.out.println("pas d'erreur... tout est correct");
        App.gestionnaire.creerCompte(backend.Compte.getNbreComptes(),clientCible.getCodeClient() , 0, 1000, 1000, (typeCompteEntry.getValue().toString().toLowerCase()));
        
        //on reinitialise tous les champs
        generatedCompteCode.setText(String.valueOf(backend.Compte.getNbreComptes()));
        errorText.setText("");
        emailEntry.setText("");
        
        // On fait le message de confirmation et on sort de la fenêtre
        
        App.toggleStage("adminCreerCompte");
        BasicControls.popUp("Opération reussie", "La creation du compte pour le client est faite", Alert.AlertType.CONFIRMATION);
        
        
    }
    
    @FXML
    public void test(){
        System.out.println("Bouton cliquer");
    }
    
}
