package backend;

public class Epargne extends Compte {
    private final double tauxInteret = 1; // doit etre entre 0 et 100 !. dans l'énoncé, il est de 1%

    public Epargne(int numeroCompte, String codeClient, double soldeCompte){
        super(numeroCompte, codeClient, soldeCompte);
    }

    // paie les interets et retourne le montant de l'interet ( de base pour logger dans les transactions)
    public double paiementInteret(){
        // retrait(getSolde() * tauxInteret);
        double montantInteret = getSolde()*tauxInteret/100;
        setSolde(getSolde()+ montantInteret);
        
        return montantInteret;
    }

}
