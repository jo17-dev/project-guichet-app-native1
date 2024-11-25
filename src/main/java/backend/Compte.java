package backend;

public class Compte {
    private int numeroCompte;
    private String codeClient;
    private double soldeCompte;
    private double retraitMaximum;
    private double montantTransfertMaximum; // nécéssaire ?

    public Compte(int numeroCompte, String codeClient, double soldeCompte, double retraitMaximum, double montantTransfertMaximum) {
        this.numeroCompte = numeroCompte;
        this.codeClient = codeClient;
        this.soldeCompte = soldeCompte;
        this.retraitMaximum = retraitMaximum; // doit forcement etre initialisé avec 1000 ici
        this.montantTransfertMaximum = montantTransfertMaximum;
    }

    public void retrait(double montant){
        if(montant % 10 == 0 || montant > retraitMaximum){
            System.out.println("Impossible de retirer les montant de plus de 1000 $");
        }else if(montant <= soldeCompte){
            soldeCompte -= montant;
        }
    }

    public void depot(double montant){
        if(montant > 0){
            soldeCompte += montant;
        }else{
            System.out.println("Impossible de faire le dépot d'un montant négatif");;
        }
    }

    public String getCodeClient(){
        return codeClient;
    }

    public int getNumeroCompte(){
        return numeroCompte;
    }

    public double getSolde(){
        return soldeCompte;
    }

    public void setSolde(double newSolde){
        soldeCompte = newSolde;
    }

    public double getmontantTransfertMaximum(){
        return montantTransfertMaximum;
    }

    public String toString(){
        return "Compte ( NumeroCompte: "+ numeroCompte + ", codeClient: " + codeClient + ", soldeCompte: "+ soldeCompte + ", retraitMaximum: "+ retraitMaximum + ", montantTransfertMaximum: " + montantTransfertMaximum + " )" ;
    }
}
