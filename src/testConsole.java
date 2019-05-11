import com.google.gson.Gson;
import readingTracker.com.br.BLL.BooksBLL;
import readingTracker.com.br.factory.ConnectionFactory;
import readingTracker.com.br.model.LivroModel;

import java.sql.Connection;
import java.util.List;

public class testConsole {

    public static void main(String[] args) {

        List<LivroModel>  lstLivro = BooksBLL.getInstance().doGetList("Harry Potter");

        String json = new Gson().toJson(lstLivro);

        System.out.println(json);

    }

}
