import java.time.LocalDate;
import java.util.ArrayList;

//classe Livre qui va gérer l
public class Livre {
    private int id;
    private String isbn;
    private String titre;
    private String auteur;
    private String nationaliteAuteur;
    private String categorie;
    private LocalDate dateParution;
    private int nombreExemplaires;

    public Livre(String isbn) {
        this.id = 0;
        this.isbn = String.valueOf(0);
        this.titre = "";
        this.auteur = "";
        this.nationaliteAuteur = "";
        this.categorie = "";
        this.dateParution = LocalDate.now();
        this.nombreExemplaires = 0;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = String.valueOf(isbn);
    }
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public String getAuteur() {
        return auteur;
    }
    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
    public String getNationaliteAuteur() {
        return nationaliteAuteur;
    }
    public void setNationaliteAuteur(String nationaliteAuteur) {
        this.nationaliteAuteur = nationaliteAuteur;
    }
    public String getCategorie() {
        return categorie;
    }
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    public LocalDate getDateParution() {
        return dateParution;
    }
    public void setDateParution(LocalDate dateParution) {
        this.dateParution = dateParution;
    }
    public int getNombreExemplaires() {
        return nombreExemplaires;
    }
    public void setNombreExemplaires(int nombreExemplaires) {
        this.nombreExemplaires = nombreExemplaires;
    }


    // Méthodes pour ajouter, modifier et supprimer un livre
    public static void ajouterLivre(ArrayList<Livre> livres, Livre livre) {
        livres.add(livre);
    }

    public static void modifierLivre(ArrayList<Livre> livres, int id, String isbn ,String titre, String auteur, String nationaliteAuteur, String categorie, LocalDate dateParution, int nombreExemplaires) {
        for (Livre livre : livres) {
            if (livre.getId() == id) {
                livre.setIsbn(String.valueOf(Integer.parseInt(isbn)));
                livre.setTitre(titre);
                livre.setAuteur(auteur);
                livre.setNationaliteAuteur(nationaliteAuteur);
                livre.setCategorie(categorie);
                livre.setDateParution(dateParution);
                livre.setNombreExemplaires(nombreExemplaires);
            }
        }
    }

    public static void supprimerLivre(ArrayList<Livre> livres, int id) {
        livres.removeIf(livre -> livre.getId() == id);
    }

}


