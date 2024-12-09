package com.mycompany.quickcash;

import backend.Cheque;
import backend.Client;
import backend.Compte;
import backend.Epargne;
import backend.Hypothecaire;
import guicomponent.ElementListe;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author jo17-dev
 */
public class AdminInformationsClientController extends BasicControls implements Initializable {

    @FXML VBox infoContainer;
    
    @FXML private Label champNom;
    @FXML private Label champPrenom;
    @FXML private Label champCourriel;
    @FXML private Label champTelephone;
    
    public static Client cible = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // step 1 récupérer le client cible et ses comptes:
        ArrayList<Compte> comptesClient; // designe l'ensemble des comptes aapartenant au client cible
        double retraitMaximum = 20000; // ceci est la quantitée d'argent qu'il y a en papier dans le guichet
        
        // client cible
        System.out.println("la page info client est montée");
        if(cible != null){
            champNom.setText(cible.getNom());
            champPrenom.setText(cible.getPrenom());
            champCourriel.setText(cible.getCouriel());
        // Pour récuperer les comptes cibles. ( pour les tests, on vas en creer vite fait ici..
            comptesClient = App.gestionnaire.getComptesParClient(App.loggedUser.getCodeClient());
        
            
            // step 2: ajout des comptes à l'interface grphique via ListeElement
            for(Compte item : comptesClient){
                infoContainer.getChildren().add(
                  new ElementListe(item) {
                    @Override
                    protected void setAction1() {
                        System.out.println("Voir les transactions");
                        
                        try {
                            AdminListeTransactionsController.compteTarget = item;
                            App.setRoot("adminListeTransactions", infoContainer.getScene());
                        } catch (IOException ex) {                                 // TODO faire un popup qui affiche que le systeme vas s'arreter
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Erreur interne");
                            alert.setContentText("Nous notons une erreur interne. Revenez plus tard !");


                            alert.showAndWait();
                            System.out.println("IOException. arret du systeme");
                            System.out.println(ex.getMessage());
                            System.exit(1);
                        }
                    }

                    @Override
                    protected void setAction2() {
                        System.out.println("Action 2 sur comptes");
                    }

                    @Override
                    protected void setAction3() {
                        System.out.println("Action 3 sur comptes");
                    }
                }
                );
            }
        }
    }    
}
