package backend;

public class Cheque extends Compte {
    public Cheque(int numeroCompte, int codeClient, double soldeCompte, double retraitMaximum, double montantTransfertMaximum){
        super(numeroCompte, codeClient, soldeCompte, retraitMaximum, montantTransfertMaximum);// montantRetrait=1000
    }

    public void paiementFacture(double montant){
        if(getmontantTransfertMaximum() < montant){
            System.out.println("echec de paiement de facture: trop grand pour un transfert");
        }else{
            setSolde(getSolde() - montant);
        }
    }
}
