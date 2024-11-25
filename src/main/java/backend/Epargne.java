package backend;

public class Epargne extends Compte {
    private double tauxInteret; // doit etre entre 0 et 100 !


    public Epargne(int numeroCompte, String codeClient, double soldeCompte, double retraitMaximum, double montantTransfertMaximum, double tauxInteret){
        super(numeroCompte, codeClient, soldeCompte, retraitMaximum, montantTransfertMaximum);// montantRetrait=1000
        this.tauxInteret = tauxInteret;
    }

    public void paiementInteret(){
        // retrait(getSolde() * tauxInteret);
        setSolde(getSolde()- getSolde()*tauxInteret/100);
    }

}
