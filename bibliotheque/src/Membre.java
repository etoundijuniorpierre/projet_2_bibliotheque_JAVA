import java.time.LocalDate;
import java.util.ArrayList;

public class Membre {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private LocalDate adhesionDate;

    public Membre(int id, String nom, String prenom, String email, String telephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.adhesionDate = LocalDate.now();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public LocalDate getAdhesionDate() {
        return adhesionDate;
    }
    public void setAdhesionDate(LocalDate adhesionDate) {
        this.adhesionDate = adhesionDate;
    }


    // Méthodes pour ajouter, modifier et supprimer un membre
    public static void ajouterMembre(ArrayList<Membre> membres, Membre membre) {
        membres.add(membre);
    }

    public static void modifierMembre(ArrayList<Membre> membres, int id, String nom, String prenom, String email, String telephone, LocalDate adhesionDate) {
        for (Membre membre : membres) {
            if (membre.getId() == id) {
                membre.setNom(nom);
                membre.setPrenom(prenom);
                membre.setEmail(email);
                membre.setTelephone(telephone);
                membre.setAdhesionDate(adhesionDate);
            }
        }
    }

    public static void supprimerMembre(ArrayList<Membre> membres, int id) {
        membres.removeIf(membre -> membre.getId() == id);
    }
}
