

 package java.com.br.factory;

 // import java.net.URL;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.SQLException;


public class ConnectionFactory {

    public final String SERVER = "";
    public final String PORT = "";
    public final String USER = "";
    public final String PASSWORD = "";
    public final String DATABASE = "";

    public Connection getConnection() {
        String url = "jdbc:mysql://" + SERVER + ":" + PORT + "/" + DATABASE;
        Connection con;

        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            con = DriverManager.getConnection(url, USER, PASSWORD);
            return con;
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver não encontrado" + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Não conecta com o Servidor" + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Outro erro: " + ex.getMessage());

        }
        return null;

    }
}