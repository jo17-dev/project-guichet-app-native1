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

    // Constructeur
    public Client(String codeClient, String nom, String prenom, String telephone, String courriel, int numeroNIP){
        this.codeClient = codeClient;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.courriel = courriel;
        this.numeroNIP = numeroNIP;
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

    // toString
    @Override
    public String toString() {
        return "Nom : " + nom + ", Prenom : " +  prenom + ", Numéro de téléphone :  " + telephone + ", Adresse Courriel : " + courriel + ", Code NIP : " + numeroNIP;
    }
}