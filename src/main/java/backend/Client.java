    
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
    
    // ces attributs n'ont vraiment pas besoin d'être privés
    public USER_ROLE userRole;
    public USER_STATUS userStatus;
    
    // enum pour connaître le statut de connexion d'un user
    public static enum USER_STATUS {
      LOGGED_IN,
      LOGGED_OUT,
    };
    
    // this enum est le role de l'utilisateur actuelement connecté.
    // seras utilisé pour authentifier acceder au role de celui qui c'est authentifié
    
    public static enum USER_ROLE {
      ADMIN, // role de l'admin
      CLIENT,
      DEFAULT
    };

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
        
        this.userRole = USER_ROLE.CLIENT;
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
        
        this.userRole = estAdmin ? USER_ROLE.ADMIN : USER_ROLE.CLIENT;
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
    
    public String getTelephone(){
        return telephone;
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