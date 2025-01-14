import java.sql.SQLException;

public class RequeteContainer {

    //afficher les penalites
    public static String afficherPenalite(){
        return "SELECT membres.nom AS NomEmprunteur, membres.prenom AS PrenomEmprunteur, livres.titre AS LivreEmprunte, penalites.montant AS MontantPenalite FROM penalites JOIN emprunts ON penalites.empruntId = emprunts.empruntId JOIN membres ON emprunts.membreId = membres.id JOIN livres ON emprunts.livreId = livres.id";
    }

    //afficher les emprunts encours
    public static String afficherEmpruntEncours(){
        return "SELECT " +
                "e.empruntId AS id, " +
                "m.nom AS NomEmprunteur, " +
                "m.prenom AS PrenomEmprunteur, " +
                "l.titre AS LivreEmprunte, " +
                "e.dateEmprunt AS DateEmprunt, " +
                "e.dateRetourPrevue AS DateRetourPrevue " +
                "FROM Emprunts e JOIN Membres m ON e.membreId = m.id " +
                "JOIN Livres l ON e.livreId = l.id WHERE e.dateRetourEffective IS NULL";
    }

    //afficher les emprunts
    public static String afficherEmpruntsRetournes() {
        return "SELECT " +
                "    e.empruntId AS id, " +
                "    m.nom AS NomEmprunteur, " +
                "    m.prenom AS PrenomEmprunteur, " +
                "    l.titre AS LivreEmprunte, " +
                "    e.dateEmprunt AS DateEmprunt, " +
                "    e.dateRetourPrevue AS DateRetourPrevue, " +
                "    e.dateRetourEffective AS DateRetourEffective, " +
                "    CASE " +
                "        WHEN e.dateRetourEffective > e.dateRetourPrevue THEN 'Oui' " +
                "        ELSE 'Non' " +
                "    END AS PenaliteEffective " +
                "FROM Emprunts e " +
                "JOIN Membres m ON e.membreId = m.id " +
                "JOIN Livres l ON e.livreId = l.id " +
                "WHERE e.dateRetourEffective IS NOT NULL";
    }


    //afficher tous les elements d'une table
    public static String afficherAllElem(String elem) throws SQLException {
        // Exécuter la requête SELECT pour afficher la liste des livres
        return "SELECT * FROM " + elem;
    }

    //chercher un éléments
    public static <V> String searchElem(String tableName, String columnName, V value) {
        // Ajouter une conversion explicite si nécessaire (CAST pour BIGINT)
        if ("id".equalsIgnoreCase(columnName)) {
            return "SELECT * FROM " + tableName + " WHERE " + columnName + " = " + value;
        } else {
            return "SELECT * FROM " + tableName + " WHERE LOWER(" + columnName + ") LIKE LOWER('%" + value.toString() + "%')";
        }
    }

    //supprimer un éléments
    public static <V> String deleteElem(String table, String column, V value) {
        if ("id".equalsIgnoreCase(column)) {
            // Suppression basée sur des colonnes numériques
            return "DELETE FROM " + table + " WHERE " + column + " = " + value;
        } else {
            // Suppression basée sur des colonnes de type chaîne
            return "DELETE FROM " + table + " WHERE LOWER(CAST(" + column + " AS TEXT)) LIKE LOWER('%" + value.toString() + "%')";
        }
    }

    //ajouter d'un livre
    public static String insertLivre(long val1, String val2, String val3, String val4, String val5, String val6, int val7) {
        return "INSERT INTO livres (isbn, titre, auteur, nationaliteauteur, categorie, dateParution, nombreExemplaires) VALUES ("
                + val1 + ", '"
                + val2 + "', '"
                + val3 + "', '"
                + val4 + "', '"
                + val5 + "', '"
                + val6 + "', "
                + val7 + ")";
    }

    //ajout d'un membre
    public static <D> String insertMembre(String nom, String prenom, String email, String telephone) {
        return "INSERT INTO membres (nom, prenom, email, telephone, adhesiondate) VALUES ('"
                + nom + "', '"
                + prenom + "', '"
                + email + "', '"
                + telephone + "', CURRENT_DATE)";
    }

    //modifier elem
    public static <V, T> String updateElem(String table, String columnModif, String columnSearch, T newVal, V searchVal) {
        if ("id".equalsIgnoreCase(columnSearch)) {
            // Cas pour ID (valeur numérique)
            return "UPDATE " + table + " SET " + columnModif + " = " + "'"+newVal+"'" + " WHERE " + columnSearch + " = " + "'" + searchVal + "'";
        } else {
            // Cas pour texte (texte insensible à la casse)
            return "UPDATE " + table + " SET " + columnModif + " = '" + newVal + "' WHERE LOWER(" + columnSearch + ") LIKE LOWER('%" + searchVal + "%')";
        }

    }

    //retourner un emprunt
    public static <v> String retournerEmprunt(v id){
        return "UPDATE emprunts SET dateretoureffective = CURRENT_DATE WHERE empruntId = " + "'" + id + "'";
    }

    //ajouter un emprunt
    public static String ajouterEmprunt(String nom, String prenom, String titreORisbn) {
        return "INSERT INTO Emprunts (membreId, livreId, dateEmprunt, dateRetourPrevue) " +
                "VALUES ( " +
                "(SELECT id FROM Membres " +
                "WHERE LOWER(Nom) LIKE LOWER('%" + nom + "%') " +
                "AND LOWER(Prenom) LIKE LOWER('%" + prenom + "%') LIMIT 1), " +
                "(SELECT id FROM Livres " +
                "WHERE LOWER(titre) LIKE LOWER('%" + titreORisbn + "%') " +
                "OR isbn = '" + titreORisbn + "' LIMIT 1), " +
                "CURRENT_DATE, CURRENT_DATE + INTERVAL '14 days')";
    }


    //calcule automatique des pénalités
    public static String calculPenalites() {
        return "SELECT ROW_NUMBER() " +
                "OVER " +
                "(ORDER BY membreId, livreId) " +
                "AS empruntId, (date(dateRetourEffective) - date(dateRetourPrevue)) * 100 " +
                "AS montant FROM Emprunts " +
                "WHERE dateRetourEffective IS NOT NULL " +
                "AND " +
                "dateRetourEffective > dateRetourPrevue;";
    }

}




