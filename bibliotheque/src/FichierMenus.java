import java.util.Scanner;
import java.sql.SQLException;

public class FichierMenus {


    // Menus de notre application
    public FichierMenus() throws SQLException {
    }

    static void menuChoice() throws SQLException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenue dans notre Bibliothèque <LECTTER WORLD>\n");
        System.out.println("Veuillez choisir une option en entrant un nombre entre 1 et 4: \n");
        System.out.println("1. Gestion des livres");
        System.out.println("2. Gestion des membres");
        System.out.println("3. Gestion des Emprunts et pénélités");
        System.out.println("4. quitter\n");
        System.out.println("Votre choix: ");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                generiqueMenuHead("livres");
                int choice1 = scanner.nextInt();
                switch (choice1) {
                    case 0:
                        menuChoice();
                        break;
                    case 1:
                        LivreDAO.displayLivres();
                        genrequeMenuBody("livres");
                        choice1 = scanner.nextInt();
                        optionDeSortie(scanner, choice1);
                        break;
                        case 2:
                            LivreDAO.menuAddDelModLivres();
                            break;
                            case 3:
                                LivreDAO.menuDeRechercheLivres();
                                break;
                    default:
                        System.out.println("choix invalide... retour au menu");
                        System.out.println("Votre choix: ");
                        choice1 = scanner.nextInt();

                }
                break;
                case 2:
                    generiqueMenuHead("membres");
                    int choice2 = scanner.nextInt();
                    switch (choice2) {
                        case 0:
                            menuChoice();
                            break;
                        case 1:
                            MembreDAO.displayMembres();
                            genrequeMenuBody("membres");
                            choice2 = scanner.nextInt();
                            optionDeSortie(scanner, choice2);
                            break;
                        case 2:
                            MembreDAO.menuAddDelModMembres();
                            break;
                        case 3:
                            MembreDAO.menuDeRechercheMembres();
                            break;
                        default:
                            System.out.println("choix invalide... retour au menu");
                            System.out.println("Votre choix: ");
                            choice2 = scanner.nextInt();
                    }
                    break;
                    case 3:
                        EmpruntDAO.menuEmpruntPenalite();
                        break;
                        case 4:
                            end();
                            System.exit(0);
                            break;
            default:
                System.out.println("choix invalide... retour au menu principal");
                menuChoice();
        }
    }

    private static void optionDeSortie(Scanner scanner, int choice1) throws SQLException {
        switch (choice1) {
            case 1:
                menuChoice();
                break;
                case 2:
                    end();
                    System.exit(0);
                    break;
            default:
                System.out.println("choix invalide... retour au menu");
                System.out.println("Votre choix: ");
                choice1 = scanner.nextInt();
        }
    }

    // Méthode pour gérer la navigation post-mise à jour
    static void postUpdateNavigation() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nVoulez-vous retourner au menu principal ? :");
            System.out.println("1 : Retour au menu principal");
            System.out.println("2 : Quitter\n");
            System.out.println("Votre choix: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                menuChoice();
                break; // Sort de la boucle après le retour au menu
            } else if (choice.equals("2")) {
                System.out.println("Merci d'avoir utilisé notre application...");
                System.exit(0); // Quitte l'application
            } else {
                System.out.println("Choix invalide. Veuillez entrer '1' ou '2'.");
            }
        }
    }

    static void end() {
        System.out.println("merci d'avoir utilisé notre application A BIENTOT ");
    }

    public static void generiqueMenuHead(String elem) {
        System.out.println("Bienvenue dans le Menu de Gestion de " + elem);
        System.out.println("0. retour au menu Principal");
        System.out.println("1. Afficher la liste des " + elem);
        System.out.println("2. Ajouter / modifier / supprimer un " + elem);
        System.out.println("3. Rechercher un " + elem);
        System.out.print("Votre choix: ");
    }

    public static void genrequeMenuBody(String elem) {
        System.out.println("Voici la liste des tous les " +elem+ " de notre biliothèque \n");
        System.out.println("1. retour au menu Principal");
        System.out.println("2. quitter\n");
        System.out.println("Votre choix: ");
    }
}
