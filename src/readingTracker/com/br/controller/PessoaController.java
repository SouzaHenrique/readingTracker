package readingTracker.com.br.controller;

import readingTracker.com.br.BLL.PessoaBLL;
import readingTracker.com.br.model.PessoaModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PessoaController extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("Servlet Pessoa Funcionando!");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        PessoaModel oPessoaModel = new PessoaModel();
        PessoaBLL oPessoaBLL = new PessoaBLL();


        String action = request.getParameter("action");

        //TODO - Walter : Popular oPessoaModel com os parâmetros do request

        switch (action){

            case "create":{
                //TODO -  Walter : chamar método BLL para incluir registro de pessoa
                break;
            }

            case "edit":{
                //TODO - Walter : chamar método BLL para alterar registro de pessoa
                break;
            }

            case "listar":{

                //TODO - Walter : chamar método da BLL para listar todas as pessoas
                break;
            }

            case "obter":{
                // TODO Walter : Chamar méotod da BLL para obter um registro por ID
            }

        }


    }


}
