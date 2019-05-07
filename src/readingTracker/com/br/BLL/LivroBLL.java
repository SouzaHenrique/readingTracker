package readingTracker.com.br.BLL;

import readingTracker.com.br.dao.DaoLivro;

import java.util.ArrayList;
import java.util.List;

public class LivroBLL {

    private DaoLivro daoLivro = new DaoLivro();

    public boolean verificaCampos(String Titulo) {

        if (Titulo.equals(null)) {

            return false;
        }

        return true;
    }

    public List<Object> buscaLivros(String Titulo) {

        List<Object> lstLivro = new ArrayList<>();
        lstLivro = daoLivro.Select(Titulo);

        if (lstLivro.isEmpty()){

            //Implementar método de busca de livros na API.
            //lstLivro = BooksAPI.getapi;



            return lstLivro;
        }

            return lstLivro;

    }
}
