package backend;

public class Hypothecaire extends Compte {

    public Hypothecaire(int numeroCompte, String codeClient, double soldeCompte, double retraitMaximum, double montantTransfertMaximum) {
        super(numeroCompte, codeClient, soldeCompte, retraitMaximum, montantTransfertMaximum);
    }
    
    public String toString() {
        return super.toString();
    }
}
