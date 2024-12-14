/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.quickcash;

import backend.Cheque;
import backend.Compte;
import backend.Epargne;
import backend.PreleverMontantException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author samsl
 */
public class ClientRetraitArgentController implements Initializable {
    @FXML private ComboBox comptesEntry;
    @FXML private TextField montantEntry;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("initialisation");
        for(Compte item : App.gestionnaire.getComptesParClient(App.loggedUser.getCodeClient())){
            // les retrait ne sont dispo que dans les comptes cheques et epagnes
            if(item instanceof Epargne ){
                comptesEntry.getItems().add("Compte Epargne "+item.getNumeroCompte());
            }else if(item instanceof Cheque){
                comptesEntry.getItems().add("Compte Cheque "+item.getNumeroCompte());
            }
        }
    }    
    
    
    @FXML
    public void valider(){
        System.out.println("Validation de la transaction de retrait");
        String numroCompteCible = null;
        boolean isError = false;
        
        try{
            numroCompteCible = comptesEntry.getValue().toString().split(" ")[2];
        }catch(NullPointerException pre){
            isError = true;
        }
        
        if(numroCompteCible == null){
            isError = true;
            BasicControls.popUp("Echec de l'operation", "Vous devez choisir un compte", Alert.AlertType.ERROR);
        }
        
        System.out.println("numero de compte recupérer " + numroCompteCible);
        
        String montant = montantEntry.getText();
        
        
        
        if(montant.length() == 0){
            isError = true;
            BasicControls.popUp("Echec de l'operation", "Le montant doit etre requis", Alert.AlertType.ERROR);
        }
        
        try{
            Double.valueOf(montant);
        }catch(NumberFormatException e){
            isError = true;
            BasicControls.popUp("Echec de l'operation", "Le montant doit etre un nombre", Alert.AlertType.ERROR);
        }
        
        if(Integer.parseInt(montant) % 10 != 0){
            isError = true;
            BasicControls.popUp("Echec de l'operation", "Le montant doit etre un multiple de 10", Alert.AlertType.ERROR);
        }
        
        
        if(isError){
            System.out.println("Erreurrrrrrrrrr");
            return;
        }
        System.out.println("---------- tout est correct");
        
        if(comptesEntry.getValue().toString().toLowerCase().contains("epargne")){
            try {
                App.gestionnaire.retraitEpargne(App.loggedUser.getCodeClient(), Integer.parseInt(numroCompteCible) ,Double.parseDouble(montant));
                System.out.println("retrait du compte ");
            } catch (PreleverMontantException ex) {
                BasicControls.popUp("statut de l'operation", ex.getMessage(), Alert.AlertType.INFORMATION);
            }
        }else{
            System.out.println("compte epargne");
            try {
                App.gestionnaire.retraitCheque(App.loggedUser.getCodeClient(), Double.parseDouble(montant));
            } catch (PreleverMontantException ex) {
                BasicControls.popUp("statut de l'operation", ex.getMessage(), Alert.AlertType.INFORMATION);
            }
        }
        
        BasicControls.popUp("statut de l'operation", "l'operation a reussie", Alert.AlertType.INFORMATION);
        
    }
    
}
