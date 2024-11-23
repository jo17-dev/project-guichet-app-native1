package com.mycompany.quickcash;

import guicomponent.ElementListe;
import backend.Client;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author jo17-dev
 */
public class AdminListeClientsController implements Initializable {
    
    @FXML VBox itemContainer;
    
    
    private ArrayList<Client> listeClients;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 1st etape: recupération des données de touts les clients de gestionnaire guichers
       listeClients = new ArrayList<>();
       for(int i=0; i<11; i++){
        listeClients.add(new Client("00"+i, 
                "nomClient "+i, 
                "prenomClient "+i,
                 "123"+i, "couriel@gmail."+i, 200+i));
       }
       
       
       // 2nd etape: affichage des données dans la vue
       for(Client item : listeClients){
           itemContainer.getChildren().add(new ElementListe(item) {
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
               protected void setAction2() {
                   System.out.println("action 2");
               }

               @Override
               protected void setAction3() {
                   System.out.println("action 3");
               }
           });
       }
       
        System.out.println("page - liste client montée");
    }

   @FXML
   public void logout(){
       // ceci n'est pas le vrai code de la methode.
       // la vrai version sera crée ultérieurement
       System.exit(0);
   }
   
    
}
