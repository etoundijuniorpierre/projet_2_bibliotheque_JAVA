import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EmpruntDAO {

    // Récupérer la connexion
    static Connection connection;

    static {
        try {
            connection = DBconnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Créer un Statement
    static Statement statement;

    static {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Menu général des emprunts et penalites
    static void menuEmpruntPenalite() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenue dans le menu de gestion des emprunts et pénalités");
        System.out.println("0. retour au menu Principal");
        System.out.println("1. Afficher la liste des emprunts");
        System.out.println("2. Ajouter un emprunt");
        System.out.println("3. Retourner un emprunt");
        System.out.println("4. calculer et Afficher la liste pénalites");
        System.out.println("5. Quitter");
        System.out.println("votre choix : ");

        int choice = scanner.nextInt();

        switch (choice) {
            case 0:
                FichierMenus.menuChoice();
                break;
            case 1:
                System.out.println("\n0. retour au menu Principal");
                System.out.println("1. Afficher les emprunts RETOURNES");
                System.out.println("2. Afficher les emprunts ENCOURS");
                System.out.println("votre choix : ");

                int choice1 = scanner.nextInt();
                switch (choice1) {
                    case 0:
                        FichierMenus.menuChoice();
                        break;
                        case 1:
                            System.out.println("Liste de toutes les emprunts RETOURNES\n :");
                            displayEmpruntRetourne();
                            break;
                            case 2:
                                System.out.println("Liste de toutes les emprunts ENCOURS\n :");
                                displayEmpruntEncours();
                                FichierMenus.postUpdateNavigation();
                                break;
                    default:
                        break;
                }
                break;
            case 2:
                System.out.println("\n MENU d'AJOUT des emprunts \n");
                System.out.println("saisir le nom de l'emprunteur ");
                String nom = scanner.next();
                System.out.println("saisir le prenom de l'emprunteur ");
                String prenom = scanner.next();
                scanner.nextLine();
                System.out.println("saisir le titre ou l'isbn du livre ");
                String isbn = scanner.nextLine();

                String updateQuery0 = RequeteContainer.ajouterEmprunt(nom, prenom, isbn);
                int rowsUpdated0 = statement.executeUpdate(updateQuery0);
                if (rowsUpdated0 > 0) {
                    System.out.println("ajout réussie !");
                } else {
                    System.out.println("Aucune ligne ajoutée.");
                }
                System.out.println("Requête exécutée : " + updateQuery0);
                FichierMenus.postUpdateNavigation();

                break;
            case 3:
                System.out.println("\n MENU de RETOUR des emprunts ");
                System.out.println("la liste des emprunts ENCOURS... ");
                displayEmpruntEncours();
                System.out.println("\n saisir l'id de emprunt à retourné");
                System.out.println("saisir : ");
                int choice2 = scanner.nextInt();

                System.out.println("confirmer l'opération oui / non");
                String choice3 = scanner.next();

                if (choice3.equalsIgnoreCase("oui")) {
                    String updateQuery = RequeteContainer.retournerEmprunt(choice2);
                    int rowsUpdated1 = statement.executeUpdate(updateQuery);
                    if (rowsUpdated1 > 0) {
                        System.out.println("Modification réussie !");
                    } else {
                        System.out.println("Aucune ligne mise à jour.");
                    }
                    System.out.println("Requête exécutée : " + updateQuery);
                    FichierMenus.postUpdateNavigation();
                }else {
                    FichierMenus.postUpdateNavigation();
                }

                break;
            case 4:
                System.out.println("\n calcule automatique des pénalités effectués \n");
                RequeteContainer.calculPenalites();
                System.out.println("liste des pénalités : ");
                displayPenalite();
                break;
            case 5:
                FichierMenus.end();
                System.exit(0);
                break;
            default:
                System.out.println("choix invalide... retour au menu principal");
                FichierMenus.menuChoice();
        }

    }


    //méthode pour afficher les pénalités
    public static void displayPenalite() throws SQLException {
        // Exécuter une requête pour afficher les pénalités
        String display = RequeteContainer.afficherPenalite(); // Assurez-vous que cette méthode génère la bonne requête
        ResultSet resultSet = statement.executeQuery(display);

        // Afficher l'en-tête du tableau
        String format = "| %-18s | %-18s | %-40s | %-18s |\n";
        System.out.format("+--------------------+--------------------+------------------------------------------+--------------------+\n");
        System.out.format("| NomEmprunteur      | PrenomEmprunteur   | LivreEmprunte                            | MontantPenalite    |\n");
        System.out.format("+--------------------+--------------------+------------------------------------------+--------------------+\n");

        // Parcourir et afficher les résultats
        while (resultSet.next()) {
            String nom = resultSet.getString("NomEmprunteur");
            String prenom = resultSet.getString("PrenomEmprunteur");
            String titre = resultSet.getString("LivreEmprunte");
            String montant = resultSet.getString("MontantPenalite");

            // Formater chaque ligne
            System.out.format(format, nom, prenom, titre, montant);
        }

        // Afficher la ligne de fin du tableau
        System.out.format("+--------------------+--------------------+------------------------------------------+--------------------+\n");

        // Navigation post affichage
        FichierMenus.postUpdateNavigation();

        // Fermer les ressources
        resultSet.close();
    }


    public static void displayEmpruntRetourne() throws SQLException {
        // Exécuter une requête pour afficher les emprunts retournés
        String display = RequeteContainer.afficherEmpruntsRetournes(); // Assurez-vous que cette méthode génère la requête SQL correcte
        ResultSet resultSet = statement.executeQuery(display);

        // Afficher l'en-tête du tableau
        String format = "| %-10s | %-18s | %-18s | %-45s | %-15s | %-15s | %-10s |\n";
        System.out.format("+------------+--------------------+--------------------+-----------------------------------------------+-----------------+-----------------+------------+\n");
        System.out.format("| idEmprunt  | NomEmprunteur      | PrenomEmprunteur   | LivreEmprunte                                 | DateEmprunt     | DateRetourEff   | Penalité   |\n");
        System.out.format("+------------+--------------------+--------------------+-----------------------------------------------+-----------------+-----------------+------------+\n");

        // Parcourir et afficher les résultats
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nom = resultSet.getString("NomEmprunteur");
            String prenom = resultSet.getString("PrenomEmprunteur");
            String titre = resultSet.getString("LivreEmprunte");
            String dateEmprunt = resultSet.getString("DateEmprunt");
            String dateRetourEffective = resultSet.getString("DateRetourEffective");
            String penaliteEffective = resultSet.getString("PenaliteEffective"); // "Oui" ou "Non"

            // Formater chaque ligne
            System.out.format(format, id ,nom, prenom, titre, dateEmprunt, dateRetourEffective, penaliteEffective);
        }

        // Afficher la ligne de fin du tableau
        System.out.format("+------------+--------------------+--------------------+-----------------------------------------------+-----------------+-----------------+------------+\n");

        // Navigation post affichage
        FichierMenus.postUpdateNavigation();

        // Fermer les ressources
        resultSet.close();
    }


    public static void displayEmpruntEncours() throws SQLException {
        // Exécuter une requête pour afficher les emprunts en cours
        String display = RequeteContainer.afficherEmpruntEncours(); // Assurez-vous que cette méthode génère la requête SQL pour les emprunts en cours
        ResultSet resultSet = statement.executeQuery(display);

        // Afficher l'en-tête du tableau
        String format = "| %-10s | %-18s | %-18s | %-45s | %-15s | %-15s |\n";
        System.out.format("+------------+--------------------+--------------------+-----------------------------------------------+-----------------+-----------------+\n");
        System.out.format("| idEmprunt  | NomEmprunteur      | PrenomEmprunteur   | LivreEmprunte                                 | DateEmprunt     | DateRetourPrevue|\n");
        System.out.format("+------------+--------------------+--------------------+-----------------------------------------------+-----------------+-----------------+\n");

        // Parcourir et afficher les résultats
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nom = resultSet.getString("NomEmprunteur");
            String prenom = resultSet.getString("PrenomEmprunteur");
            String titre = resultSet.getString("LivreEmprunte");
            String dateEmprunt = resultSet.getString("DateEmprunt");
            String dateRetourPrevue = resultSet.getString("DateRetourPrevue");

            // Formater chaque ligne
            System.out.format(format, id ,nom, prenom, titre, dateEmprunt, dateRetourPrevue);
        }

        // Afficher la ligne de fin du tableau
        System.out.format("+------------+--------------------+--------------------+-----------------------------------------------+-----------------+-----------------+\n");

        // Fermer les ressources
        resultSet.close();
    }



}