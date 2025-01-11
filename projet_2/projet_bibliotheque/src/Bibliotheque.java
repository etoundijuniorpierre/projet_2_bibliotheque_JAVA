import java.sql.SQLException;

public class Bibliotheque {
    public static void main(String[] args) {
        try {
            // Initialisez la connexion à la base de données
            DBconnection db = new DBconnection();
            db.initialize("jdbc:postgresql://localhost:8080/postgres", "postgres", "Ejep2001@");

            Menus.menuChoice();

            // Fermez la connexion à la fin
            DBconnection.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
