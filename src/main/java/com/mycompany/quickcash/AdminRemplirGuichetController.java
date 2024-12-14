package com.mycompany.quickcash;


import backend.Banque;
import backend.RemplirGuichetException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author jo17-dev
 */
public class AdminRemplirGuichetController implements Initializable {
    @FXML
    private Label errorText;
    
    @FXML
    private TextField montantRemplissageEntry;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("page de remplissage de guichet montée");
    }    
    
    public void annuler(){
        System.out.println("Annulation des choses");
        App.toggleStage("adminRemplirGuichet");
    }    
    
    /**
     * Creer un compte à un utilisateur
     * 1er etape: a validation des infos
     * 2e etape: recherche d'un client par son email( jai pensé que ce serait mieux que son id ;)
     * 3e etape: creation proprement dite via gestionnaire
     * pup-up creation reussie
     * fermer la fenêtre.
     * 
     * 
     * */
    @FXML
    public void valider(){
        System.out.println("Remplissage de guichet");
        boolean isError = false;
        String montantRemplissage = montantRemplissageEntry.getText();
        
        // on check is une valeure a été rentrée
        if(montantRemplissage.length() == 0){
            errorText.setText("- Tous les champs sont obligatoires");
            isError = true;
        }
        
        // on check si c'est un nombre
        try{
            Double.valueOf(montantRemplissage);
        }catch(NumberFormatException e){
            System.out.println("nber format exception");
            errorText.setText("- Vous devez entrer un nombre valide");
            isError = true;
        }
        

        if(isError){
            return;
        }
        
        // normalement il n'y pas d'erreur à partir d'ici
        System.out.println("pas d'erreur... tout est correct");
        
        try {
            ((Banque) App.gestionnaire.getCompteBancaire()).remplirGuichet(Double.parseDouble(montantRemplissage));
        } catch (RemplirGuichetException ex) {
            BasicControls.popUp("Echec de l'Operation", ex.getMessage(), Alert.AlertType.ERROR);
            return;
        }
        
        
        //on reinitialise tous les champs
        errorText.setText("");
        montantRemplissageEntry.setText("");
        // On reload la page        
        // On fait le message de confirmation et on sort de la fenêtre
        
        App.toggleStage("adminRemplirGuichet");
        BasicControls.popUp("Opération reussie", "Du cash a été ajouté au guichet", Alert.AlertType.CONFIRMATION);
    }
}
