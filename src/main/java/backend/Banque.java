package backend;

public class Banque extends Compte {

    private double montantMaximum;
    private double montantRemplissage;

    // Constructeur
    public Banque(int numeroCompte, String codeClient, double soldeCompte, double retraitMaximum, double montantTransfertMaximum, double montantMaximum, double montantRemplissage) {
        super(numeroCompte, codeClient, soldeCompte, retraitMaximum, montantTransfertMaximum);
        this.montantMaximum = montantMaximum;
        this.montantRemplissage = montantRemplissage;
    }

    // Remplir Guichet 
    public void remplirGuichet() {
    }

    // toString 
    public String toString() {
        return "Montant Maximum : " + montantMaximum + ", Montant Remplissage : " + montantRemplissage;
    }
}
