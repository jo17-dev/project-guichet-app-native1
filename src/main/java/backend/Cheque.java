package backend;

public class Cheque extends Compte {
    private final double fraisPaiementFacture = 1.25;
    public Cheque(int numeroCompte, String codeClient, double soldeCompte){
        super(numeroCompte, codeClient, soldeCompte);// montantRetrait=1000
    }

    public void paiementFacture(double montant) {
        double montantAvecFrais = montant + montant*fraisPaiementFacture/100;
        if(getmontantTransfertMaximum() < montantAvecFrais){
            System.out.println("echec de paiement de facture: trop grand pour un transfert");
        }else{
            setSolde(getSolde() - (montantAvecFrais));
        }
    }
    
    @Override
    public String toString(){
       return "Cheque " + super.toString();
    }
}
