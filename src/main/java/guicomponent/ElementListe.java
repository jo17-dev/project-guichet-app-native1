package guicomponent;

import backend.Client;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.ColumnConstraints;
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
        double nombreDivisions = 5;
        
        String boutonBleuStyle = "-fx-background-color:  #2b3385; -fx-text-fill: #ffffff";
        String boutonRougeStyle = "-fx-background-color:  #942F2F; -fx-text-fill: #ffffff";
        
        Button boutonVoir = new Button("voir");
        Button boutonBloquer =new Button("Bloquer");
        
        
        super.add(new Label(target.getCodeClient()), 0, 0);
        super.add(new Label(target.getNom()), 1, 0);
        super.add(new Label(target.getCouriel()), 2, 0);
        super.add(boutonBloquer, 4, 0);
        super.add(boutonVoir, 3, 0);
        
        
        
        // 3th etape: ajouter les styles
        
        boutonVoir.setStyle(boutonBleuStyle);
        boutonBloquer.setStyle(boutonRougeStyle);
        
        
        
        // stylisation du gridPane
        // TODO: il faut que le grid s'étende sur toute le ligne
//        super.setMinHeight(GridPane.);
//        super.setAlignment(Pos.CENTER);
        super.setWidth(GridPane.USE_COMPUTED_SIZE);
        super.setStyle("-fx-border-width: 1px 0px 0px 0px;-fx-border-color: #2b3385; -fx-padding: 4px");
//        super.setGridLinesVisible(true);
        super.setHgap(10);
        GridPane.setHalignment(boutonVoir, HPos.RIGHT);
       
        
        for(int i=0; i< nombreDivisions; i++){
            ColumnConstraints columnCons = new ColumnConstraints();
            columnCons.setPercentWidth(100/nombreDivisions);
            super.getColumnConstraints().add(columnCons);
        }
//        super.setHgap(5);
        
    }
    
}
