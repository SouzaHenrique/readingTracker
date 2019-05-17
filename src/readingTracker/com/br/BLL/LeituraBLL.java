package readingTracker.com.br.BLL;

import readingTracker.com.br.model.LeituraModel;
import readingTracker.com.br.dao.DaoLeitura;
import readingTracker.com.br.model.PessoaModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LeituraBLL {

    private DaoLeitura daoLeitura = new DaoLeitura();

   /* Este método basicamente valida com IF's se existem os campos na model que está vindo
      e que são obrigatórios na tabela (ver tabela) e também ele reune a chamada dos métodos
      que acessam a BLL
    */
    public boolean verificaCampos (LeituraModel oLeitura, String Action) {

        if(Action.contains("add")){
            if(oLeitura.getId_Livro() == 0) {
                return false;
            }

            if(oLeitura.getId_Leitor() == 0){
                return false;
            }
        }

        try {
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        formatoData.setLenient(false);
        Date dataParseada = formatoData.parse(oLeitura.getDataterminoPlanejado());

        } catch (ParseException ex) {
            return false;
        }

        //Se for inserção sempre inicia como ativa
        if(oLeitura.getId() == 0){
            oLeitura.setStatusLeitura(1);
        }

        return true;
    }

    public boolean novaLeitura(LeituraModel oLeitura){

        String action = "";

        if(oLeitura.getId() == 0){
            action = "add";
        }else{
            action = "edit";
        }

        if (verificaCampos(oLeitura, action)){
            return daoLeitura.Save(oLeitura);
        }else{
            return false;
        }

    }

    public boolean editarLeitura(LeituraModel oLeitura){

        String action = "";

        if(oLeitura.getId() == 0){
            action = "add";
        }else{
            action = "edit";
        }

        if (verificaCampos(oLeitura,action)) {
            return daoLeitura.Update(oLeitura);
        }else{
            return false;
        }

    }

    public List<LeituraModel> listarLeituras(int id){

        List<LeituraModel> lstLeitura = new ArrayList<LeituraModel>();

        //Casting de supertipo para subtipo
        lstLeitura = (List<LeituraModel>) (List<?>) daoLeitura.getListById(id);

        if(!lstLeitura.isEmpty()){

            return lstLeitura;

        }else{
            return null;
        }

    }

    public LeituraModel obterLeitura(int id){

        LeituraModel oLeituraModel = new LeituraModel();

        if(id != 0){
            oLeituraModel = (LeituraModel) daoLeitura.get(id);
            return oLeituraModel;
        }

        return null;

    }

}