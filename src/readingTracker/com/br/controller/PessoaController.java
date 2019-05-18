package readingTracker.com.br.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import readingTracker.com.br.BLL.PessoaBLL;
import readingTracker.com.br.model.PessoaModel;
import readingTracker.com.br.model.requestStatus;

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

        PessoaBLL oPessoaBLL = new PessoaBLL();

        int id = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")): 0 ;
        String nome = request.getParameter("nome");
        String sobrenome = request.getParameter("sobrenome");
        String dataNascimento = request.getParameter("dataNascimento");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String action = request.getParameter("action");

        PessoaBLL pessoaBLL = new PessoaBLL();
        List<Object> obj = new ArrayList<>();
        requestStatus oRequestStatus = new requestStatus();
        String mensagem;


        if(action != null) {
            switch (action) {

                case "create": {

                    PessoaModel pessoaModel = new PessoaModel(0,nome,sobrenome,dataNascimento,email,senha,"",false);

                    if (pessoaBLL.save(pessoaModel)) {
                        oRequestStatus.setRequestStatus(true);
                    }

                    obj.add(oRequestStatus);
                    break;
                }

                case "edit": {

                    PessoaModel pessoaModel = new PessoaModel(id,nome,sobrenome,dataNascimento,email,senha,"",false);

                    if (pessoaBLL.update(pessoaModel)) {
                        oRequestStatus.setRequestStatus(true);
                    }
                    obj.add(oRequestStatus);

                    break;
                }

                case "listar": {

                    List<PessoaModel> lstPessoa = pessoaBLL.ObterPessoas();

                    if(!lstPessoa.isEmpty()){
                        oRequestStatus.setRequestStatus(true);
                    }

                    obj.add(oRequestStatus);
                    obj.add(lstPessoa);
                    break;
                }

                case "obterPorID": {

                    PessoaModel oPessoaModel = pessoaBLL.ObterPessoaPorID(id);

                    if(!oPessoaModel.equals(null)){
                        oRequestStatus.setRequestStatus(true);
                    }

                    obj.add(oRequestStatus);
                    obj.add(oPessoaModel);

                    break;
                }

                case "obterId":{

                    PessoaModel pessoaModel = new PessoaModel(0,nome,sobrenome,dataNascimento,email,senha,"",false);

                    int Id = pessoaBLL.ObterPessoaPorEmailSenha(pessoaModel);
                    if(Id != 0){
                        oRequestStatus.setRequestStatus(true);
                    }

                    obj.add(oRequestStatus);
                    obj.add(Id);


                    break;
                }

            }

        } else {
            mensagem = "inválido!";
            obj.add(mensagem);
        }

        Type listOfLeituraObject = new TypeToken<Object>(){}.getType();

        String json = new Gson().toJson(obj,listOfLeituraObject);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);


    }

}
