package readingTracker.com.br.controller;

import readingTracker.com.br.model.PessoaModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

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

        boolean autenticado = false;
        HttpSession session = request.getSession();

        //Chamar método que valida login depois de validar valores do request
        if (acao != null  && !acao.isEmpty()) {

            switch (acao){
                case "login": {

                    if(emailForm != null && senhaForm != null && !emailForm.isEmpty() && !senhaForm.isEmpty()){

                        autenticado = isLoginValid(emailForm, senhaForm);

                        if (autenticado) {

                            session.setAttribute("logado", "true");
                            session.setMaxInactiveInterval(5);
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

    public boolean isLoginValid(String usuario, String senha) {

        boolean autenticaco = false;

        //validar se usuario e senha existem na base de pessoas
        PessoaModel oPessoa = new PessoaModel(
                1,
                "Henrique",
                "Martins de Souza",
                "19/11/1993",
                "martins_henrique@uninove.edu.br",
                "521197",
                "AHXYZ",
                true);

        if(usuario.equals(oPessoa.getEmail()) && senha.equals(oPessoa.getSenha())){
            autenticaco = true;
        }

        return autenticaco;
    }

}