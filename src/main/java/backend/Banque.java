package backend;

public class Banque extends Compte {
//    private double montantRemplissage; Cette variable ne vas pas être utilisée car super.solde vas remplacer le solde courant
    private final double montantMaximum = 20000;
    

    // Constructeur
    public Banque(int numeroCompte, String codeClient, double soldeCompte) {
        super(numeroCompte, codeClient, soldeCompte);
    }

    // Remplir Guichet 
    public void remplirGuichet(double montantRemplissage) throws RemplirGuichetException{
        
        if(montantRemplissage< 0){
            System.out.println("Echec. Le montant doit être négatif");
            throw new RemplirGuichetException("Echec. Le montant doit ne doit pas être négatif");
        }else if (getSolde() + montantRemplissage > montantMaximum )  {
            System.out.println("Echec. Le montant dépasse le montant maximum");
            throw new RemplirGuichetException("Echec. Le montant dépasse le montant maximum");
        }
        else {
            setSolde(getSolde()+montantRemplissage);
        }
    }

    // toString 
    @Override
    public String toString() {
        return "banque;  Montant Maximum "+ montantMaximum +"Montant Remplissage : " + getSolde();
    }
}
