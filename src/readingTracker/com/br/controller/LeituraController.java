package readingTracker.com.br.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import readingTracker.com.br.BLL.LeituraBLL;
import readingTracker.com.br.model.LeituraModel;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LeituraController extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /*response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("Servlet Leitura Funcionando!");*/

        String Mensagem = "Leitura incluída com sucesso, pra cego ver!";

        Gson gson = new Gson();

        List<LeituraModel> lstLeitura =  retListaLeitura();
        Type listOfLeituraObject = new TypeToken<List<LeituraModel>>(){}.getType();

        String myJson = gson.toJson(lstLeitura,listOfLeituraObject);

        response.setContentType("application/json");

        response.setCharacterEncoding("utf-8");
        response.getWriter().write(myJson);
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


    public List<LeituraModel> retListaLeitura(){

        List<LeituraModel> lstLeituras = new ArrayList<LeituraModel>(){
            {
                add(new LeituraModel(2,2,3,1,80,"09052019"));
                add(new LeituraModel(1,2,3,1,80,"09052019"));
                add(new LeituraModel(3,2,3,1,80,"09052019"));
                add(new LeituraModel(4,2,3,1,80,"09052019"));
            }
        };

        return   lstLeituras;
    }


}
