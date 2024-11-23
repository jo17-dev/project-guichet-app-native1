package com.mycompany.quickcash;

import backend.Client;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author jo17-dev
 */
public class AdminInformationsClientController implements Initializable {
    
    @FXML Button btn_lister_clients;
    
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
        System.out.println("la page info client est montée");
        if(cible != null){
            champNom.setText(cible.getNom());
            champPrenom.setText(cible.getPrenom());
            champCourriel.setText(cible.getCouriel());
//            champTelephone.setText(cible.get);
        }
    }    
    
    
    @FXML
    public void afficher_liste_clients(){
        try{
            App.setRoot("adminListeClients", btn_lister_clients.getScene());
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
