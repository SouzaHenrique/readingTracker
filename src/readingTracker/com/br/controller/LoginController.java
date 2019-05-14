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

import com.sun.deploy.net.HttpRequest;

import readingTracker.com.br.BLL.PessoaBLL;
import readingTracker.com.br.model.LeituraModel;
import readingTracker.com.br.model.PessoaModel;

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

        HttpSession session = request.getSession();

        //Chamar método que valida login depois de validar valores do request
        if (acao != null  && !acao.isEmpty()) {

            switch (acao){
                case "login": {

                    if(emailForm != null && senhaForm != null && !emailForm.isEmpty() && !senhaForm.isEmpty()){

                        PessoaModel oPessoaModel = new PessoaModel();
                        oPessoaModel.setEmail(emailForm);
                        oPessoaModel.setSenha(senhaForm);

                        boolean autenticado = isLoginValid(oPessoaModel);

                        if (autenticado && !oPessoaModel.getApiId().equals(null)) {

                            session.setAttribute("APID", oPessoaModel.getApiId());
                            session.setMaxInactiveInterval(30);
                            response.sendRedirect("logado.jsp");

                        }else{
                            response.setContentType("text/html");
                            PrintWriter out = response.getWriter();
                            out.println("Falha na autenticação! Verifique seu usuário e senha e tente novamente!");
                        }

                    }else{
                        response.setContentType("text/html");
                        PrintWriter out = response.getWriter();
                    out.println("Dados devem ser preenchidos!");
                }
                    break;
                }

                case "logoff":{

                    session.invalidate();
                    response.setContentType("text/html");
                    PrintWriter out = response.getWriter();
                    out.println("Você foi deslogado!");

                    break;
                }
            }
        }else{
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("falta açao!");
        }

    }

    public boolean isLoginValid(PessoaModel oPessoaModel) {

        PessoaBLL oPessoaBLL = new PessoaBLL();
        boolean autenticaco = false;

        //validar se usuario e senha existem na base de pessoas
        oPessoaModel.setApiId(oPessoaBLL.ObterPessoaPorEmailSenha(oPessoaModel));

        if(!oPessoaModel.getApiId().toString().isEmpty() && !oPessoaModel.getApiId().toString().equals(null)){
            autenticaco = true;
        }

        return autenticaco;
    }




}