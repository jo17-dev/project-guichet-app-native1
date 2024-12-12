package backend;

public class Marge extends Compte {
/**
 * Il faut savoir que le compte marge est un compte spécial.. lorsq
 */
    private final double tauxInteret = 5;
    
    // Constructeur
    public Marge(double tauxInteret, int numeroCompte, String codeClient, double soldeCompte) {
        super(numeroCompte, codeClient, soldeCompte);
    }

    // Augumenter solde marge de l'ordre du taux d'interet
    public void augumenterSoldeMarge() {
        setSolde(getSolde() + getSolde()*tauxInteret/100);
    }
    
    // Ajouter au solde marge le montant souhaité:
    public void augumenterSoldeMarge(double montant) {
        setSolde(getSolde() + getSolde()*tauxInteret/100);
    }
    
    // retranche au solde marge le montant souhaité:
//    public void diminuerSoldeMarge(double montant) {
//        if(montant < 0 )
//        setSolde(getSolde() - getSolde()*tauxInteret/100);
//    }

    // ToString
    @Override
    public String toString() {
        return super.toString() + "Taux D'interet : " + tauxInteret;
    }
}
