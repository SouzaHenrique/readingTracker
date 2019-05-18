package readingTracker.com.br.controller;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import readingTracker.com.br.BLL.LivroBLL;
import readingTracker.com.br.model.LivroModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class LivroController extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("Servlet Livro Funcionando!");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        LivroModel oLivro;
        LivroBLL oLivroBLL = new LivroBLL();

        String mensagem = "";
        int quantidadePaginas;
        int quantidadeLeituras;

        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String anoPublicacao = request.getParameter("anoPublicacao");
        String editora = request.getParameter("editora");
        String action = request.getParameter("action");

        if(tryParseInt(request.getParameter("quantidadePaginas"))){
            quantidadePaginas = Integer.parseInt(request.getParameter("quantidadePaginas"));
        }else{
            quantidadePaginas = 0;
        }

        if(tryParseInt(request.getParameter("quantidadeLeituras"))){
            quantidadeLeituras = Integer.parseInt(request.getParameter("quantidadeLeituras"));
        }else{
            quantidadeLeituras = 0;
        }

        if (action != null  && !action.isEmpty()) {
            switch (action) {

            case "create":{

                oLivro = new LivroModel(0,titulo,autor,anoPublicacao,editora,quantidadePaginas,quantidadeLeituras);

                if(oLivroBLL.novoLivro(oLivro)){
                    mensagem ="Livro cadastrado!";
                }
                else{
                    mensagem = "Erro ao cadastrar o livro!";
                }

                String json = new Gson().toJson(mensagem);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
                break;
            }

            case "edit":{

                mensagem = "Não são permitidas nesta versão da ReadingTracker API alterações dos dados dos livros!";

                String json = new Gson().toJson(mensagem);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
                break;
            }

            case "listar":{

                oLivro = new LivroModel(0,titulo,autor,anoPublicacao,editora,quantidadePaginas,quantidadeLeituras);

                List<LivroModel> lstLivro = oLivroBLL.buscaLivros();

                Type listOfLivroObject = new TypeToken<List<LivroModel>>(){}.getType();
                String myJson = new Gson().toJson(lstLivro,listOfLivroObject);

                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(myJson);

                break;
            }

            case "obter":{

                // TODO Luiz : Chamar método da BLL para obter um registro por ID
            }

            default:{

                mensagem = "Ação invalida!";
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("falta açao!");
            }

        }

        }else{

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("falta açao!");

        }

    }


    boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}




