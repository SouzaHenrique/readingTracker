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

        String mensagem = "";

        boolean valid = false;
        HttpSession session = request.getSession();

        //Chamar método que valida login depois de validar valores do request

        if (acao != null  && !acao.isEmpty()) {

            switch (acao){
                case "login": {

                    if(emailForm != null && senhaForm != null && !emailForm.isEmpty() && !senhaForm.isEmpty()){

                        PessoaModel oPessoaModel = new PessoaModel();
                        PessoaBLL oPessoaBLL = new PessoaBLL();

                        oPessoaModel = oPessoaBLL.ObterLogin(emailForm,senhaForm);


                        if (oPessoaModel != null) {

                            session.setAttribute("APID", oPessoaModel.getApiId());
                            session.setMaxInactiveInterval(5);

                            String API = (String) session.getAttribute("APID");

                            mensagem = "Usuário Logado";
                            valid = true;


                        }else{
                            mensagem = "Falha na autenticação! Verifique seu usuário e senha e tente novamente!";
                        }



                    }else{

                    mensagem = "Dados devem ser preenchidos!";

                }
                    break;
                }

                case "logoff":{
                    session.invalidate();
                    mensagem = "Você foi deslogado!";
                    valid = true;

                    break;
                }
            }
        }else{
            valid = false;
            mensagem = "falta açao!";
        }

        List<Object> obj = new ArrayList<>();
        obj.add(mensagem);
        obj.add(valid);

        String json = new Gson().toJson(obj);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}