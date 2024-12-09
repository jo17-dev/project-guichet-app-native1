/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickcash;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author jo17-dev
 * Cette classe contient les controls de bases ( logout, changement de page(il vas catch l'expeception ici est afficher les messages d'erreurs)) déclenchés pas l'user via le click et tout..
 */
public class BasicControls {
    @FXML AnchorPane mainContainer;
    public BasicControls(){
        
    }
    
    
    // actions
    
    // clic sur le bouton ajouterClient ( ce n'est pas le bouton du form ici..)
    @FXML
    public void ajouterClient(){
        System.out.println("Redirection vers le form d'ajout d'un client");
        App.toggleStage("adminAjouterClient");
    }
    
        @FXML
    public void creerCompte(){
        System.out.println("Ouverture de la page de creation de compte bancaire");
        App.toggleStage("adminCreerCompte");
    }
    
    
    @FXML
    public void listeClient(){
        System.out.println("Redirection vers lite Client");
    }
    
    @FXML
    public void afficher_liste_clients(){
        try{
            App.setRoot("adminListeClients", mainContainer.getScene());
        }catch(IOException ioe){
            // TODO faire un popup qui affiche que le systeme vas s'arreter
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur interne");
            alert.setContentText("Nous notons une erreur interne. Revenez plus tard !");


            alert.showAndWait();
            System.out.println("IOException. arret du systeme");
            System.out.println(ioe.getMessage());
            System.exit(1);
        }
    }
    
    @FXML
    public void remplirGuichet(){
        System.out.println("___ remplissage de guichet (VUE)");
    }
    
    @FXML
    public void preleverInteret(){
        System.out.println("___ preleverment interetà (VUE)");
    }
        
    @FXML
    public void logout(){
        System.out.println("_____deconnexion_____");
        try{
            App.loggedUser = null;
            App.setRoot("adminLogin", mainContainer.getScene());
        }catch(IOException ioe){
            // TODO faire un popup qui affiche que le systeme vas s'arreter
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur interne");
            alert.setContentText("Nous notons une erreur interne. Revenez plus tard !");


            alert.showAndWait();
            System.out.println("IOException. arret du systeme");
            System.out.println(ioe.getMessage());
            System.exit(1);
        }
    }
    
}
