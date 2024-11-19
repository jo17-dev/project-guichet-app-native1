/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

/**
 *
 * @author samsl
 */
public class GestionnaireGuichetTest {
    private GestionnaireGuichet gestionguichet;
    private Client client;
    private Compte compte;
    private Cheque compteCheque;
    private Epargne compteEpargne;
    private Marge compteMarge;
    private Hypothecaire compteHypothecaire;
    private ArrayList<Client> clients;
    private ArrayList<Cheque> comptesCheque;
    private ArrayList<Epargne> comptesEpargne;
    private ArrayList<Marge> comptesMarge;
    private ArrayList<Hypothecaire> comHypothecaire;
    private ArrayList<Transaction> transactions;
    private double soldeCompteCourant;

    @BeforeEach
    public void setUp() {
        client = new Client("12340", "Allag", "Sami", "514567890", "sami@gmail.com", 4567);
        compte = new Compte(1234, 123, 15000, 1000, 1000);
        compteCheque = new Cheque(1234, 123, 15000, 1000, 1000);
        compteEpargne = new Epargne(1234, 123, 15000, 1000, 1000, 20);
        compteMarge = new Marge(20, 1234, 123, 15000, 1000, 1000);
        compteHypothecaire = new Hypothecaire(1234, 123, 15000, 1000, 1000);
        soldeCompteCourant = 30000;
        
        clients = new ArrayList<>();
        comptesCheque = new ArrayList<>();
        comptesEpargne = new ArrayList<>();
        comptesMarge = new ArrayList<>();
        comHypothecaire = new ArrayList<>();
        transactions = new ArrayList<>();
        
        clients.add(client);
        comptesCheque.add(compteCheque);
        comptesEpargne.add(compteEpargne);
        comptesMarge.add(compteMarge);
        comHypothecaire.add(compteHypothecaire);
    }

    @AfterEach
    public void tearDown() {
        client = null;
        compte = null;
        compteCheque = null;
        compteEpargne = null;
        compteMarge = null;
        comHypothecaire = null;
        clients = null;
        comptesCheque = null;
        comptesEpargne = null;
        comptesMarge = null;
        compteHypothecaire = null;
        transactions = null;
    }
    
    public GestionnaireGuichetTest() {
    }

    @Test
    public void testValiderUtilisateur() {
        assertEquals(client, gestionguichet.validerUtilisateur("12340", 4567));
    }

    @Test
    public void testRetraitCheque() {
        gestionguichet.retraitCheque(123, 100);
        assertEquals(compteCheque.getSolde(), 14900);
    }

    @Test
    public void testRetraitEpargne() {
        gestionguichet.retraitEpargne(123, 1234, 100);
        assertEquals(compteEpargne.getSolde(), 14900);
    }

    @Test
    public void testDepotCheque() {
        gestionguichet.depotCheque(123, 100);
        assertEquals(compteCheque.getSolde(), 15100);
    }

    @Test
    public void testDepotEpargne() {
        gestionguichet.depotEpargne(123, 1234, 100);
        assertEquals(compteEpargne.getSolde(), 15100);
    }

    @Test
    public void testPaiementFacture() {
        gestionguichet.paiementFacture(123, 1234, 500);
        assertEquals(compteCheque.getSolde(), 14500);
    }

    @Test
    public void testTransfertFonds() {
        gestionguichet.transfertFonds(123, 3000, "Epargne", 1234);
        assertEquals(compteEpargne.getSolde(), 12000);
    }

    @Test
    public void testAfficheSoldeCompte() {
        assertEquals(gestionguichet.afficheSoldeCompte(), 30000);
    }

    @Test
    public void testCreerClient() {
        Client clientATrouver = null;
        Client client2 = new Client("12341", "Bertrand", "joel", "514567891", "joel@gmail.com", 4568);
        gestionguichet.creerClient("12341", "Bertrand", "joel", "514567891", "joel@gmail.com", 4568);
        for(int i = 0; i < clients.size(); i++) {
            if(clients.get(i).getCodeClient().equals("1234")){
                clientATrouver = clients.get(i);
                break;
            }
        }
        
        assertEquals(client2, clientATrouver);
        
    }

    @Test
    public void testCreerCompte() {
        Cheque compteatrouver = null;
        Cheque compte2 = new Cheque(1235, 124, 15000, 1000, 1000);
        gestionguichet.creerCompte(1235, 124, 15000, 1000, 1000);
        for(int i = 0; i < comptesCheque.size(); i++) {
            if(comptesCheque.get(i).equals(compte2)){
                compteatrouver = comptesCheque.get(i);
                break;
            }
        }
        assertEquals(compte2, compteatrouver);
        
    }

    @Test
    public void testPreleverMontantdeHypothecaire() {
        gestionguichet.preleverMontantdeHypothecaire(1234, 123, 1000);
        assertEquals(compteHypothecaire.getSolde(), 14000);
    }
    
}
