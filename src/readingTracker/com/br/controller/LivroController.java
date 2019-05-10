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

        LivroModel oLivro = new LivroModel();
        LivroBLL oLivroBLL = new LivroBLL();

        String msg = "";

        String action = request.getParameter("action");

        //TODO - Luiz: Preencher LivroModel com parâmetros esperados

        oLivro.setTitulo(request.getParameter("Titulo"));
        oLivro.setAutor(request.getParameter("Autor"));
        oLivro.setAnoPublicacao(request.getParameter("AnoPublicacao"));
        oLivro.setEditora(request.getParameter("Editora"));
        oLivro.setQuantidadePaginas(Integer.parseInt(request.getParameter("QuantidadePaginas")));
        oLivro.setQuantidadeLeituras(Long.parseLong(request.getParameter("QuantidadeLeituras")));


        if (action != null  && !action.isEmpty()) {
            switch (action) {

            case "create":{

                //TODO - Luiz: Chamar método da BLL para inserir registro de um Livro

                if(oLivroBLL.verificaCampos(oLivro.getTitulo())){
                    if (oLivroBLL.novoLivro(oLivro)) {
                        msg = "Livro cadastrado com sucesso!";
                    } else {
                        msg = "Não foi possivel cadastrar este livro!";
                    }
                } else {
                    msg = "Título inválido ou nulo!";
                }


                String json = new Gson().toJson(msg);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
                break;
            }

            case "edit":{

                //TODO - Luiz: Chamar método da BLL para alterar registro de um Livro

                if(oLivroBLL.verificaCampos(oLivro.getTitulo())){
                    if (oLivroBLL.editarLivro(oLivro)) {
                        msg = "Livro editado com sucesso!";
                    } else {
                        msg = "Não foi possivel editar este livro!";
                    }
                } else {
                    msg = "Título inválido ou nulo!";
                }


                String json = new Gson().toJson(msg);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
                break;
            }

            case "listar":{

                // TODO - Luiz : Chamar método da BLL para obter uma lista de livros
                List<LivroModel> lstLivro = new ArrayList<LivroModel>();
                lstLivro = oLivroBLL.buscaLivros(oLivro.getTitulo());

                Gson gson = new Gson();
                Type listOfLivroObject = new TypeToken<List<LivroModel>>(){}.getType();
                String myJson = gson.toJson(lstLivro,listOfLivroObject);

                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(myJson);

                break;
            }

            case "obter":{

                // TODO Luiz : Chamar método da BLL para obter um registro por ID
            }

            default:{

                msg = "Ação invalida!";
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

}




