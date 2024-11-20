package com.mycompany.quickcash;

import backend.Client;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.GridPane;
/**
 *
 * @author jo17-dev
 * Cette classe premet de creer un élément GUI qui vas être listé dans un Pane
 * c'est une espèce d'observable bien designé et réutilisable hahaha
 * 
 * ---- utilisation:
 * juste l'instancier et donner le produit en parametre
 * On vas avoir un constructeur par affichage et donc par type d'object
 */
public abstract class ElementListe extends GridPane {
    private Object cible;
    
    
    // classes abstraites liées aux actions. à date il y a maximum deux de repérés
    protected abstract void setAction1();
    protected abstract void setAction2();
    protected abstract void setAction3();
    
            
    //    constructeur: Pour la classe Client 
    public ElementListe(Client target){
        super();
        cible = target;
        
        super.add(new Label(target.getCodeClient()), 0, 0);
        super.add(new Label(target.getNom()), 4, 0);
        super.add(new Label(target.getCouriel()), 6, 0);
        
        
        // stylisation du grid
        super.setStyle("-fx-border-width: 1px 0px 0px 0px;-fx-border-color: #2b3385; -fx-padding: 4px");
        
    }
    
}
