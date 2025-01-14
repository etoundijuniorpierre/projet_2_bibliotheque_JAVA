import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LivreDAO {

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

    //Méthode pour afficher tous les listes
    public static void displayLivres() throws SQLException {
        // Exécuter une requête
        String display = RequeteContainer.afficherAllElem("livres");
        ResultSet resultSet = statement.executeQuery(display);

        displayElem(resultSet);

        // Fermer les ressources
        resultSet.close();
        statement.close();
    }


    //Méthode pour rechercher un livre
    static <V> void menuDeRechercheLivres() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenue dans le menu de recherche");
        System.out.println("Veuillez choisir une option de recherche:");
        System.out.println("0. Retour au menu principal");
        System.out.println("1. par titre");
        System.out.println("2. par catégorie");
        System.out.println("3. par auteur");
        System.out.println("4. par isbn");
        System.out.println("5. quitter\n");
        System.out.print("Votre choix: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 0:
                FichierMenus.menuChoice();
            break;
            case 1:
                String colunn1 = "titre";
                System.out.println("Veuillez Entrer un titre: ");
                String titre = scanner.next();
                String display = RequeteContainer.searchElem("livres", colunn1, titre);
                ResultSet resultSet0 = statement.executeQuery(display);

                System.out.println("liste des livres avec le titre : " + titre);
                displayElem(resultSet0);
                FichierMenus.postUpdateNavigation();
                // Fermer les ressources
                resultSet0.close();
                statement.close();
                break;
                case 2:
                    String colunn2 = "categorie";
                    System.out.println("Veuillez Entrer une catégorie: ");
                    String categorie = scanner.next();
                    String display1 = RequeteContainer.searchElem("livres", colunn2, categorie.toLowerCase());
                    ResultSet resultSet1 = statement.executeQuery(display1);

                    System.out.println("liste des livres de la catégorie : " + categorie);
                    displayElem(resultSet1);
                    FichierMenus.postUpdateNavigation();
                    // Fermer les ressources
                    resultSet1.close();
                    statement.close();
                    break;
                    case 3:
                        String colunn3 = "auteur";
                        System.out.println("Veuillez Entrer un Auteur: ");
                        String auteur = scanner.next();
                        String display2 = RequeteContainer.searchElem("livres", colunn3, auteur.toLowerCase());
                        ResultSet resultSet2 = statement.executeQuery(display2);

                        System.out.println("liste des livres de l'auteur : " + auteur);
                        displayElem(resultSet2);
                        FichierMenus.postUpdateNavigation();
                        // Fermer les ressources
                        resultSet2.close();
                        statement.close();
                        break;
            case 4:
                String colunn4 = "isbn";
                System.out.println("Veuillez Entrer un ISBN: ");
                V isbn = (V) scanner.next();
                String display3 = RequeteContainer.searchElem("livres", colunn4, isbn);
                ResultSet resultSet3 = statement.executeQuery(display3);

                System.out.println("livre avec l'isbn : " + isbn);
                displayElem(resultSet3);
                FichierMenus.postUpdateNavigation();
                // Fermer les ressources
                resultSet3.close();
                statement.close();
                break;
                        case 5:
                            FichierMenus.end();
                            System.exit(0);
                            break;
            default:
                System.out.println("Choix invalide... retour au menu principal");

                return;
        }

    }


    //Méthode qui gère l'ajout modification et suppresion des livres
    static void menuAddDelModLivres() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenue dans le menu ADD/DEL/MOD");
        System.out.println("Veuillez choisir une option de tri:");
        System.out.println("0. retour au menu Principal");
        System.out.println("1. Ajouter");
        System.out.println("2. Modifier");
        System.out.println("3. Supprimer");

        int choice = scanner.nextInt();

        switch (choice) {
            case 0:
                FichierMenus.menuChoice();
                break;

            case 1:
                // Ajout d'un nouveau livre
                System.out.println("Bienvenue dans le menu d'ajout des livres");
                System.out.println("Entrer l'ISBN du livre (numéro entier)");
                String isbn = scanner.next();
                // Consommer le saut de ligne laissé par next()
                scanner.nextLine();
                System.out.println("Entrer le titre du livre");
                String titre = scanner.nextLine();
                System.out.println("Entrer le nom de l'auteur");
                String auteur = scanner.nextLine();
                System.out.println("Entrer la nationalité de l'auteur");
                String nationalite = scanner.next();
                // Consommer le saut de ligne laissé par next()
                scanner.nextLine();
                System.out.println("Entrer la catégorie du livre");
                String categorie = scanner.nextLine();
                System.out.println("Entrer l'année de publication");
                String dateDeParution = scanner.nextLine();
                System.out.println("Entrer le nombre d'exemplaires");
                int nombreExemplaire = Integer.parseInt(scanner.next());


                // Génération de la requête SQL
                String query = RequeteContainer.insertLivre(Long.parseLong(isbn), titre, auteur, nationalite, categorie, dateDeParution, nombreExemplaire);
                System.out.println("Requête SQL générée : " + query); // Debug

                // Exécution de la requête SQL
                statement.executeUpdate(query);
                System.out.println("Ajout avec succès !");
                FichierMenus.postUpdateNavigation();
                    break;


            case 2:
                // Modification d'une information de livre
                Scanner scanner0 = new Scanner(System.in);
                System.out.println("Bienvenue dans le menu de modification des livres");
                System.out.print("indiquer la donnée à entrer pour selectionner le livre : ");
                System.out.print("isbn / titre \n");
                System.out.print("votre choix: ");
                String userInput = scanner0.nextLine();

                System.out.print("veuillez entrer " + userInput + " du livre \n");
                System.out.print("Saisir: ");
                String userInput1 = scanner0.nextLine();

                // Affichage des informations du livre sélectionné
                String display = RequeteContainer.searchElem("livres", userInput, userInput1);
                ResultSet resultSet0 = statement.executeQuery(display);
                displayElem(resultSet0);

                System.out.println("Indiquer la donnée à modifier :");
                System.out.println("1. L'ISBN du livre");
                System.out.println("2. Le titre du livre");
                System.out.println("3. Le nom de l'auteur");
                System.out.println("4. La nationalité de l'auteur");
                System.out.println("5. La catégorie du livre");
                System.out.println("6. L'année de publication");
                System.out.println("7. Le nombre d'exemplaires");
                System.out.print("Votre choix : ");
                String userInput2 = scanner0.nextLine();  // Utilisation de nextLine pour capturer correctement l'input

                switch (userInput2) {
                    case "1":
                        // ISBN
                        System.out.print("Nouvelle donnée (ISBN) : ");
                        String userInput3 = scanner0.nextLine();
                        String updateQuery = RequeteContainer.updateElem("livres", "isbn",userInput, userInput3, userInput1);
//                        statement.executeUpdate(updateQuery);
                        int rowsUpdated = statement.executeUpdate(updateQuery);
                        if (rowsUpdated > 0) {
                            System.out.println("Modification réussie !");
                        } else {
                            System.out.println("Aucune ligne mise à jour.");
                        }
                        System.out.println("Requête exécutée : " + updateQuery);

                        FichierMenus.postUpdateNavigation();
                        break;
                    case "2":
                        // Titre
                        System.out.print("Nouvelle donnée (titre) : ");
                        String userInput4 = scanner0.nextLine();
                        String updateQuery1 = RequeteContainer.updateElem("livres", "titre",userInput, userInput4, userInput1);
//                        statement.executeUpdate(updateQuery1);
                        int rowsUpdated1 = statement.executeUpdate(updateQuery1);
                        if (rowsUpdated1 > 0) {
                            System.out.println("Modification réussie !");
                        } else {
                            System.out.println("Aucune ligne mise à jour.");
                        }
                        System.out.println("Requête exécutée : " + updateQuery1);
                        FichierMenus.postUpdateNavigation();
                        break;
                    case "3":
                        // Auteur
                        System.out.print("Nouvelle donnée (nom de l'auteur) : ");
                        String userInput5 = scanner0.nextLine();
                        String updateQuery2 = RequeteContainer.updateElem("livres", "auteur",userInput, userInput5, userInput1); // Changement du nom de la colonne
//                        statement.executeUpdate(updateQuery2);
                        int rowsUpdated2 = statement.executeUpdate(updateQuery2);
                        if (rowsUpdated2 > 0) {
                            System.out.println("Modification réussie !");
                        } else {
                            System.out.println("Aucune ligne mise à jour.");
                        }
                        System.out.println("Requête exécutée : " + updateQuery2);
                        FichierMenus.postUpdateNavigation();
                        break;
                    case "4":
                        // Nationalité de l'auteur
                        System.out.print("Nouvelle donnée (nationalité de l'auteur) : ");
                        String userInput6 = scanner0.nextLine();
                        String updateQuery3 = RequeteContainer.updateElem("livres", "nationaliteauteur",userInput, userInput6, userInput1);  // Changement du nom de la colonne
//                        statement.executeUpdate(updateQuery3);
                        int rowsUpdated3 = statement.executeUpdate(updateQuery3);
                        if (rowsUpdated3 > 0) {
                            System.out.println("Modification réussie !");
                        } else {
                            System.out.println("Aucune ligne mise à jour.");
                        }
                        System.out.println("Requête exécutée : " + updateQuery3);
                        FichierMenus.postUpdateNavigation();
                        break;
                    case "5":
                        // Catégorie
                        System.out.print("Nouvelle donnée (catégorie) : ");
                        String userInput7 = scanner0.nextLine();
                        String updateQuery4 = RequeteContainer.updateElem("livres", "categorie",userInput, userInput7, userInput1);
//                        statement.executeUpdate(updateQuery4);
                        int rowsUpdated4 = statement.executeUpdate(updateQuery4);
                        if (rowsUpdated4 > 0) {
                            System.out.println("Modification réussie !");
                        } else {
                            System.out.println("Aucune ligne mise à jour.");
                        }
                        System.out.println("Requête exécutée : " + updateQuery4);
                        FichierMenus.postUpdateNavigation();
                        break;
                    case "6":
                        // Date de parution
                        System.out.print("Nouvelle donnée (année de publication) : ");
                        String userInput8 = scanner0.nextLine();
                        String updateQuery5 = RequeteContainer.updateElem("livres", "dateParution",userInput, userInput8, userInput1);  // Changement du nom de la colonne
//                        statement.executeUpdate(updateQuery5);
                        int rowsUpdated5 = statement.executeUpdate(updateQuery5);
                        if (rowsUpdated5 > 0) {
                            System.out.println("Modification réussie !");
                        } else {
                            System.out.println("Aucune ligne mise à jour.");
                        }
                        System.out.println("Requête exécutée : " + updateQuery5);
                        FichierMenus.postUpdateNavigation();
                        break;
                    case "7":
                        // Nombre d'exemplaires
                        System.out.print("Nouvelle donnée (nombre d'exemplaires) : ");
                        String userInput9 = scanner0.nextLine();
                        String updateQuery6 = RequeteContainer.updateElem("livres", "nombreExemplaires", userInput,userInput9, userInput1);  // Changement du nom de la colonne
//                        statement.executeUpdate(updateQuery6);
                        int rowsUpdated6 = statement.executeUpdate(updateQuery6);
                        if (rowsUpdated6 > 0) {
                            System.out.println("Modification réussie !");
                        } else {
                            System.out.println("Aucune ligne mise à jour.");
                        }
                        System.out.println("Requête exécutée : " + updateQuery6);
                        FichierMenus.postUpdateNavigation();
                        break;
                    default:
                        System.out.println("Choix invalide... retour au menu principal");
                        break;
                }
                break;
            case 3:
                // Suppression d'un livre par titre
                System.out.println("Bienvenue dans le menu de suppression des livres");
                System.out.print("indiquer la donnée à entrer pour selectionner le livre : ");
                System.out.print("isbn / titre \n");
                System.out.print("votre choix: ");
                String entre2 = scanner.next();

                scanner.nextLine();
                System.out.print("veuillez entrer " + entre2 + " du livre \n");
                System.out.print("Saisir: ");
                String entre5 = scanner.nextLine();

                String display1 = RequeteContainer.searchElem("livres", entre2, entre5);
                ResultSet resultSet1 = statement.executeQuery(display1);
                displayElem(resultSet1);

                System.out.print("voulez-vous vraiment supprimer le livre "+ entre2+ ": "+ entre5 +"\n");
                System.out.print("oui / non \n");
                String entre4 = scanner.next();


                if (entre4.equalsIgnoreCase("non")) {
                    System.out.println("retour au menu principal");
                    FichierMenus.menuChoice();
                }else {
                    String query1 = RequeteContainer.deleteElem("livres", entre2, entre5);
                    //System.out.println("Requête SQL générée : " + query1); // Debug

                    // Exécution de la requête SQL
                    statement.executeUpdate(query1);
                    System.out.println("delete avec succès !");
                    FichierMenus.postUpdateNavigation();
                }

                break;
            default:
                System.out.println("choix invalide... retour au menu principal");
                FichierMenus.menuChoice();
        }
        scanner.close();
    }


    //afficharge des éléments
    public static void displayElem(ResultSet set) throws SQLException {
        if (set == null) {
            System.out.println("Le ResultSet est vide !");
            return;
        }

        // Afficher l'en-tête du tableau
        System.out.println("\nListe des livres : \n");
        String format = "| %-5s | %-42s | %-25s | %-25s | %-5s |\n";
        System.out.format("+-------+--------------------------------------------+---------------------------+---------------------------+-------+\n");
        System.out.format("| ID    | Titre                                      | Auteur                    | Catégorie                 | NbEx  |\n");
        System.out.format("+-------+--------------------------------------------+---------------------------+---------------------------+-------+\n");

        // Parcourir et afficher les résultats
        while (set.next()) {
            int id = set.getInt("id");
            String titre = set.getString("titre");
            String auteur = set.getString("auteur");
            String categorie = set.getString("categorie");
            int nombreExemplaires = set.getInt("nombreExemplaires");

            // Formater chaque ligne
            System.out.format(format, id, titre, auteur, categorie, nombreExemplaires);
        }

        // Afficher la ligne de fin du tableau
        System.out.format("+-------+--------------------------------------------+---------------------------+---------------------------+-------+\n");
    }


}

