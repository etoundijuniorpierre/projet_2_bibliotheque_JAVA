import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprunt {
    private int idEmprunt;
    private Membre membre;               // Référence au membre ayant emprunté le livre
    private Livre livre;                 // Référence au livre emprunté
    private LocalDate dateEmprunt;       // Date d'emprunt
    private LocalDate dateRetourPrevue;  // Date de retour prévue
    private LocalDate dateRetourEffective; // Date de retour effective

    public Emprunt() {
        this.idEmprunt = 0;
        this.membre = null;
        this.livre = null;
        this.dateEmprunt = null;
        this.dateRetourPrevue = null;
        this.dateRetourEffective = null;
    }

    public int getIdEmprunt() {
        return idEmprunt;
    }
    public void setIdEmprunt(int idEmprunt) {
        this.idEmprunt = idEmprunt;
    }
    public Membre getMembre() {
        return membre;
    }
    public void setMembre(Membre membre) {
        this.membre = membre;
    }
    public Livre getLivre() {
        return livre;
    }
    public void setLivre(Livre livre) {
        this.livre = livre;
    }
    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }
    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }
    public LocalDate getDateRetourPrevue() {
        return dateRetourPrevue;
    }
    public void setDateRetourPrevue(LocalDate dateRetourPrevue) {
        this.dateRetourPrevue = dateRetourPrevue;
    }
    public LocalDate getDateRetourEffective() {
        return dateRetourEffective;
    }
    public void setDateRetourEffective(LocalDate dateRetourEffective) {
        this.dateRetourEffective = dateRetourEffective;
    }


    // Méthode pour enregistrer un emprunt
    public void enregistrerEmprunt(Membre membre, Livre livre, LocalDate dateEmprunt, LocalDate dateRetourPrevue) {
        this.membre = membre;
        this.livre = livre;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
        this.dateRetourEffective = null; // Initialement, il n'y a pas de retour
    }

    // Méthode pour gérer le retour d'un livre et enregistrer la date de retour
    public void enregistrerRetour(LocalDate dateRetourEffective) {
        this.dateRetourEffective = dateRetourEffective;
    }

    // Méthode pour calculer la pénalité en cas de retard
    public long calculerPenalite() {
        if (dateRetourEffective != null && dateRetourEffective.isAfter(dateRetourPrevue)) {
            long joursDeRetard = ChronoUnit.DAYS.between(dateRetourPrevue, dateRetourEffective);
            // return : 100 F CFA par jour de retard
            return joursDeRetard * 100;
        }
        return 0; // Pas de pénalité si le retour est dans les délais
    }

    // Méthode pour afficher les détails de l'emprunt
    public void afficherDetails() {
        System.out.println("Emprunt ID: " + idEmprunt + ", Livre: " + livre.getTitre() + ", Membre: " + membre.getNom() + " " + membre.getPrenom() +
                ", Date d'emprunt: " + dateEmprunt + ", Date retour prévue: " + dateRetourPrevue + ", Date retour effective: " + dateRetourEffective);
    }
}
