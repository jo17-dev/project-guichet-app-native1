import java.util.ArrayList;

public class GestionnaireGuichet {
    private Compte banque;
    private ArrayList<Client> clients;
    private ArrayList<Cheque> comptesCheque;
    private ArrayList<Epargne> comptesEpargne;
    private ArrayList<Marge> comptesMarge;
    private ArrayList<Hypothecaire> comHypothecaire;
    private ArrayList<Transaction> transactions;
    private double soldeCompteCourant;

    private int nbreTentative;

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
    }

    // valider utilisateur
    public Client validerUtilisateur(String codeClient, int nip){
        nbreTentative++;
        for(Client item : clients){
            if(item.getCodeClient().equals(codeClient) && item.checkNIP(nip)){
                nbreTentative = 0;
                return item;
            }
        }

        return null;
    }

    // retrait du compte cheque
    /*
     * pour ça
     * 1- on cherche le compte cheque coresspondant
     */
    public void retraitCheque(int codeClient, double montant){
        for(Cheque item : comptesCheque){
            if(item.getCodeClient() == codeClient){
                item.retrait(montant);
                break;
            }
        }
    }

    public void retraitEpargne(int codeClient, int numeroCompte ,double montant){
        for(Epargne item : comptesEpargne){
            if(item.getCodeClient() == codeClient && item.getNumeroCompte() == numeroCompte){
                item.retrait(montant);
                break;
            }else{
                System.out.println("On a pas trouve le compte correspondant");
            }
        }
    }

    public void depotCheque(int codeClient, double montant){
        for(Cheque item : comptesCheque){
            if(item.getCodeClient() == codeClient){
                item.depot(montant);
                break;
            }
        }
    }

    
    public void depotEpargne(int codeClient, int numeroCompte ,double montant){
        for(Epargne item : comptesEpargne){
            if(item.getCodeClient() == codeClient && item.getNumeroCompte() == numeroCompte){
                item.depot(montant);
                break;
            }else{
                System.out.println("On a pas trouvé le compte");
            }
        }
    }

    /*paiement de facture d'un compte cheque vers un autre de compte de numée "numeroCompte" montant */
    public void paiementFacture(int codeClient, int numeroCompte, double montant){ // je pense que numeroCompte n'est pas nécéssaire.. par ce que tout paiement de facture se fait via le compte cheque dans se cas
        for(Cheque item : comptesCheque){ // de toute façon on boucle sur les compte cheque soo..
            if(item.getCodeClient() == codeClient && item.getNumeroCompte() == numeroCompte){
                item.paiementFacture(montant);
                break;
            }else{
                System.out.println("On a pas trouvé le client");
            }
        }
    }

    public void transfertFonds(int codeClient, double montant, String typeCompte, int numeroCompteDestination){
        // 1st step find the current cheque Account
        Cheque compteDepart = null;
        for(Cheque item : comptesCheque){
            if(item.getCodeClient() == codeClient){
                compteDepart = item;
            }
        }

        if(compteDepart == null){
            System.out.println("le compte n'a pas ete trouve");
            return ;
        }

        switch (typeCompte) {
            case "epargne":
                for(Epargne item : comptesEpargne){
                    if(item.getNumeroCompte() == numeroCompteDestination){
                        compteDepart.setSolde(compteDepart.getSolde() - montant);
                        item.setSolde(item.getSolde() + montant);
                        return ;
                    }
                }
                break;
            case "marge":
                for(Marge item : comptesMarge){
                    if(item.getNumeroCompte() == numeroCompteDestination){
                        compteDepart.setSolde(compteDepart.getSolde() - montant);
                        item.setSolde(item.getSolde() + montant);
                        return ;
                    }
                }
                break;
            case "hypothecaire":
            for(Hypothecaire item : comHypothecaire){
                if(item.getNumeroCompte() == numeroCompteDestination){
                    compteDepart.setSolde(compteDepart.getSolde() - montant);
                    item.setSolde(item.getSolde() + montant);
                    return ;
                }
            }
                break;
            default:
                System.out.println("typde de compte non pris en charge pour les transfert");
                break;
        }
    }

    public void afficheSoldeCompte(){
        System.out.println(soldeCompteCourant);
    }

    // creer client est une methode destiné à l'admin uniquement. il faudrai donc trouver un moyen de l'authenfier
    public void creerClient(String codeClient, String nom, String prenom, String telephone, String courriel, int numeroNIP){
        Client tmp = new Client(codeClient, nom, prenom, telephone, courriel, numeroNIP);
        clients.add(tmp);
    }

    // en supposant que c'est une methode admin, le compte crée doit être Cheque tandis que les autres comptes comptes seont crée au login des client eux meme
    public void creerCompte(int numeroCompte, int codeClient, double soldeCompte, double retraitMaximum, double montantTransfertMaximum){
        Cheque tmp = new Cheque(numeroCompte, codeClient, soldeCompte, retraitMaximum, montantTransfertMaximum);
        comptesCheque.add(tmp);
    }
    
}