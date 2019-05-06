package readingTracker.com.br.controller;

import readingTracker.com.br.BLL.LivroBLL;
import readingTracker.com.br.dao.DaoLivro;
import readingTracker.com.br.model.LivroModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class LivroController extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {



    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String Titulo = "";
        LivroBLL oBLL = new LivroBLL();
        Titulo = request.getParameter("titulo");
        //String op = request.getParameter("opcao");
        List<Object> lstLivros = new ArrayList<>();

        //if(op.equals(opcaoLivro.Select)) {

            try {
                if (oBLL.verificaCampos(Titulo)) {
                    lstLivros = oBLL.buscaLivros;
                    //verificar conforme formato da tela
                    // request.setAttributes("");

                } else {
                    Logger.getLogger(LivroController.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute("mensagem", "Título não pode ser vazio" + ex);
                }
            } catch (Exception ex) {
                Logger.getLogger(LivroController.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("mensagem", "Livro não encontrado" + ex);
            }

    //} alterações na base depois de busca na API.
          // DaoLivro.Save();
          //  if(op.equals(opcaoLivro.Save)) {

          //  }
       // DaoLivro.Update();
       // DaoLivro.Delete();

    }

}
