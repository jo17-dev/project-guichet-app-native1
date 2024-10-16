public class Compte {
    private int numeroCompte;
    private int codeClient;
    private double soldeCompte;
    private double retraitMaximum;
    private double montantTransfertMaximum;

    public Compte(int numeroCompte, int codeClient, double soldeCompte, double retraitMaximum, double montantTransfertMaximum) {
        this.numeroCompte = numeroCompte;
        this.codeClient = codeClient;
        this.soldeCompte = soldeCompte;
        this.retraitMaximum = retraitMaximum;
        this.montantTransfertMaximum = montantTransfertMaximum;
    }
}
