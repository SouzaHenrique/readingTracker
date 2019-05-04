package readingTracker.com.br.controller;

import readingTracker.com.br.BLL.LeituraBLL;
import readingTracker.com.br.dao.DaoLeitura;
import readingTracker.com.br.model.LeituraModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import readingTracker.com.br.model.opcaoLeitura;

import static java.lang.Integer.*;

public class LeituraController extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LeituraModel oLeitura = new LeituraModel();
        LeituraBLL oBll = new LeituraBLL();
        String op = request.getParameter("opcao");


        oLeitura.setId_Leitor(parseInt(request.getParameter("id_leitor")));
        oLeitura.setId_Livro(parseInt(request.getParameter("id_livro")));
        oLeitura.setDataterminoPlanejado(request.getParameter("dataTerminoPlanejado"));
        oLeitura.setPaginasLidas(parseInt(request.getParameter("paginasLidas")));
        oLeitura.setStatusLeitura(parseInt(request.getParameter("statusLeitura")));


        if(op.equals(opcaoLeitura.CRIAR)) {
            //Opção 1 - criar nova leitura
            try {
                if (oBll.verificaCampos(oLeitura)) {
                    if (oBll.novaLeitura(oLeitura)) {
                        //request.setAttribute("mensagem", "Leitura cadastrada com sucesso!");
                        response.getWriter().println("<html>");
                        response.getWriter().println("<head>");
                        response.getWriter().println("<title> teste retorno ok </title>");
                        response.getWriter().println("</head>");
                        response.getWriter().println("<body>");
                        response.getWriter().println("cadastro sucesso");
                        response.getWriter().println("</body>");
                        response.getWriter().println("</html>");


                    } else {
                        request.setAttribute("mensagem", "Não foi possivel cadastrar esta leitura!");
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(LeituraController.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("mensagem", "Campos invalidos ou incompletos" + ex);
            }

        }

        else if (op.equals(opcaoLeitura.EDITAR)){
                //Opção 2 - editar leitura
            if(oBll.verificaCampos(oLeitura)){
                if(oBll.editarLeitura(oLeitura)){
                    request.setAttribute("mensagem", "Leitura atualizada com sucesso!");
                }else{
                    request.setAttribute("mensagem", "Não foi possivel atualizar esta leitura!");
                }
            }else{
                request.setAttribute("mensagem", "Campos invalidos ou incompletos");

            }

        }
        else if (op.equals(opcaoLeitura.LISTAR)){
            //Opção 3 - listar leituras do usuario
            if (oBll.verificaCampos(oLeitura)){
                List<Object> lstLeitura = new ArrayList<>();
                lstLeitura = oBll.listarLeituras(oLeitura.getId_Leitor());
            }else{
                request.setAttribute("mensagem", "Campos invalidos ou incompletos");
            }

        }

        else if (op.equals(opcaoLeitura.EXCLUIR)){

        }
        else {

        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
