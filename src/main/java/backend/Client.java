    
package backend;

/**
 * Client
 */
public class Client {
    private String codeClient;
    private String nom;
    private String prenom;
    private String telephone;
    private String courriel;
    private int numeroNIP;
    private boolean estAdmin;
    private static int nbreClients = 0;

    // Constructeur ( Pour creer un client normal)
    public Client(String codeClient, String nom, String prenom, String telephone, String courriel, int numeroNIP){
        this.codeClient = codeClient;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.courriel = courriel;
        this.numeroNIP = numeroNIP;
        nbreClients++;
        estAdmin = false;
    }

    // Constructeur ( Pour crer un client ou un Admin, dépendament du dernier parametre )
    public Client(String codeClient, String nom, String prenom, String telephone, String courriel, int numeroNIP, boolean estAdmin){
        this.codeClient = codeClient;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.courriel = courriel;
        this.numeroNIP = numeroNIP;
        this.estAdmin = estAdmin;
        nbreClients++;
    }

    // Getters

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getCodeClient() {
        return codeClient;
    }
    
    public String getCouriel(){
        return courriel;
    }
    
    public static int getNbreClients(){
        return nbreClients;
    }

    // checkNIP
    
    public boolean checkNIP(int numeroNIP) {
        return (this.numeroNIP == numeroNIP);
    }

    public boolean checkAdmin(){
        return estAdmin;
    }

    // toString
    @Override
    public String toString() {
        return "Code Client: " + codeClient + ", Nom : " + nom + ", Prenom : " +  prenom + ", Numéro de téléphone :  " + telephone + ", Adresse Courriel : " + courriel + ", Code NIP : " + numeroNIP;
    }
}