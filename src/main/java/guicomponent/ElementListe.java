package guicomponent;

import backend.Cheque;
import backend.Client;
import backend.Compte;
import backend.Epargne;
import backend.Hypothecaire;
import backend.Marge;
import backend.Transaction;
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
 * juste l'instancier et donner une instance de la classe en parametre
 * On vas avoir un constructeur par affichage et donc par type d'object
 */
public abstract class ElementListe extends GridPane {
    private Object cible;
    
    
    // classes abstraites liées aux actions. à date il y a maximum deux de repérés
    protected abstract void setAction1();
    protected abstract void setAction2();
    protected abstract void setAction3();
    
            
    //    constructeur: Pour la classe Client 
    public ElementListe(Client target, boolean estBloque){
        super();
        cible = target;
        double nombreDivisions = 5;
        
        String boutonBleuStyle = "-fx-background-color:  #2b3385; -fx-text-fill: #ffffff";
        String boutonRougeStyle = "-fx-background-color:  #942F2F; -fx-text-fill: #ffffff";
        
        Button boutonVoir = new Button("voir");
        Button boutonBloquer = new Button(estBloque ? "Débloquer" : "Bloquer");
        
        
        super.add(new Label(target.getCodeClient()), 0, 0);
        super.add(new Label(target.getNom()), 1, 0);
        super.add(new Label(target.getCouriel()), 2, 0);
        super.add(boutonBloquer, 4, 0);
        super.add(boutonVoir, 3, 0);
        
        
        
        // 3th etape: ajouter les styles
        
        boutonVoir.setStyle(boutonBleuStyle);
        boutonBloquer.setStyle(boutonRougeStyle);
        
        // 4th etape: assigner les évenements DANS L'ORDRE (Voir-> action1, bloquer/débloquer-> action2)
        boutonVoir.setOnMouseClicked(event ->{
            setAction1();
        });
        
        boutonBloquer.setOnMouseClicked(event ->{
            setAction2();
        });
        
        // stylisation du gridPane
        // TODO: il faut que le grid s'étende sur toute le ligne
//        super.setMinHeight(GridPane.);
//        super.setAlignment(Pos.CENTER);
//        super.setWidth(GridPane.USE_COMPUTED_SIZE);
        super.setStyle("-fx-border-width: 1px 0px 0px 0px;-fx-border-color: #2b3385; -fx-padding: 4px");
        super.setGridLinesVisible(false);
        super.setHgap(10);
        GridPane.setHalignment(boutonVoir, HPos.RIGHT);
       
        
        for(int i=0; i< nombreDivisions; i++){
            ColumnConstraints columnCons = new ColumnConstraints();
            columnCons.setPercentWidth(100/nombreDivisions);
            super.getColumnConstraints().add(columnCons);
        }
//        super.setHgap(5);
        
    }
    
    
    // Constructeur pour la view des comptes sur admin
    public ElementListe(Compte target){
        super();
        cible = target;
        double nombreDivisions = 3;
        
        String boutonBleuStyle = "-fx-background-color:  #2b3385; -fx-text-fill: #ffffff";
        String boutonRougeStyle = "-fx-background-color:  #942F2F; -fx-text-fill: #ffffff";
        
        Button boutonVoir = new Button("Consulter");
        Button boutonPrelever =new Button("Prelever");
        
        // determinaison du type de compte:
        String typeCompte = "Cheque";
        
        if(target instanceof Epargne){
            typeCompte = "epargne";
        }else if(target instanceof Hypothecaire){
            typeCompte = "hypothecaire";
        }else if(target instanceof Marge){
            typeCompte = "marge";
        }
        
        super.add(new Label("Compte "+typeCompte + "("+target.getNumeroCompte()+")"), 0, 0);
        super.add(new Label("Solde: "+ target.getSolde() + " $"), 1, 0);

        super.add(boutonVoir, 2, 0);
        if(typeCompte.equals("hypothecaire")){
            super.add(boutonPrelever, 3, 0);
//            nombreDivisions = 4;
        }else{
            super.add(new Label(""), 3, 0);
        }
        
        
        
        
        // 3th etape: ajouter les styles
        
        boutonVoir.setStyle(boutonBleuStyle);
        boutonPrelever.setStyle(boutonRougeStyle);
        
        // 4th etape: assigner les évenements DANS L'ORDRE (voir/consulter-> action1, prelever-> action2, )
        boutonVoir.setOnMouseClicked(event ->{
            setAction1();
        });
        
        boutonPrelever.setOnMouseClicked(event ->{
            setAction2();
        });
        
        
        super.setStyle("-fx-border-width: 1px 0px 0px 0px;-fx-border-color: #2b3385; -fx-padding: 4px");
        super.setGridLinesVisible(false);
        super.setHgap(10);
       
        
        for(int i=0; i< nombreDivisions-1; i++){
            ColumnConstraints columnCons = new ColumnConstraints();
            columnCons.setPercentWidth(100/nombreDivisions);
            super.getColumnConstraints().add(columnCons);
        }
//        GridPane.setHalignment(boutonVoir, HPos.LEFT);
//        GridPane.setFillWidth(boutonVoir, true);
//        GridPane.setFillWidth(boutonPrelever, true);
//        GridPane.setHalignment(boutonPrelever, HPos.RIGHT);
        GridPane.setHalignment(boutonVoir, HPos.RIGHT);
    }
    
    
    // Constructeur pour la view des comptes sur admin
    public ElementListe(Transaction target){
        super();
        cible = target;
        int nombreDivisions = 0;
        
        
        // creer les différents champs 
        // TODO à modifier par les donnés de target
        super.add(new Label("001"), 0, 0);
        super.add(new Label("dépot"), 1, 0);
        super.add(new Label("Non renseigné"), 2, 0);
        super.add(new Label("Compte #101"), 3, 0);
        super.add(new Label("55 $"), 4, 0);
        
        nombreDivisions = super.getChildren().size();
        
        
        super.setStyle("-fx-border-width: 1px 0px 0px 0px;-fx-border-color: #2b3385; -fx-padding: 4px");
        super.setHgap(10);
       
        
        for(int i=0; i< nombreDivisions; i++){
            ColumnConstraints columnCons = new ColumnConstraints();
            columnCons.setPercentWidth(100/nombreDivisions);
            super.getColumnConstraints().add(columnCons);
        }
        
        GridPane.setHalignment(super.getChildren().get(nombreDivisions-1) , HPos.CENTER);
    }
    
    public Object getTarget(){
        return cible;
    }
    
}
