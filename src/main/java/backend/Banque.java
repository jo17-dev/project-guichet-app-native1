package backend;

public class Banque extends Compte {

    private double montantMaximum;
    private double montantRemplissage;
    private double montantTotal;

    // Constructeur
    public Banque(int numeroCompte, String codeClient, double soldeCompte, double retraitMaximum, double montantTransfertMaximum, double montantMaximum, double montantRemplissage) {
        super(numeroCompte, codeClient, soldeCompte, retraitMaximum, montantTransfertMaximum);
        this.montantMaximum = montantMaximum;
        this.montantRemplissage = montantRemplissage;
        this.montantTotal = montantTotal;
    }

    // Remplir Guichet 
    public void remplirGuichet() {
        if (montantRemplissage + montantTotal > montantMaximum)  {
            System.out.println("Echec. Le montant dépasse le montant maximum");
        } else {
            montantTotal += montantRemplissage;
        }
    }

    // toString 
    public String toString() {
        return "Montant Total : " + montantTotal + "Montant Maximum : " + montantMaximum + ", Montant Remplissage : " + montantRemplissage;
    }
}
