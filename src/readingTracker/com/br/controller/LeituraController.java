package readingTracker.com.br.controller;

import com.google.gson.Gson;
import readingTracker.com.br.BLL.LeituraBLL;
import readingTracker.com.br.model.LeituraModel;
import readingTracker.com.br.model.MensagensModel;
import readingTracker.com.br.model.PessoaModel;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class LeituraController extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("Servlet Leitura Funcionando!");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        LeituraModel oLeitura = new LeituraModel();
        LeituraBLL oLeituraBLL = new LeituraBLL();
        PessoaModel oPessoa = new PessoaModel();
        MensagensModel msg = new MensagensModel();
        String action = request.getParameter("action");

        oLeitura.setId_Leitor(Integer.parseInt(request.getParameter("id_leitor")));
        oLeitura.setId_Livro(Integer.parseInt(request.getParameter("id_livro")));
        oLeitura.setStatusLeitura(Integer.parseInt(request.getParameter("statusLeitura")));
        oLeitura.setPaginasLidas(Integer.parseInt(request.getParameter("paginasLidas")));
        oLeitura.setDataterminoPlanejado(request.getParameter("DataterminoPlanejado"));


        switch (action){

            case "create":{
                //TODO -  Davi : chamar método BLL para incluir registro de Leitura
                /*oLeitura.setId_Leitor(Integer.parseInt(request.getParameter("id_leitor")));
                oLeitura.setId_Livro(Integer.parseInt(request.getParameter("id_livro")));
                oLeitura.setStatusLeitura(Integer.parseInt(request.getParameter("statusLeitura")));
                oLeitura.setPaginasLidas(Integer.parseInt(request.getParameter("paginasLidas")));
                oLeitura.setDataterminoPlanejado(request.getParameter("DataterminoPlanejado"));*/

                if(oLeituraBLL.verificaCampos(oLeitura)){
                    if(oLeituraBLL.novaLeitura(oLeitura)) {
                        msg.setMensagem("Leitura cadastrada com sucesso!");
                    }else{
                        msg.setMensagem("Não foi possivel cadastrar esta leitura!");
                    }
                }else{
                    msg.setMensagem("Dados invalidos ou nulo!");
                }


                String json = new Gson().toJson(msg);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
                break;
            }

            case "edit":{
                //TODO - Davi : chamar método BLL para alterar registro de Leitura
                /*oLeitura.setId_Leitor(Integer.parseInt(request.getParameter("id_leitor")));
                oLeitura.setId_Livro(Integer.parseInt(request.getParameter("id_livro")));
                oLeitura.setStatusLeitura(Integer.parseInt(request.getParameter("statusLeitura")));
                oLeitura.setPaginasLidas(Integer.parseInt(request.getParameter("paginasLidas")));
                oLeitura.setDataterminoPlanejado(request.getParameter("DataterminoPlanejado"));*/

                if(oLeituraBLL.verificaCampos(oLeitura)){
                    if (oLeituraBLL.editarLeitura(oLeitura)){
                    }else{
                        msg.setMensagem("Não foi possivel editar esta leitura!");
                    }
                }else{
                    msg.setMensagem("Dados invalidos ou nulo!");
                }

                String json = new Gson().toJson(msg);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
                break;
            }

            case "listar":{
                //TODO - Davi : chamar método da BLL para listar todas as Leitura
                List<Object> lstLeitura = new ArrayList<>();

                lstLeitura = oLeituraBLL.listarLeituras(oLeitura.getId_Leitor());
                String json = new Gson().toJson(lstLeitura);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);


                break;
            }

            case "obter":{

                break;
            }

        }

    }

}
