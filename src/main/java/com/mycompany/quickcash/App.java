package com.mycompany.quickcash;

import backend.Banque;
import backend.Cheque;
import backend.Client;
import backend.Compte;
import backend.Epargne;
import backend.GestionnaireGuichet;
import backend.Hypothecaire;
import backend.Marge;
import backend.Transaction;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene sceneConnexionClient;
    public static Scene sceneConnexionAdmin;
    static Stage stageConnexionAdmin;
    static Stage stageAdminCreerClient;
    static Stage stageAdminCreerCompte;
    static Stage stageClientRetraitArgent;
    static Stage stageClientDepotArgent;
    static Stage stageClientTransfertComptes;
    static Stage stageClientPayerFacture;
    static Stage stageAdminRemplirGuichet;
    protected static GestionnaireGuichet gestionnaire;
    
    public static Client loggedUser = null;

    @Override
    public void start(Stage stage) throws IOException {
        // 1st etape: initialiser le gestionnaire de Guichet pour la banque haha
        
        initGestionnaireGuichet();
        
        
        
        //2e etape:initialiser les vues et afficher les parties login de client et admin
        
        stageConnexionAdmin = new Stage();
        sceneConnexionClient = new Scene(loadFXML("adminLogin"), 720, 450);
        stageConnexionAdmin.setScene(sceneConnexionClient);
        stageConnexionAdmin.show();
        
        
        stageAdminCreerClient = new Stage(); // le reste des choses vont êtres faites lors du click sur le bouton pour économiser les ressources..
        stageAdminCreerClient.setScene(new Scene(loadFXML("adminAjouterClient"),330, 462));
        
        stageClientRetraitArgent = new Stage();
        stageClientRetraitArgent.setScene(new Scene(loadFXML("clientRetraitArgent"), 434, 277));
        
        stageClientDepotArgent = new Stage();
        stageClientDepotArgent.setScene(new Scene(loadFXML("clientDepotArgent"), 434, 277));
        
        stageClientTransfertComptes = new Stage();
        stageClientTransfertComptes.setScene(new Scene(loadFXML("clientTransfertComptes"), 434, 308));
        
        stageClientPayerFacture = new Stage();
        stageClientPayerFacture.setScene(new Scene(loadFXML("clientPayerFacture"), 434, 277));
        
        
        stageAdminCreerCompte = new Stage();
        stageAdminCreerCompte.setScene(new Scene(loadFXML("adminCreerCompte"), 333, 321));
        
        stageAdminRemplirGuichet = new Stage();
        stageAdminRemplirGuichet.setScene(new Scene(loadFXML("adminRemplirGuichet"), 332, 277));
    }
    
    public static void setRoot(String fxml, Scene target) throws IOException {
        target.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    /**
     * show or close a stage depending on his current stage
     * @param target page 
     * 
     */
    public static void toggleStage(String target){
        Stage targetStage = null;
        switch(target){
            case "adminAjouterClient":
                targetStage = stageAdminCreerClient;
                break;
            case "adminCreerCompte":
                targetStage = stageAdminCreerCompte;
                break;
            case "clientRetraitArgent":
                targetStage = stageClientRetraitArgent;
                break;
            case "clientDepotArgent":
                targetStage = stageClientDepotArgent;
                break;
            case "clientTransfertComptes":
                targetStage = stageClientTransfertComptes;
                break;
            case "clientPayerFacture":
                targetStage = stageClientPayerFacture;
                break;
            case "adminRemplirGuichet":
                targetStage = stageAdminRemplirGuichet;
                break;
            default:
                System.out.println("Aucune fenetre n'as pu être affichée");
                break;
        }
        
        // targetStage != null ? (targetStage.isShowing() ? targetStage.close() : targetStage.show()) : System.out.println("dammn this doesn't works");

        // si la fenetre cible existe et n'est pas ouverte, on la ferme. sinon on l'ouvre... c'est TRIVIAL hahahah
        // OUIIIII mes commentaires sont WTF hahaha mais avouez sa détends l'athmosphère de pression comme ça hahahah. bon promis j'arrete
        if(targetStage != null){
            if(targetStage.isShowing()){
                targetStage.close();
                System.out.println("page fermée");
            }else{
                targetStage.show();
                System.out.println("page ouverte ");
            }
        }
    }
    
    /**
     * Initialisation de l'attribut statique gestionnaire de guichet
     * Le gestionnaire de Guichet vas être initialisé avec:
     * -5 (+1) clients
     * - un compte cheque et un compte epargne par client
     * - un admin( 6e client)
     */
    public void initGestionnaireGuichet(){
        // compte client de la banque:
        Client compteClientDeLaBanqueActuelle = new Client( Character.toString((char) Client.getNbreClients()), "quickCash", "", "tel-banque", "banque@couriel.com", 0000);
        // compte de la banque actuelle (quickCash)
        Compte compte = new Banque(Compte.getNbreComptes(), compteClientDeLaBanqueActuelle.getCodeClient(), 15000 );
        Client admin;
        
        ArrayList<Cheque> cheques = new ArrayList<>();
        ArrayList<Epargne> epargnes = new ArrayList<>();
        ArrayList<Marge> marges = new ArrayList<>();
        ArrayList<Hypothecaire> hypothecaires = new ArrayList<>();
        ArrayList<Transaction> transactions = new ArrayList<>();
        double soldeCompteCourant = 0;
        // creation des clients
        ArrayList<Client> clients = new ArrayList<>();
        clients = new ArrayList<>();
        // creation et ajout de l'admin
       admin = new Client(String.valueOf(Client.getNbreClients()), "Bertrand", "Joel", "514-xxx-3--7", "joel@gmail.com", 1000, true);
       clients.add(admin);
       for(int i=0; i<5; i++){
            clients.add(
                new Client(String.valueOf(Client.getNbreClients()), 
                    "nomClient "+i, 
                    "prenomClient "+i,
                     "514"+i, "couriel@gmail."+i, 200+i
                )
            );
       }
       
       // creation des comptes asssociés à chaque compte:
       
       for(Client client : clients){
           // compte cheque obligatoire
            cheques.add(
                new Cheque(
                     Compte.getNbreComptes(),
                     client.getCodeClient(),
                     120
                )
           );
           
            // compte epargne:
            epargnes.add(
                new Epargne(                     
                    Compte.getNbreComptes(),
                    client.getCodeClient(),
                    120
                )
            );
       }
       // instantiation de gestionnaireGuichet:
       gestionnaire = new GestionnaireGuichet(compte, clients, cheques, epargnes, marges, hypothecaires, transactions, soldeCompteCourant);
        System.out.println(clients);
       
    }

    public static void main(String[] args) {
        launch();
    }

}