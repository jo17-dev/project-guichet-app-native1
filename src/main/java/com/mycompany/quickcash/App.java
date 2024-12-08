package com.mycompany.quickcash;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene sceneConnexionClient;
    public static Scene sceneConnexionAdmin;
    static Stage stageConnexionAdmin;
    static Stage stageAdminCreerClient;

    @Override
    public void start(Stage stage) throws IOException {
        sceneConnexionClient = new Scene(loadFXML("clientLogin"), 720, 450);
        stage.setScene(sceneConnexionClient);
        stage.show();
        
        
        stageConnexionAdmin = new Stage();
        sceneConnexionClient = new Scene(loadFXML("adminLogin"), 720, 450);
        stageConnexionAdmin.setScene(sceneConnexionClient);
        stageConnexionAdmin.show();
        
        
        stageAdminCreerClient = new Stage(); // le reste des choses vont êtres faites lors du click sur le bouton pour économiser les ressources..
        stageAdminCreerClient.setScene(new Scene(loadFXML("adminAjouterClient"),330, 462));
    }
    
    public static void setRoot(String fxml, Scene target) throws IOException {
        target.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    /**
     * create or close a stage depending on his curenr stage
     * @param target page 
     * 
     */
    public static void toggleStage(String target){
        Stage targetStage = null;
        switch(target){
            case "adminAjouterClient":
                targetStage = stageAdminCreerClient;
                System.out.println(stageAdminCreerClient.isShowing());
                break;
            default:
                System.out.println("Aucune fenetre n'as pu être affichée");
                break;
        }
        
//        targetStage != null ? (targetStage.isShowing() ? targetStage.close() : targetStage.show()) : System.out.println("dammn this doesn't works");

        // si la fenetre cible existe et n'est pas ouverte, on la ferme. sinon on l'ouvre... c'est TRIVIAL hahahah
        // OUIIIII mes commentaires sont WTF hahaha mais avouez sa détends l'athmosphère de pression comme ça hahahah. bon promis j'arrete
        if(targetStage != null){
            if(targetStage.isShowing()){
                targetStage.close();
            }else{
                targetStage.show();
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }

}