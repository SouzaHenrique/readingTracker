package readingTracker.com.br.BLL;

import readingTracker.com.br.model.LivroModel;
import readingTracker.com.br.dao.DaoLivro;

public class LivroBLL {

    //TODO - Luiz : Método para verificar se o livro pesquisado existe na base do sistema
        /* Este método funciona como "QuarterBack" validando se o titulo nao está nulo
           Ele recebe uma string Titulo e também a referencia de uma Lista que recebera o retorno
           dos livros encontrados. Este método deve retornar uma String informando "titulo nao pode estar vazio"
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
