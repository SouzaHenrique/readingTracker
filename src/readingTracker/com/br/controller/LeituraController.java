package readingTracker.com.br.controller;

import com.google.gson.Gson;
import readingTracker.com.br.BLL.LeituraBLL;
import readingTracker.com.br.model.LeituraModel;
import readingTracker.com.br.model.PessoaModel;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

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
        String msg = "";

        /// TODO: 10/05/2019 implementar metodo para pegar object de pessoa baseado no api id
        //bucando usuario logado
        HttpSession session = request.getSession();
        oLeitura.setId_Leitor(Integer.parseInt((String) session.getAttribute("APID")));

        String action = request.getParameter("action");
        oLeitura.setId_Leitor(Integer.parseInt(request.getParameter("id_leitor")));
        oLeitura.setId_Livro(Integer.parseInt(request.getParameter("id_livro")));
        oLeitura.setStatusLeitura(Integer.parseInt(request.getParameter("statusLeitura")));
        oLeitura.setPaginasLidas(Integer.parseInt(request.getParameter("paginasLidas")));
        oLeitura.setDataterminoPlanejado(request.getParameter("DataterminoPlanejado"));

        if (action != null  && !action.isEmpty()) {
            switch (action) {

                case "create": {

                    if (oLeituraBLL.verificaCampos(oLeitura)) {
                        if (oLeituraBLL.novaLeitura(oLeitura)) {
                            msg = "Leitura cadastrada com sucesso!";
                        } else {
                            msg = "Não foi possivel cadastrar esta leitura!";
                        }
                    } else {
                        msg = "Dados invalidos ou nulo!";
                    }

                    String json = new Gson().toJson(msg);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                    break;
                }

                case "edit": {

                    if (oLeituraBLL.verificaCampos(oLeitura)) {
                        if (oLeituraBLL.editarLeitura(oLeitura)) {
                            msg = "Leitura editada com sucesso!";
                        } else {
                            msg = "Não foi possivel editar esta leitura!";
                        }
                    } else {
                        msg = "Dados invalidos ou nulo!";
                    }

                    String json = new Gson().toJson(msg);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                    break;
                }

                case "listar": {
                    List<LeituraModel> lstLeitura = new ArrayList<LeituraModel>();

                    lstLeitura = oLeituraBLL.listarLeituras(oLeitura.getId_Leitor());

                    String json = new Gson().toJson(lstLeitura);

                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);


                    break;
                }

                case "obter": {

                    break;
                }

            }
        }else{
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("falta açao!");
        }

    }

}
