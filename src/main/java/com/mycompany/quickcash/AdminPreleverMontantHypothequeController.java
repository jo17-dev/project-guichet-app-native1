package com.mycompany.quickcash;

import backend.Hypothecaire;
import backend.PreleverMontantException;
import java.io.IOException;
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
public class AdminPreleverMontantHypothequeController implements Initializable {
    @FXML
    private Label errorText;
    
    @FXML
    private TextField montantEntry;
    @FXML
    private Label generatedClientCode; // id cu compte:
    private static Hypothecaire compteCible = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Page de prelevement de compte hypothece montée");
    }    
    
        public void annuler(){
        System.out.println("Annulation des choses");
        App.toggleStage("adminPreleverMontantHypotheque");
    }    
    
    /**
     * Creer un compte à un utilisateur
     * 1er etape: a validation des infos
     * 2e etape: on fait juste try catch et ex.getMessage()
     * 3e etape: creation proprement dite via gestionnaire
     * pup-up creation reussie
     * fermer la fenêtre.
     * 
     * 
     * */
    @FXML
    public void valider(){
        System.out.println("Operation de prelevement...");
        boolean isError = false;
        String montant = montantEntry.getText();
        
        // on check is une valeure a été rentrée
        if(montant.length() == 0){
            errorText.setText("- Tous les champs sont obligatoires");
            isError = true;
        }
        
        // on check si c'est un nombre
        try{
            if(Double.valueOf(montant) <= 0){
                System.out.println("le nbre est non conforme");
                throw new NumberFormatException();
            }
        }catch(NumberFormatException e){
            System.out.println("nber format exception");
            errorText.setText("- Vous devez entrer un nombre valide");
            isError = true;
        }
        

        if(isError){
            return;
        }
        
        if(compteCible == null){
            System.out.println("le compte cible est null");
            BasicControls.popUp("Erreur interne", "Nous notons une erreur de traitement... referez-vous au service client", Alert.AlertType.ERROR);
            return;
        }
        
        // normalement il n'y pas d'erreur à partir d'ici
        System.out.println("pas d'erreur... tout est correct");
        
        try {
            App.gestionnaire.preleverMontantdeHypothecaire(compteCible.getNumeroCompte(), Integer.valueOf(compteCible.getCodeClient()), Double.valueOf(montant));
            try {
                App.setRoot("adminInformationsClient", App.stageConnexionAdmin.getScene());
            } catch (IOException ex) {
                System.out.println("On aurait pu recharger la page mais bon..");
            }
        } catch (PreleverMontantException ex) {
            BasicControls.popUp("statut de l'opération", ex.getMessage(), Alert.AlertType.INFORMATION);
            return;
        }
        
        
        //on reinitialise tous les champs
        errorText.setText("");
        montantEntry.setText("");
                
        // On fait le message de confirmation et on sort de la fenêtre
        
        App.toggleStage("adminPreleverMontantHypotheque");
        BasicControls.popUp("Opération reussie", "Le prelevement de " + montant + " a été effectué avec succes", Alert.AlertType.INFORMATION);
    }
    
    public static void setCompteCible(Hypothecaire target){
        compteCible = target;
    }
}
