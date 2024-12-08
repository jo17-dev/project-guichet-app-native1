package com.mycompany.quickcash;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class to manage customer creation hahaha
 *
 * @author LENOVO
 */
public class AdminAjouterClientController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Page de creation d'un client montee");
    }    
    
    
    @FXML
    public void annuler(){
        // Vu que pour cliquer sur ce bouton la fenêtre doir être d'abors ouverte, on a juste à la fermer via le toogle
        App.toggleStage("adminAjouterClient");

    }
}
