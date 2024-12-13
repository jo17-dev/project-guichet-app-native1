package backend;

public class Hypothecaire extends Compte {

    public Hypothecaire(int numeroCompte, String codeClient, double soldeCompte) {
        super(numeroCompte, codeClient, soldeCompte);
    }
    
    /**
     * Prelever un montant specifique
     * @param montant 
     * @throws backend.PreleverMontantException 
     */
    public void preleverMontantHypotheque(double montant) throws PreleverMontantException{
        // Si le solde est inferieur au montant, on leve un exception prelever montant. elle vas être utilisée dans le
        // gestionnaire pour trouver si un compte marge est associé à tout ça ou non...
        if(getSolde() < montant){
            throw new PreleverMontantException();
        }else{// si le montant est suorérieur ou égale, on le prélève sans soucis
           setSolde(getSolde() - montant);
        }
    }
    
    @Override
    public String toString() {
        return "Hypotheque: " +super.toString();
    }
}