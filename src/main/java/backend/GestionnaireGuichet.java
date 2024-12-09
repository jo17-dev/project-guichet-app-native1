package backend;

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
    }

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

    // retrait du compte cheque
    /*
     * pour ça
     * 1- on cherche le compte cheque coresspondant
     */
    public void retraitCheque(int codeClient, double montant){
        for(Cheque item : comptesCheque){
            if(item.getCodeClient() == codeClient){
                item.retrait(montant);
                transactions.add( new Transaction(Transaction.nbreTransaction, montant, item, null, "retrait"));
                break;
            }
        }
    }

    public void retraitEpargne(int codeClient, int numeroCompte ,double montant){
        for(Epargne item : comptesEpargne){
            if(item.getCodeClient() == codeClient && item.getNumeroCompte() == numeroCompte){
                item.retrait(montant);
                transactions.add( new Transaction(Transaction.nbreTransaction, montant, item, null, "retrait"));
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
                transactions.add( new Transaction(Transaction.nbreTransaction, montant, item, null, "depot"));
                break;
            }
        }
    }

    
    public void depotEpargne(int codeClient, int numeroCompte ,double montant){
        for(Epargne item : comptesEpargne){
            if(item.getCodeClient() == codeClient && item.getNumeroCompte() == numeroCompte){
                item.depot(montant);
                transactions.add( new Transaction(Transaction.nbreTransaction, montant, item, null, "depot"));
                break;
            }else{
                System.out.println("On a pas trouvé le compte");
            }
        }
    }

    /*paiement de facture d'un compte cheque vers un autre de compte de numée "numeroCompte" montant */
    public void paiementFacture(int codeClient, int numeroCompte, double montant){ 
        for(Cheque item : comptesCheque){
            if(item.getCodeClient() == codeClient && item.getNumeroCompte() == numeroCompte){
                item.paiementFacture(montant);
                transactions.add( new Transaction(Transaction.nbreTransaction, montant, item, banque, "paiement")); // le paiement de facture se fait à la banque pour que'elle traite de sont cotéavac les partenaires
                break;
            }else{
                System.out.println("On a pas trouvé le client");
            }
        }
    }

    public void transfertFonds(int codeClient, double montant, String typeCompte, int numeroCompteDestination){
        Cheque compteDepart = null;
        for(Cheque item : comptesCheque){
            if(item.getCodeClient() == codeClient){
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
    public void creerClient(String codeClient, String nom, String prenom, String telephone, String courriel, int numeroNIP){
        Client tmp = new Client(codeClient, nom, prenom, telephone, courriel, numeroNIP, false);
        clients.add(tmp);
    }

    // en supposant que c'est une methode admin, le compte crée doit être Cheque tandis que les autres comptes comptes seont crée au login des client eux meme
    public void creerCompte(int numeroCompte, int codeClient, double soldeCompte, double retraitMaximum, double montantTransfertMaximum){
        Cheque tmp = new Cheque(numeroCompte, codeClient, soldeCompte, retraitMaximum, montantTransfertMaximum);
        comptesCheque.add(tmp);
    }

    
    public void preleverMontantdeHypothecaire(int numeroCompteDepart, int codeClient, double montantAPrelever){
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
                            return ;
                        }
                    }
                    System.out.println("On a pas trouve de compte marge à votre nom");
                    return ;
                }
            }
        }
    }
    
}