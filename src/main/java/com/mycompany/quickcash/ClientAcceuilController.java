/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.quickcash;

import backend.Cheque;
import backend.Compte;
import static com.mycompany.quickcash.App.loadFXML;
import static com.mycompany.quickcash.App.stageClientHistoriqueTransactions;
import guicomponent.ElementListe;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author samsl
 */
public class ClientAcceuilController extends BasicControls implements Initializable {
    public static Compte compteSelected;
    @FXML VBox itemContainer;
    @FXML Label textUser;
    private ArrayList<Compte> listeComptes;
    @FXML Button btnTransfert;
    @FXML Button btnPayer;
    @FXML Button btnDepot;
    @FXML Button btnRetrait;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listeComptes = App.gestionnaire.getComptesParClient(App.loggedUser.getCodeClient());
        textUser.setText(App.loggedUser.getNom());
        
        for(Compte item : listeComptes) {
            itemContainer.getChildren().add(
                new ElementListe(item) {
                    @Override
                    protected void setAction1() {
                        compteSelected = item;
                        afficherTransactions();
                    }
                    @Override
                    protected void setAction2() {
                        
                    }
                    @Override
                    protected void setAction3() {
        
                    }
                }
            );
        }
    }
    
}
