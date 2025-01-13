import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    private static Connection connection;

    // Méthode pour initialiser la connexion
    public static void initialize(String url, String user, String password) throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion établie avec succès !");
        }
    }

    // Méthode pour obtenir la connexion existante
    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            throw new SQLException("La connexion à la base de données n'a pas été initialisée.");
        }
        return connection;
    }

    // Méthode pour fermer la connexion
    public static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
            System.out.println("Connexion fermée avec succès !");
        }
    }
}
