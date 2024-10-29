public class Marge extends Compte {

    private double tauxInteret;
    
    // Constructeur
    public Marge(double tauxInteret, int numeroCompte, int codeClient, double soldeCompte, double retraitMaximum, double montantTransfertMaximum) {
        super(numeroCompte, codeClient, soldeCompte, retraitMaximum, montantTransfertMaximum);
        this.tauxInteret = tauxInteret;
    }

    // Augumenter solde marge
    public void augumenterSoldeMarge(double pourcentage) {
        solde += (solde*(pourcentage/100));
    }

    // ToString
    public String toString() {
        return super.toString() + "Taux D'interet : " + tauxInteret;
    }
}
