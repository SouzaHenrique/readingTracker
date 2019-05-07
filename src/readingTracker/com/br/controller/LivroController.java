package readingTracker.com.br.controller;


import readingTracker.com.br.BLL.LivroBLL;
import readingTracker.com.br.model.LivroModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class LivroController extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("Servlet Livro Funcionando!");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        LivroModel oLivroModel = new LivroModel();
        LivroBLL oLivroBLL = new LivroBLL();

        String action = request.getParameter("action");

        //TODO - Luiz: Preencher LivroModel com parâmetros esperados

        switch (action){

            case "create":{

                //TODO - Luiz: Chamar método da BLL para inserir registro de um Livro
                break;
            }

            case "edit":{

                //TODO - Luiz: Chamar método da BLL para inserir registro de um Livro
                break;
            }

            case "listar":{

                // TODO - Luiz : Chamar método da BLL para obter uma lista de livros
                break;
            }

            case "obter":{

                // TODO Luiz : Chamar méotod da BLL para obter um registro por ID
            }

        }

    }



}
