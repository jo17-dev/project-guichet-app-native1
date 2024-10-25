public class Banque {

    private double montantMaximum;
    private double montantRemplissage;

    // Constructeur
    public Banque(double montantMaximum, double montantRemplissage) {
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
