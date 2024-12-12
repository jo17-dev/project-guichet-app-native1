package com.mycompany.quickcash;

import guicomponent.ElementListe;
import backend.Client;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class AdminListeClientsController extends BasicControls implements Initializable {
    
    @FXML VBox itemContainer;
    @FXML private Label username;
    
    private ArrayList<Client> listeClients;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 1st etape: recupération des données de touts les clients de gestionnaire guichers
       listeClients = App.gestionnaire.getClients();
       
       
       // 2nd etape: affichage des données dans la vue
       for(Client item : listeClients){
           username.setText(App.loggedUser.getNom() + " " + App.loggedUser.getPrenom());
           // ici, on vas afficher tous les compte client/admin sauf pour celui qui est actuelement connecté:
           if(item.equals(App.loggedUser)){
               continue;
           }
           itemContainer.getChildren().add(new ElementListe(item, App.gestionnaire.estBloque(item)) {
               // action 1: corespond au bouton pour voir un element en particulier
               @Override
               protected void setAction1() {
                   try{
                       System.out.println("yooooo + " );
                       System.out.println("yooooo + " + (itemContainer.getScene() == App.sceneConnexionAdmin ? "yesss" : "Noooo"));
                       AdminInformationsClientController.cible = item;
                       App.setRoot("adminInformationsClient", itemContainer.getScene());
                        
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
               }

               @Override
               // Ceci est l'action pour bloquer/debloquer un client
               protected void setAction2() {
                   // Ceci bloque le client s'il ne l'est pas et le s'il l'est     

                    if(App.gestionnaire.estBloque(item) == true){
                        App.gestionnaire.debloquerClient(item);
                    }else{
                        App.gestionnaire.bloquerClient(item);
                    }
                   System.out.println("action 2 - bloquer/debloquer un client");
                    
                   try {
                       // reaffichage de cette vue
                       App.setRoot("adminListeClients", itemContainer.getScene());
                   } catch (IOException ex) {
                       System.out.println("Impossible de recharger la page... mais pas grave hahaha");
                   }
               }

               @Override
               protected void setAction3() {
                   System.out.println("action 3");
               }
           });
       }
       
        System.out.println("page - liste client montée");
    }
   
    
}
