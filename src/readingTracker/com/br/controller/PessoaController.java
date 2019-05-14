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

        PessoaBLL oPessoaBLL = new PessoaBLL();
        PessoaModel oPessoaModel = new PessoaModel();

        oPessoaModel = oPessoaBLL.ObterPessoaPorID(1);

        String json = new Gson().toJson(oPessoaModel);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);


    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        PessoaModel oPessoaModel = new PessoaModel();
        PessoaBLL oPessoaBLL = new PessoaBLL();


        int id = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")): 0 ;
        String nome = request.getParameter("nome");
        String sobrenome = request.getParameter("sobrenome");
        String dataNascimento = request.getParameter("dataNascimento");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String apiId = request.getParameter("apiId");
        String statusPessoa = request.getParameter("isAtivo");
        Boolean isAtivo = null;

        if(statusPessoa != null){
            isAtivo = statusPessoa != "0";
        }

        String action = request.getParameter("action");

        PessoaModel pessoaModel = new PessoaModel(id,nome,sobrenome,dataNascimento,email,senha,apiId,isAtivo);
        PessoaBLL pessoaBLL = new PessoaBLL();
        List<Object> obj = new ArrayList<>();
        String mensagem = "";

        if(action != null) {
            switch (action) {

                case "create": {
                    if (pessoaBLL.save(pessoaModel)) {
                        mensagem = "registro inserido com sucesso!";
                    } else {
                        mensagem = "Erro ao inserir dados";
                    }
                    obj.add(mensagem);
                    break;
                }

                case "edit": {

                    if (pessoaBLL.update(pessoaModel)) {
                        mensagem = "Alteração feita com sucesso!";
                    } else {
                        mensagem = "Erro ao alterar dados";
                    }
                    obj.add(mensagem);
                    break;
                }

                case "listar": {
                    List<PessoaModel> lstPessoa = pessoaBLL.ObterPessoas();
                    mensagem = "Listagem de todos os usuarios";
                    obj.add(mensagem);
                    obj.add(lstPessoa);
                    break;
                }

                case "obterPorID": {
                    pessoaModel = pessoaBLL.ObterPessoaPorID(id);
                    mensagem = "Registro encontrado com sucesso!";
                    obj.add(mensagem);
                    obj.add(pessoaModel);
                    break;
                }

            }

        } else {
            mensagem = "Não especificado parâmetro de ação";
        }

        Type listOfLeituraObject = new TypeToken<Object>(){}.getType();

        String json = new Gson().toJson(obj,listOfLeituraObject);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);


    }

}
