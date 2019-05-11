import readingTracker.com.br.factory.ConnectionFactory;

import java.sql.Connection;

public class testConsole {

    public static void main(String[] args) {

        Connection conn = new ConnectionFactory().getConnection();

        if(conn != null){
            System.out.println("Conexão bem sucedida!");
        }

    }

}
