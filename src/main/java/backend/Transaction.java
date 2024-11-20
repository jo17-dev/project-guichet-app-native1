package backend;

public class Transaction {

    private int numeroTransaction;
    private double montant;
    private Compte compte;
    private Compte compteTransfert;
    private String type;
    
    public static int nbreTransaction = 0;

    // Constructeur

    public Transaction(int numeroTransaction, double montant, Compte compte, Compte compteTransfert, String type){
        this.numeroTransaction = numeroTransaction;
        this.montant = montant;
        this.compte = compte;
        this.compteTransfert = compteTransfert;
        this.type = type;
    }

    // toString 
    public String toString() {
        return "Numéro de transaction : " + numeroTransaction + ", Montant : " + montant + ", Compte : " + compte + ", Compte à transferer : " + compteTransfert + ", Type de transaction : " + type;
    }
}