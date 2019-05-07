package readingTracker.com.br.controller;

import readingTracker.com.br.BLL.LeituraBLL;
import readingTracker.com.br.model.LeituraModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LeituraController extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("Servlet Leitura Funcionando!");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        LeituraModel oLeituraModel = new LeituraModel();
        LeituraBLL oLeituraBLL = new LeituraBLL();

        String action = request.getParameter("action");

        //TODO - Davi: Popular oLeituraModel com os parâmetros do request

        switch (action){

            case "create":{
                //TODO -  Davi : chamar método BLL para incluir registro de Leitura
                break;
            }

            case "edit":{
                //TODO - Davi : chamar método BLL para alterar registro de Leitura
                break;
            }

            case "listar":{
                //TODO - Davi : chamar método da BLL para listar todas as Leitura
                break;
            }

            case "obter":{
                // TODO Davi : Chamar méotod da BLL para obter um registro por ID
                break;
            }

        }

    }

}
