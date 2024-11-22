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

    @Override
    public void start(Stage stage) throws IOException {
        sceneConnexionClient = new Scene(loadFXML("clientLogin"), 720, 450);
        stage.setScene(sceneConnexionClient);
        stage.show();
        
        
        stageConnexionAdmin = new Stage();
        sceneConnexionClient = new Scene(loadFXML("adminLogin"), 720, 450);
        stageConnexionAdmin.setScene(sceneConnexionClient);
        stageConnexionAdmin.show();
    }
    
    public static void setRoot(String fxml, Scene target) throws IOException {
        target.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}