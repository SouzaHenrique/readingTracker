package readingTracker.com.br.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import readingTracker.com.br.BLL.PessoaBLL;
import readingTracker.com.br.model.PessoaModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PessoaController extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("Servlet Pessoa Funcionando!");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        PessoaModel oPessoaModel = new PessoaModel();
        PessoaBLL oPessoaBLL = new PessoaBLL();

        /*
            private int Id;
            private String Nome;
            private String Sobrenome;
            private String DataNascimento;
            private String Email;
            private String Senha;
            private String ApiId;
            private Boolean isAtivo;
        */

        //como não sei qual vai ser a ação, vou criar uma String para cada atributo utilizando o fator ternário

        int id = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")): null ;
        String nome = request.getParameter("nome") != null ? request.getParameter("nome"): null;
        String sobrenome = request.getParameter("sobrenome") != null ? request.getParameter("sobrenome"): null;
        String dataNascimento = request.getParameter("dataNascimento") != null ? request.getParameter("dataNascimento"): null;
        String email = request.getParameter("email") != null ? request.getParameter("email"): null;
        String senha = request.getParameter("senha") != null ? request.getParameter("senha"): null;
        String apiId = request.getParameter("apiId") != null ? request.getParameter("apiId"): null;
        String statusPessoa = request.getParameter("isAtivo") != null ? request.getParameter("isAtivo"): null;
        Boolean isAtivo = null;
        if(statusPessoa != null){
            isAtivo = statusPessoa == "0" ? false: true;
        }

        String action = request.getParameter("action") != null ? request.getParameter("action") : null;

        PessoaModel pessoaModel = new PessoaModel(id,nome,sobrenome,dataNascimento,email,senha,apiId,isAtivo);
        PessoaBLL pessoaBLL = new PessoaBLL();
        List<Object> obj = new ArrayList<>();
        String mensagem = "";
        switch (action){

            case "create":{
                if(pessoaBLL.save(pessoaModel)){
                    mensagem = "registro inserido com sucesso!";
                } else {
                    mensagem =  "Erro ao inserir dados";
                }
                obj.add(mensagem);
                break;
            }

            case "edit":{

                if(pessoaBLL.update(pessoaModel)){
                    mensagem = "Alteração feita com sucesso!";
                } else {
                    mensagem = "Erro ao alterar dados";
                }
                obj.add(mensagem);
                break;
            }

            case "listar":{
                List<PessoaModel> lstPessoa = pessoaBLL.ObterPessoas();
                mensagem = "Listagem de todos os usuarios";
                obj.add(mensagem);
                obj.add(lstPessoa);
                break;
            }

            case "obterPorID":{
                pessoaModel = pessoaBLL.ObterPessoaPorID(id);
                mensagem = "Registro encontrado com sucesso!";
                obj.add(mensagem);
                obj.add(pessoaModel);
                break;
            }

        }

        Type listOfLeituraObject = new TypeToken<Object>(){}.getType();

        String json = new Gson().toJson(obj,listOfLeituraObject);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);


    }

}
