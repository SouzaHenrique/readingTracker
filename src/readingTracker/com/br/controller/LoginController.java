package readingTracker.com.br.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import readingTracker.com.br.BLL.PessoaBLL;
import readingTracker.com.br.model.*;

public class LoginController extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("Servlet Login Funcionando!");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        //Obter dados do request
        String emailForm = request.getParameter("usuario");
        String senhaForm = request.getParameter("senha");
        String acao = request.getParameter("action");
        String mensagem = "";
        List<Object> obj = new ArrayList<>();
        requestStatus orequestStatus = new requestStatus(false);

        HttpSession session = request.getSession();

        //Chamar método que valida login depois de validar valores do request

        if(emailForm != null && senhaForm != null && !emailForm.isEmpty() && !senhaForm.isEmpty()){

            if (acao != null  && !acao.isEmpty()) {

                switch (acao){
                    case "login": {

                            PessoaModel oPessoaModel = new PessoaModel();
                            oPessoaModel.setEmail(emailForm);
                            oPessoaModel.setSenha(senhaForm);

                            boolean autenticado = isLoginValid(oPessoaModel);

                            if (autenticado) {

                                session.setAttribute("ID", oPessoaModel.getId());
                                session.setMaxInactiveInterval(30);
                                orequestStatus.setRequestStatus(true);
                            }

                            obj.add(orequestStatus);
                            obj.add(oPessoaModel.getId());

                        break;
                    }

                    case "logoff":{

                        session.invalidate();
                        orequestStatus.setRequestStatus(true);

                        obj.add(orequestStatus);
                        break;
                    }
                }
            }else{
                mensagem = "Falta ação (ações disponíveis: login ou logoff) ";
                obj.add(orequestStatus);
                obj.add(mensagem);
            }

        }else{
            mensagem = "Dados devem login devem ser preenchidos!";
            obj.add(orequestStatus);
            obj.add(mensagem);
        }

        String json = new Gson().toJson(obj);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }

    private boolean isLoginValid(PessoaModel oPessoaModel) {

        PessoaBLL oPessoaBLL = new PessoaBLL();
        boolean autenticaco;

        //validar se usuario e senha existem na base de pessoas
        oPessoaModel.setId(oPessoaBLL.ObterPessoaPorEmailSenha(oPessoaModel));

        if(oPessoaModel.getId() == 0){
            autenticaco = false;
        }else{
            autenticaco = true;
        }

        return autenticaco;
    }




}