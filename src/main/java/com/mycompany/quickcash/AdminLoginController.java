/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.quickcash;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
        // 1er etape: check les entréé
        try{
            Integer.parseInt(nipEntry.getText());
            if(codeClient.getText().length() == 0 || nipEntry.getText().length() == 0){
                throw new Exception("Les champs doivent etre non vides");
            }
        }catch(Exception ex){
            System.out.println("la sasie des entrées est non valide");
            return;
        }
        boolean estAdmin = false;
        if(App.gestionnaire.validerUtilisateur(codeClient.getText(), Integer.parseInt(nipEntry.getText())) == null){
            // echec: identifiansts non valides
            System.out.println("Identifiants non trouvés dans la 'bd' hahaha");
            return;
        }else if(App.gestionnaire.validerUtilisateur(codeClient.getText(), Integer.parseInt(nipEntry.getText())).checkAdmin()){
            // le user est un admin
            estAdmin = true;
            System.out.println("Le user est un admin");
        }else{
            // on le logge dans l'espace client
            System.out.println("Le user n'est pas un admin");
        }
        
        
        // le user est un admin
        System.out.println("Le user est un admin");
        App.userStatus = App.USER_STATUS.LOGGED_IN;
        // on le logge dans le compte admin
        try {
            App.setRoot(estAdmin ? "adminListeClients" : "clientAcceuil", btnValider.getScene());
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
    
}
