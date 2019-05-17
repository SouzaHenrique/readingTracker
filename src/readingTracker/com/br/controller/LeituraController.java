package readingTracker.com.br.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import readingTracker.com.br.BLL.LeituraBLL;
import readingTracker.com.br.BLL.LivroBLL;
import readingTracker.com.br.BLL.PessoaBLL;
import readingTracker.com.br.model.LeituraModel;
import readingTracker.com.br.model.LivroModel;
import readingTracker.com.br.model.PessoaModel;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

public class LeituraController extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        LeituraModel oLeitura = new LeituraModel();
        LeituraBLL oLeituraBLL = new LeituraBLL();
        String msg = "";
        Gson gson = new Gson();
        List<Object> obj = new ArrayList<>();

        //Busca dados do usuario pelo APID
        /* Desativado nesta versao da API por Henrique Souza.
        try {
            HttpSession session = request.getSession();
            String APID = ((String) session.getAttribute("APID"));
            oPessoa = oPessoaBLL.ObterPessoaPorAPIID(APID);
        }catch (Exception ex){
            Logger.getLogger(LeituraController.class.getName()).log(Level.SEVERE, null, ex);
        }
        */


        //Obtem valores do request
        String action = request.getParameter("action");
        String idLeitura = request.getParameter("id");
        String idLeitor =  request.getParameter("idLeitor");
        String idLivro = request.getParameter("idLivro");
        String statusLeitura = request.getParameter("statusLeitura");
        String paginasLidas = request.getParameter("paginasLidas");
        String dataTerminoPlanejado = request.getParameter("dataTerminoPlanejado");

        //Obtem dados e popula objeto oLeituraModel
        if(tryParseInt(idLeitura)){
            oLeitura.setId(Integer.parseInt(idLeitura));
        }else{
            oLeitura.setId(0);
        }

        if(tryParseInt(idLeitor)){
            oLeitura.setId_Leitor(Integer.parseInt(idLeitor));
        }else{
            oLeitura.setId_Leitor(0);
        }

        if(tryParseInt(idLivro)){
            oLeitura.setId_Livro(Integer.parseInt(idLivro));
        }

        if(tryParseInt(statusLeitura)){
            oLeitura.setStatusLeitura(Integer.parseInt(statusLeitura));
        }else{
            oLeitura.setStatusLeitura(0);
        }

        if(tryParseInt(paginasLidas)){
            oLeitura.setPaginasLidas(Integer.parseInt(paginasLidas));
        }else{
            oLeitura.setPaginasLidas(0);
        }

        oLeitura.setDataterminoPlanejado(dataTerminoPlanejado);



        //CRUD de leitura de acordo com campo action informado
        if (action != null  && !action.isEmpty()) {
            switch (action) {

                case "create": {

                    /* Desativado nesta versão da API por Henrique Souza.

                    //Verificar se livro existe na base e insere (caso inexistente)
                    LivroBLL olivroBLL = new LivroBLL();
                    LivroModel oLivro = new LivroModel();
                    String titulo = request.getParameter("title");

                    oLivro = (LivroModel) olivroBLL.selecionaLivro(titulo);

                    if (oLivro.getId() == 0) {
                        oLivro.setTitulo(titulo);
                        oLivro.setAnoPublicacao(request.getParameter("publishedDate"));
                        oLivro.setAutor(request.getParameter("authors"));
                        oLivro.setEditora(request.getParameter("publisher"));
                        oLivro.setQuantidadePaginas(Integer.parseInt(request.getParameter("pageCount")));

                        olivroBLL.novoLivro(oLivro);

                        //Setando ID do livro (caso não exista na base)
                        oLivro =  (LivroModel) olivroBLL.selecionaLivro(titulo);
                        oLeitura.setId_Livro(oLivro.getId());

                    } else {
                        //Setando ID do livro (caso exista na base)
                        oLeitura.setId_Livro(oLivro.getId());
                    }*/

                    //Inserção de nova leitura
                    if (oLeituraBLL.novaLeitura(oLeitura)) {
                        msg = "Leitura cadastrada com sucesso!";
                    } else {
                        msg = "Não foi possivel cadastrar esta leitura!";
                    }

                    obj.add(msg);
                    break;

                }

                case "edit": {

                    /* Desativado nesta versão da API por Henrique Souza.
                    //Caso edição de leitura: o ID do livro e Leitura é retornado da base e invisivel no dashboard
                    oLeitura.setId_Livro(Integer.parseInt(request.getParameter("id_Livro")));
                    oLeitura.setId(Integer.parseInt(request.getParameter("id_Leitura"))); */
                    if (oLeituraBLL.editarLeitura(oLeitura)) {
                        msg = "Leitura editada com sucesso!";
                    } else {
                        msg = "Não foi possivel editar esta leitura!";
                    }

                    obj.add(msg);
                    break;
                }

                case "listar": {
                    List<LeituraModel> lstLeitura;

                    //Consulta de Leituras por ID do usuario
                    lstLeitura = oLeituraBLL.listarLeituras(oLeitura.getId_Leitor());

                    Type listOfLeituraObject = new TypeToken<List<LeituraModel>>(){}.getType();

                    String myJson = gson.toJson(lstLeitura,listOfLeituraObject);

                    obj.add(myJson);

                    break;
                }

                case "obterPorId": {

                    //Obtem um objeto leitura
                    oLeitura = oLeituraBLL.obterLeitura(oLeitura.getId());
                    obj.add(oLeitura);

                    break;
                }

                default:{
                    msg = "Ação invalida!";
                    obj.add(msg);
                }

            }
        }else{
            msg = "falta açao!";
            obj.add(msg);
        }

        //Serializa retorno e o envia
        String myJson = gson.toJson(obj);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(myJson);

    }

    private boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
