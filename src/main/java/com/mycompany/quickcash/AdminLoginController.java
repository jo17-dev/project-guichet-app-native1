package com.mycompany.quickcash;

import backend.Client;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import static backend.Client.USER_ROLE;
import static backend.Client.USER_STATUS;

/**
 * FXML Controller class
 *
 * @author jo17-dev
 */
public class AdminLoginController implements Initializable {
    @FXML private TextField codeClient;
    @FXML private TextField nipEntry;
    @FXML private Button btnValider;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void login(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        // 1er etape: check les entréé
        try{
            Integer.parseInt(nipEntry.getText()); // test l'entrée est un nombre
            if(codeClient.getText().length() == 0 || nipEntry.getText().length() == 0){
                throw new Exception("Les champs doivent etre non vides");
            }
        }catch(Exception ex){
            System.out.println("la sasie des entrées est non valide");
            alert.setTitle("Saisies non conformes");
            alert.setContentText("Les champs doivent être non vides et vous devez utiliser uniquement des chiffres !");
            alert.showAndWait();
            return;
        }
        App.loggedUser = App.gestionnaire.validerUtilisateur(codeClient.getText(), Integer.parseInt(nipEntry.getText()));
        if(App.loggedUser == null){
            // echec: identifiansts non valides
            System.out.println("Identifiants non trouvés dans la 'bd' hahaha");
            alert.setTitle("Erreur d'identifiants");
            alert.setContentText("Les identifiants entrés ne correspondent à aucun client.. veuillez ressayer.");
            alert.showAndWait();
            return;
        }else if(App.loggedUser.userRole == Client.USER_ROLE.ADMIN){
            // le user est un admin: on v
            App.loggedUser.userRole = USER_ROLE.ADMIN;
            System.out.println("Le user est un admin");
        }else{
            // le user est un client
            App.loggedUser.userRole = USER_ROLE.CLIENT;
            System.out.println("Le user est un client");
        }
        
        // on le logge dans le compte admin
        App.loggedUser.userStatus = USER_STATUS.LOGGED_IN;
        
        try {
            App.setRoot(App.loggedUser.userRole == USER_ROLE.ADMIN ? "adminListeClients" : "clientAcceuil", btnValider.getScene());
        } catch (IOException ex) {
            // TODO faire un popup qui affiche que le systeme vas s'arreter
            alert.setTitle("Erreur interne");
            alert.setContentText("Nous notons une erreur interne. Revenez plus tard !");
            alert.showAndWait();
            
            System.out.println("IOException. arret du systeme");
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }
    
}
