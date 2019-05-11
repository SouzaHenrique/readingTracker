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

        //Variaveis uteis
        LeituraModel oLeitura = new LeituraModel();
        LeituraBLL oLeituraBLL = new LeituraBLL();
        PessoaBLL oPessoaBLL= new PessoaBLL();
        PessoaModel oPessoa = new PessoaModel();
        String msg = "";

        //Obter ação do usuario
        String action = request.getParameter("action");


        //Busca dados do usuario pelo APID
        try {
            HttpSession session = request.getSession();
            String APID = ((String) session.getAttribute("APID"));
            oPessoa = oPessoaBll.ObterPessoaPorAPIID(APID));
        }catch (Exception ex){
            Logger.getLogger(LeituraController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Dados da leitura
        oLeitura.setStatusLeitura(Integer.parseInt(request.getParameter("statusLeitura")));
        oLeitura.setPaginasLidas(Integer.parseInt(request.getParameter("paginasLidas")));
        oLeitura.setDataterminoPlanejado(request.getParameter("DataterminoPlanejado"));


        //CRUD de leitura de acordo com campo action informado
        if (action != null  && !action.isEmpty()) {
            switch (action) {

                case "create": {

                    //Verificar se livro existe na base e insere (caso inexistente)
                    LivroBLL olivroBLL = new LivroBLL();
                    LivroModel oLivro = new LivroModel();
                    String titulo = request.getParameter("title");

                    oLivro = olivroBLL.selecionaLivro(titulo);

                    if (oLivro.getId() == 0) {
                        oLivro.setTitulo(titulo);
                        oLivro.setAnoPublicacao(request.getParameter("publishedDate"));
                        oLivro.setAutor(request.getParameter("authors"));
                        oLivro.setEditora(request.getParameter("publisher"));
                        oLivro.setQuantidadePaginas(Integer.parseInt(request.getParameter("pageCount")));

                        olivroBLL.novoLivro(oLivro);

                        //Setando ID do livro (caso não exista na base)
                        oLivro = olivroBLL.selecionaLivro(titulo);
                        oLeitura.setId_Livro(oLivro.getId());

                    } else {
                        //Setando ID do livro (caso exista na base)
                        oLeitura.setId_Livro(oLivro.getId());
                    }

                    //Inserção de nova leitura
                    if (oLeituraBLL.verificaCampos(oLeitura)) {
                        if (oLeituraBLL.novaLeitura(oLeitura)) {
                            msg = "Leitura cadastrada com sucesso!";
                        } else {
                            msg = "Não foi possivel cadastrar esta leitura!";
                        }
                    } else {
                        msg = "Dados invalidos ou nulo!";
                    }

                    //Mensgaem de retorno json
                    String json = new Gson().toJson(msg);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                    break;

                }

                case "edit": {

                    //Caso edição de leitura: o ID do livro e Leitura é retornado da base e invisivel no dashboard
                    oLeitura.setId_Livro(Integer.parseInt(request.getParameter("id_Livro")));
                    oLeitura.setId(Integer.parseInt(request.getParameter("id_Leitura")));

                    if (oLeituraBLL.verificaCampos(oLeitura)) {
                        if (oLeituraBLL.editarLeitura(oLeitura)) {
                            msg = "Leitura editada com sucesso!";
                        } else {
                            msg = "Não foi possivel editar esta leitura!";
                        }
                    } else {
                        msg = "Dados invalidos ou nulo!";
                    }

                    //Mensgaem de retorno json
                    String json = new Gson().toJson(msg);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                    break;
                }

                case "listar": {
                    List<LeituraModel> lstLeitura = new ArrayList<LeituraModel>();

                    //Consulta de Leituras por ID do usuario
                    lstLeitura = oLeituraBLL.listarLeituras(oLeitura.getId_Leitor());

                    //Conversão da Lista para Json
                    Gson gson = new Gson();

                    Type listOfLeituraObject = new TypeToken<List<LeituraModel>>(){}.getType();

                    String myJson = gson.toJson(lstLeitura,listOfLeituraObject);

                    //Retorno de lista Json
                    response.setContentType("application/json");
                    response.setCharacterEncoding("utf-8");
                    response.getWriter().write(myJson);

                    break;
                }

                case "obter": {
                    //TODO o que fazer em case obter?
                    break;
                }
                default:{
                    msg = "Ação invalida!";
                    response.setContentType("text/html");
                    PrintWriter out = response.getWriter();
                    out.println("falta açao!");
                }

            }
        }else{
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("falta açao!");
        }

    }

}
