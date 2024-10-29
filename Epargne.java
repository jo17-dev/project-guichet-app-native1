public class Epargne extends Compte {
    private double tauxInteret; // doit etre entre 0 et 100 !


    public Epargne(int numeroCompte, int codeClient, double soldeCompte, double retraitMaximum, double montantTransfertMaximum){
        super(numeroCompte, codeClient, soldeCompte, retraitMaximum, montantTransfertMaximum);// montantRetrait=1000
    }

    public void paiementInteret(){
        // retrait(getSolde() * tauxInteret);
        setSolde(getSolde()- getSolde()*tauxInteret/100);
    }

}
