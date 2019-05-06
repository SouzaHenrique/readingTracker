package readingTracker.com.br.BLL;

import readingTracker.com.br.model.LivroModel;
import readingTracker.com.br.dao.DaoLivro;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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



    //TODO - Luiz : Método para verificar se o livro pesquisado existe na base do sistema
        /* Este método funciona como "QuarterBack" validando se o titulo nao está nulo
           Ele recebe uma string Titulo e também a referencia de uma Lista que recebera o retorno
           dos livros encontrados.

           Controller
           Este método deve retornar uma String informando "titulo nao pode estar vazio"
           se o titulo for nulo ou "livro não encontrado" caso a lista esteja vazia ou nula.
        */

    //TODO - Luiz : Método para chamar a DAOLivro e retornar os livros correspondentes a pesquisa
        /*  Método que encapsula a chamada do método da DAOLivro (você precisa criar um método
            List<Object> Get (String Titulo){} na DaoLivro
        */

    //TODO - Luiz : Método para chamar a BooksBLL caso não existam Livros na base do sistema
        /*  Método que encapsula a chamada do método da classe BooksAPI responsável
            pelo acionamento da api do Google que procurará e retornará os livros correspondentes
            ao Titulo Pesquisado.
        */
}
