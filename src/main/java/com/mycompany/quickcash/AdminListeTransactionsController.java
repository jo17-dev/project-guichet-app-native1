/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.quickcash;

import backend.Client;
import backend.Compte;
import backend.Transaction;
import guicomponent.ElementListe;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author jo17-dev
 * 
 * Ceci est la classe permettant de gérer la page des transactions d'un compte par l'admin
 */
public class AdminListeTransactionsController extends BasicControls implements Initializable {
    public static Compte compteTarget;
    /**
     * Initializes the controller class.
     */
    @FXML public VBox itemContainer;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Page de la liste des transaction - admin initialisée");
        if(compteTarget != null){
            // TODO récupérer les transaction du client dans tous ces comptes
            // Pour le moment, on vas juste creer 4 transaction
             ArrayList<Transaction> transactions = App.gestionnaire.getTransactionsParComptes(compteTarget.getNumeroCompte());
             

//            if(transactions.size() == 0){
//                itemContainer.getChildren().add(
//                        
//                );
//            }
            
            
            for(Transaction item : transactions){
                itemContainer.getChildren().add(
                    new ElementListe(item) {
                        @Override
                        protected void setAction1() {
                            System.out.println("action 3 - transaction");
                        }

                        @Override
                        protected void setAction2(){
                            System.out.println("action 2 - transaction");
                        }

                        @Override
                        protected void setAction3() {
                            System.out.println("action 3 - transaction");
                        }
                }
                );
            }
        }
    }    
    
}
