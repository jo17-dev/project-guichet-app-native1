package backend;

import java.util.ArrayList;

public class GestionnaireGuichet {
    private Compte banque;
    private ArrayList<Client> clients;
    private ArrayList<Client> listeNoireClients;// liste des clients bloqués ( dont on a interdit l'acces )
    private ArrayList<Cheque> comptesCheque;
    private ArrayList<Epargne> comptesEpargne;
    private ArrayList<Marge> comptesMarge;
    private ArrayList<Hypothecaire> comHypothecaire;
    private ArrayList<Transaction> transactions;
    private double soldeCompteCourant;

    private int nbreTentative;
    private int nbreTentativeMax=3;

    // contructeur
    public GestionnaireGuichet(Compte banque, ArrayList<Client> clients, ArrayList<Cheque> comptesCheque,
                               ArrayList<Epargne> comptesEpargne, ArrayList<Marge> comptesMarge,
                               ArrayList<Hypothecaire> comHypothecaire, ArrayList<Transaction> transactions,
                               double soldeCompteCourant) {
        this.banque = banque;
        this.clients = clients;
        this.comptesCheque = comptesCheque;
        this.comptesEpargne = comptesEpargne;
        this.comptesMarge = comptesMarge;
        this.comHypothecaire = comHypothecaire;
        this.transactions = transactions;
        this.soldeCompteCourant = soldeCompteCourant;
        nbreTentative = 0;
        
        this.listeNoireClients = new ArrayList<>();
    }

    // retrait du compte cheque
    /*
     * pour ça
     * 1- on cherche le compte cheque coresspondant
     */
    public void retraitCheque(String codeClient, double montant){
        for(Cheque item : comptesCheque){
            if(codeClient.equals(item.getCodeClient())){
                item.retrait(montant);
                transactions.add( new Transaction(Transaction.nbreTransaction, montant, item, null, "retrait"));
                break;
            }
        }
    }
    
    public Compte getCompteBancaire(){
        return banque;
    }

    public void retraitEpargne(String codeClient, int numeroCompte ,double montant){
        for(Epargne item : comptesEpargne){
            if( codeClient.equals(item.getCodeClient()) && item.getNumeroCompte() == numeroCompte){
                item.retrait(montant);
                transactions.add( new Transaction(Transaction.nbreTransaction, montant, item, null, "retrait"));
                break;
            }else{
                System.out.println("On a pas trouve le compte correspondant");
            }
        }
    }

    public void depotCheque(String codeClient, double montant){
        for(Cheque item : comptesCheque){
            if(codeClient.equals(item.getCodeClient())){
                item.depot(montant);
                transactions.add( new Transaction(Transaction.nbreTransaction, montant, item, null, "dépot"));
                break;
            }
        }
    }
    
  public void payerInterets(){
      for(Epargne item : comptesEpargne){
        if(item.getSolde() > 0){
            double montantPaye = item.paiementInteret();
            transactions.add(new Transaction(Transaction.nbreTransaction, montantPaye, banque, item, "paiement" ));
        }
      }
  }

    
    public void depotEpargne(String codeClient, int numeroCompte ,double montant){
        for(Epargne item : comptesEpargne){
            if( codeClient.equals(item.getCodeClient()) && item.getNumeroCompte() == numeroCompte){
                item.depot(montant);
                transactions.add( new Transaction(Transaction.nbreTransaction, montant, item, null, "depot"));
                break;
            }else{
                System.out.println("On a pas trouvé le compte");
            }
        }
    }

    /*paiement de facture d'un compte cheque vers un autre de compte de numée "numeroCompte" montant */
    public void paiementFacture(String codeClient, int numeroCompte, double montant){ 
        for(Cheque item : comptesCheque){
            if(codeClient.equals(item.getCodeClient()) && item.getNumeroCompte() == numeroCompte){
                item.paiementFacture(montant);
                transactions.add( new Transaction(Transaction.nbreTransaction, montant, item, banque, "paiement")); // le paiement de facture se fait à la banque pour que'elle traite de sont cotéavac les partenaires
                break;
            }else{
                System.out.println("On a pas trouvé le client");
            }
        }
    }

    public void transfertFonds(String codeClient, double montant, String typeCompte, int numeroCompteDestination){
        Cheque compteDepart = null;
        for(Cheque item : comptesCheque){
            if(codeClient.equals(item.getCodeClient())){
                compteDepart = item;
            }
        }
        
        if(compteDepart == null){
            System.out.println("votre compte cheque n'a pas ete trouve");
            return ;
        }

        switch (typeCompte.toLowerCase()) {
            case "epargne":
                for(Epargne item : comptesEpargne){
                    if(item.getNumeroCompte() == numeroCompteDestination){
                        compteDepart.setSolde(compteDepart.getSolde() - montant);
                        item.setSolde(item.getSolde() + montant);
                        transactions.add( new Transaction(Transaction.nbreTransaction, montant, compteDepart, item, "transfert"));
                        return ;
                    }
                }
                break;
            case "marge":
                for(Marge item : comptesMarge){
                    if(item.getNumeroCompte() == numeroCompteDestination){
                        compteDepart.setSolde(compteDepart.getSolde() - montant);
                        item.setSolde(item.getSolde() + montant);
                        transactions.add( new Transaction(Transaction.nbreTransaction, montant, compteDepart, item, "transfert"));
                        return ;
                    }
                }
                break;
            case "hypothecaire":
            for(Hypothecaire item : comHypothecaire){
                if(item.getNumeroCompte() == numeroCompteDestination){
                    compteDepart.setSolde(compteDepart.getSolde() - montant);
                    item.setSolde(item.getSolde() + montant);
                    transactions.add( new Transaction(Transaction.nbreTransaction, montant, compteDepart, item, "transfert"));
                    return ;
                }
            }
                break;
            default:
                System.out.println("type de compte non pris en charge pour les transfert");
                break;
        }
    }

    public double afficheSoldeCompte(){
        return soldeCompteCourant;
    }

    // creer client est une methode destiné à l'admin uniquement. il faudrai donc trouver un moyen de l'authenfier
    // On pourrai utiliser la methode checkAdmin de l'object client qui vas initier cette methode avant son appel
    public void creerClient(String codeClient, String nom, String prenom, String telephone, String courriel, int numeroNIP, boolean estAdmin){
        Client tmp = new Client(codeClient, nom, prenom, telephone, courriel, numeroNIP, estAdmin);
        clients.add(tmp);
        // ensuite on le crée son compte obligatoire: (cheque)
        creerCompte(Compte.getNbreComptes(), codeClient, 0);
    }

    // creer un Compte de n'importe quel type
    public void creerCompte(int numeroCompte, String codeClient, double soldeCompte){
        Cheque tmp = new Cheque(numeroCompte, codeClient, soldeCompte);
        comptesCheque.add(tmp);
    }
    
    public void creerCompte(int numeroCompte, String codeClient, double soldeCompte, String type){
        switch(type){
            case "cheque":
                comptesCheque.add(new Cheque(numeroCompte, codeClient, soldeCompte));
                break;
            case "epargne":
                comptesEpargne.add(new Epargne(numeroCompte, codeClient, soldeCompte));
                break;
            case "marge":
                comptesMarge.add(new Marge(numeroCompte, codeClient, soldeCompte));
                break;
            case "hypothecaire":
                comHypothecaire.add(new Hypothecaire(numeroCompte, codeClient, soldeCompte));
                break;
            default:
                System.out.println("On a pas pu creer de compte .. le type de corresponds pas..");
                break;
        }
    }

    
    public void preleverMontantdeHypothecaire(int numeroCompteDepart, int codeClient, double montantAPrelever) throws PreleverMontantException{
        for(Hypothecaire item : comHypothecaire){
            if(numeroCompteDepart == item.getNumeroCompte()){
                if(item.getSolde() < montantAPrelever){
                    System.out.println("Montant insufisant. tentative de prelevement dans le compte marge");
                    double montantCourant = item.getSolde();
                    // recherche du compte marge
                    for(Marge cpt : comptesMarge){
                        if(cpt.getNumeroCompte() == numeroCompteDepart){
                            // si on trouve le compte marge
                            cpt.augumenterSoldeMarge(montantAPrelever - montantCourant);
                            item.setSolde(0);
                            System.out.println("prelevement du compte marge. réussi. transaction faite");
                            throw new PreleverMontantException("Notez qu'une partie de l'argent a été prelevé dans le compte marge du client");
                        }
                    }
                    System.out.println("On a pas trouve de compte marge à votre nom");
                    throw new PreleverMontantException("Echec. le virement n'as pas pu être effectué; le solde est insufisant");
                }else{ // si tout est bon pour le prelevement
                    item.setSolde(item.getSolde() - montantAPrelever);
                    System.out.println("prelevement reussi.. le solde du compte marge n'as pas été touché");
                }
            }
        }
    }
    
    public void augmenterSoldeMarges(){
        for(Marge item: comptesMarge){
            item.augumenterSoldeMarge();
        }
    }
    
    /**
     * Ajouter un client à la liste noire:
     * pour ça, on l'ajoute s'il nexiste pas déjas
     * @param target
     * @return Boolean
     */
    public boolean bloquerClient(Client target){
        for(Client item : listeNoireClients){
            if(item.getCodeClient().equals(target.getCodeClient())){
                return false;
            }
        }
        listeNoireClients.add(target);
        return true;
    }
    
    /**
    * Ajouter un client à la liste noire:
    * pour ça, on l'ajoute s'il nexiste pas déjas
    * @param target
    * @return Boolean
    */
    public boolean debloquerClient(Client target){
        boolean foundOnBlackList = false;
        for(Client item : listeNoireClients){
            if(item.getCodeClient().equals(target.getCodeClient())){
                foundOnBlackList = true;
            }
        }
        
        if(foundOnBlackList){
            listeNoireClients.remove(target);
        }
      return false;
    }
    /**
     * check si un client existe via son email
     * @param email
     * @return 
     */
    public boolean emailExistant(String email){
        for(Client item : clients){
            if(item.getCouriel().equals(email)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * renvoie true si le client est bloqueé ( est dans la liste noir )
     * @param target
     * @return 
     */
    public boolean estBloque(Client target){
        return listeNoireClients.contains(target);
    }
    
    // La suite ici devraient être dans une classe GestionnaireDAO mais bon... tout ça vas être maintenue après..
        // valider utilisateur
    public Client validerUtilisateur(String codeClient, int nip){
        nbreTentative++;
        if(nbreTentative > nbreTentativeMax){
            System.out.println("vous n'avez plus droit aux tentatives");
            return null;
        }
        for(Client item : clients){
            if(item.getCodeClient().equals(codeClient) && item.checkNIP(nip)){
                nbreTentative = 0;
                return item;
            }
        }
        System.out.println("Echec de l'authentification. " + (nbreTentative<nbreTentativeMax ? "Réessayez !" : " Vous avez épuiser les trois essais"));
        return null;
    }
    
    
    // get tous les clients
    public ArrayList<Client> getClients(){
        return clients;
    }
    
    // get tous les comptes appartenant à un seul client
    // on vas juste boucler sur tous les types de comptes
    public ArrayList<Compte> getComptesParClient(String codeClient){
        ArrayList<Compte> resultat = new ArrayList<>();
        // comptes cheque:
        for(Cheque cheque : comptesCheque ){
            if(cheque.getCodeClient().equals(codeClient)){
                resultat.add(cheque);
                break; // car on a un seul compte cheque non ? pas besoin d'en faire tout un plat avec les specs de la machine haha
            }
        }
        
        // comptes epargne:
        for(Epargne item : comptesEpargne ){
            if(item.getCodeClient().equals(codeClient)){
                resultat.add(item);
            }
        }
                
        // comptes hypothecaires:
        for(Hypothecaire item : comHypothecaire ){
            if(item.getCodeClient().equals(codeClient)){
                resultat.add(item);
            }
        }
        
        // comptes epargne:
        for(Marge item : comptesMarge ){
            if(item.getCodeClient().equals(codeClient)){
                resultat.add(item);
                break; // on a max un seul compte marge..
            }
        }
        
        
        return resultat;
    }
    
    public ArrayList<Transaction> getTransactionsParComptes(int numeroCompte){
        ArrayList<Transaction> result = new ArrayList<>();
        
        for(Transaction item : transactions){
            if(item.getNumeroCompteDepart() == numeroCompte || item.getNumeroCompteDestination() == numeroCompte){
                result.add(item);
            }
        }
        
        return result;
    }
    
    public Client chercherClientParEmail(String targetEmail){
        for(Client item : clients){
            if(item.getCouriel().equals(targetEmail)){
                return item;
            }
        }
        return null;
    }
    
    
}