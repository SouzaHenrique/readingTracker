package readingTracker.com.br.controller;

import readingTracker.com.br.BLL.LeituraBLL;
import readingTracker.com.br.dao.DaoLeitura;
import readingTracker.com.br.model.LeituraModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import readingTracker.com.br.model.opcaoLeitura;

import static java.lang.Integer.*;

public class LeituraController extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LeituraModel oLeitura = new LeituraModel();
        LeituraBLL oBll = new LeituraBLL();
        String op = request.getParameter("opcao");



        if(op.equals(opcaoLeitura.CRIAR)) {
            //Opção 1 - criar nova leitura
            try {
                if (oBll.verificaCampos(oLeitura)) {
                    if (oBll.novaLeitura(oLeitura)) {
                       //devolver mensagem / dados

                    } else {
                        //devolver mensagem / dados
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(LeituraController.class.getName()).log(Level.SEVERE, null, ex);
                //devolver mensagem / dados
            }

        }

        if (op.equals(opcaoLeitura.EDITAR)){
                //Opção 2 - editar leitura
            if(oBll.verificaCampos(oLeitura)){
                if(oBll.editarLeitura(oLeitura)){
                    //devolver mensagem / dados
                }else{
                    //devolver mensagem / dados
                }
            }else{
                //devolver mensagem / dados

            }

        }

        if (op.equals(opcaoLeitura.LISTAR)){
            //Opção 3 - listar leituras do usuario
            if (oBll.verificaCampos(oLeitura)){
                List<Object> lstLeitura = new ArrayList<>();
                lstLeitura = oBll.listarLeituras(oLeitura.getId_Leitor());

                //TODO RETORNO DE LISTA

            }else{
                //devolver mensagem / dados
            }

        }

        else if (op.equals(opcaoLeitura.EXCLUIR)){

        }
        else {

        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
