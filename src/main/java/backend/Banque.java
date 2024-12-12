package backend;

public class Banque extends Compte {
    private double montantTotal;
    

    // Constructeur
    public Banque(int numeroCompte, String codeClient, double soldeCompte) {
        super(numeroCompte, codeClient, soldeCompte);
    }

//    // Remplir Guichet 
//    public void remplirGuichet(double montant) {
//        if (montantRemplissage + montantTotal > super.getmontantTransfertMaximum())  {
//            System.out.println("Echec. Le montant dépasse le montant maximum");
//        } else {
//            montantTotal += montantRemplissage;
//        }
//    }

    // toString 
//    public String toString() {
//        return "Montant Total : " + montantTotal + "Montant Maximum : " + montantMaximum + ", Montant Remplissage : " + montantRemplissage;
//    }
}
