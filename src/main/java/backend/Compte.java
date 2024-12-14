package backend;

public class Compte {
    private int numeroCompte; // numeto de ce compte parmis les autres comptes
    private String codeClient; // code Client du propriétaire du compte
    private double soldeCompte; // solde courant du compte
    private final double retraitMaximum = 1000; // retraitMaximum d'un compte par transaction ( 1000 $)
    private final double montantTransfertMaximum = 1000; // montant de transfert max d'un compte à un autre: 1000$ ? nécéssaire ?
    private static int nbreComptes = 0; // nombre de comptes présents

    public Compte(int numeroCompte, String codeClient, double soldeCompte) {
        this.numeroCompte = numeroCompte;
        this.codeClient = codeClient;
        this.soldeCompte = soldeCompte;
        nbreComptes++;
    }

    public void retrait(double montant) throws PreleverMontantException{
        System.out.println("__Ancien solde " + getSolde());
        if(montant > retraitMaximum){
//            System.out.println("Impossible de retirer les montant de plus de 1000 $");
            throw new PreleverMontantException("Impossible de retirer les montant de plus de 1000 $");
        }else if(montant % 10 != 0){
            throw new PreleverMontantException("Le montant de retrait doit etre un multiple de 10");
        }
        else if(montant <= soldeCompte){
            soldeCompte -= montant;
            System.out.println("effectuasion du resultat");
        }
        System.out.println("__Nouveau solde " + getSolde());
    }

    public void depot(double montant){
        if(montant > 0){
            soldeCompte += montant;
        }else{
            System.out.println("Impossible de faire le dépot d'un montant négatif");;
        }
    }

    public String getCodeClient(){
        return codeClient;
    }

    public int getNumeroCompte(){
        return numeroCompte;
    }

    public double getSolde(){
        return soldeCompte;
    }
    
    public static int getNbreComptes(){
        return nbreComptes;
    }

    public void setSolde(double newSolde){
        soldeCompte = newSolde;
    }

    public double getmontantTransfertMaximum(){
        return montantTransfertMaximum;
    }

    public String toString(){
        return "Compte ( NumeroCompte: "+ numeroCompte + ", codeClient: " + codeClient + ", soldeCompte: "+ soldeCompte + ", retraitMaximum: "+ retraitMaximum + ", montantTransfertMaximum: " + montantTransfertMaximum + " )" ;
    }
}
