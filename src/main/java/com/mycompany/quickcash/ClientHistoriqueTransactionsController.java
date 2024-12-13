/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickcash;

import backend.Compte;
import backend.Epargne;
import backend.Hypothecaire;
import backend.Marge;
import backend.Transaction;
import guicomponent.ElementListe;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

/**
 *
 * @author samsl
 */
public class ClientHistoriqueTransactionsController extends BasicControls implements Initializable {
    @FXML Label compteAffiche;
    @FXML VBox itemContainer;
    private String compteText;
    Compte compte = ClientAcceuilController.compteSelected;
    ArrayList<Transaction> transactions = App.gestionnaire.getTransactionsParComptes(compte.getNumeroCompte());
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        compteText = "Cheque";
        if(compte instanceof Epargne){
            compteText = "Epargne";
        }else if(compte instanceof Hypothecaire){
            compteText = "Hypothecaire";
        }else if(compte instanceof Marge){
            compteText = "Marge";
        }
        if(compte != null) {
            compteText += "("+compte.getNumeroCompte()+")";
            compteAffiche.setText("Compte : "+compteText);
        }
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
