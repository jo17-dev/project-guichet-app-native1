package com.mycompany.quickcash;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class to manage customer creation hahaha
 *
 * @author LENOVO
 */
public class AdminAjouterClientController implements Initializable {
    @FXML
    private Label errorText; // contient la liste des potetiels erreurs qui seront affichés.
    @FXML 
    private Label generatedClientCode; // contient le code généré automatiquement par le potentiel nouvel utilisateur:
    
    // champs de formulaires
    @FXML private TextField nomEntry;
    @FXML private TextField prenomEntry;
    @FXML private TextField courrielEntry;
    @FXML private TextField telephoneEntry;
    @FXML private TextField nipEntry;
    @FXML private RadioButton estAdminEntry;
    @FXML
    private Button valider_btn; // contient le bouton valider pour creer un user
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Page de creation d'un client montee");
        generatedClientCode.setText(String.valueOf(backend.Client.getNbreClients()));
    }    
    
    
    @FXML
    public void annuler(){
        // Vu que pour cliquer sur ce bouton la fenêtre doir être d'abors ouverte, on a juste à la fermer via le toogle
        App.toggleStage("adminAjouterClient");

    }
    
    // 1ere etape: valider toutes les données et afficher les potentiels erreures: l'email doit être conforme et unique..        
    // 2e etape: si tout est bon, on crée dans le gestionnaire, on fait un alert confirm
    // on ferme la fenetre
    @FXML
    public void valider(){
        System.out.println("___ btn --Valider");
        // recupération de toutes les valeures
        String nom = nomEntry.getText();
        String prenom = prenomEntry.getText();
        String courriel = courrielEntry.getText();
        String telephone = telephoneEntry.getText();
        String nip = nipEntry.getText();
        
        boolean aDesErreurs = false;
        errorText.setText("");
        
        // vérification des entrés
        if(nom.length() == 0 || prenom.length() == 0 || courriel.length() == 0 || telephone.length() == 0 || nip.length() == 0){
            errorText.setText("- Tous les champs sont requis");
            aDesErreurs =  true;
        }
        
        try{
            Integer.parseInt(nip);
        }catch(NumberFormatException nogGoodInputHahaha){
            System.out.println("mauvais format de donnés");
            errorText.setText(errorText.getText() + "\n-Le NIP doit être uniquement des chiffres");
            aDesErreurs = true;
        }
        
        if(!courriel.contains("@") || !courriel.contains(".") || courriel.length()<= 2){
            System.out.println("mauvais format d'email");
            errorText.setText(errorText.getText() + "\n-L'email est mal formatée");
            aDesErreurs= true;
        }
        
        
        // On check l'unicité de l'email:
        if(App.gestionnaire.chercherClientParEmail(courriel) == null){
            errorText.setText(errorText.getText() + "\n- Cet email est déja existant..");
        }
        
        if(aDesErreurs){
            return ;
        }
        
        System.out.println("Tout est conforme");
        
        App.gestionnaire.creerClient(generatedClientCode.getText(), nom, prenom, telephone, courriel, Integer.parseInt(nip), estAdminEntry.isSelected());
        
        
        // reinitialisation mise à jours des vues
        nomEntry.setText("");
        prenomEntry.setText("");
        courrielEntry.setText("");
        telephoneEntry.setText("");
        nipEntry.setText("");
        errorText.setText("");
        
        generatedClientCode.setText(String.valueOf(backend.Client.getNbreClients()));
        try{
            App.setRoot("adminListeClients", App.stageConnexionAdmin.getScene());
        }catch(IOException ioe){
            System.out.println("Imposssible de recharger la page de liste des clients.. mais pas grave ahahah");
        }
        
       // on ferme la petite fenetre et on affiche le message de confirmation
       App.toggleStage("adminAjouterClient");
       BasicControls.popUp("Operation reussie", "Et Hop ! un nouveau client ajouté", Alert.AlertType.CONFIRMATION);
    }
}
