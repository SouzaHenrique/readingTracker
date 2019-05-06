

 package readingTracker.com.br.factory;

 // import java.net.URL;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.SQLException;


public class ConnectionFactory {

    public final String SERVER = "localhost";
    public final String PORT = "3306";
    public final String USER = "root";
    public final String PASSWORD = "";
    public final String DATABASE = "readingtracker";

    public Connection getConnection() {
        String url = "jdbc:mysql://" + SERVER + ":" + PORT + "/" + DATABASE + "?useTimezone=true&serverTimezone=UTC";
        Connection con;

        try {
            con = DriverManager.getConnection(url, USER, PASSWORD);
            return con;
        } catch (SQLException ex) {
            System.out.println("Não conecta com o Servidor, motivo: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Outro erro: " + ex.getMessage());

        }
        return null;

    }
}