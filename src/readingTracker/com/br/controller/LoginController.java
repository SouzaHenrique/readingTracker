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
        List<Object> obj = new ArrayList<>();

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

                                session.setAttribute("APID", oPessoaModel.getApiId());
                                session.setMaxInactiveInterval(30);
                                mensagem = "Autenticação OK!";
                                obj.add(mensagem);

                            }else{
                                mensagem = "Falha na autenticação!";
                                obj.add(mensagem);
                            }

                        break;
                    }

                    case "login_android":{

                        PessoaModel oPessoaModel = new PessoaModel();
                        oPessoaModel.setEmail(emailForm);
                        oPessoaModel.setSenha(senhaForm);

                        boolean autenticado = isLoginValid(oPessoaModel);

                        if (autenticado) {

                            obj.add(oPessoaModel.getApiId());

                        }else{
                            mensagem = "Falha na autenticação!";
                            obj.add(mensagem);
                        }

                        break;
                    }


                    case "logoff":{

                        session.invalidate();

                        mensagem = "INVALIDATE_SESSION";
                        obj.add(mensagem);
                        break;
                    }
                }
            }else{
                mensagem = "Falta ação (ações disponíveis: login ou logoff) ";
                obj.add(mensagem);
            }

        }else{
            mensagem = "Dados devem login devem ser preenchidos!";
            obj.add(mensagem);
        }

        String json = new Gson().toJson(obj);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }

    private boolean isLoginValid(PessoaModel oPessoaModel) {

        PessoaBLL oPessoaBLL = new PessoaBLL();
        boolean autenticaco = false;

        //validar se usuario e senha existem na base de pessoas
        oPessoaModel.setApiId(oPessoaBLL.ObterPessoaPorEmailSenha(oPessoaModel));

        if(!oPessoaModel.getApiId().toString().isEmpty()){
            autenticaco = true;
        }

        return autenticaco;
    }




}