package readingTracker.com.br.BLL;

import readingTracker.com.br.model.LeituraModel;
import readingTracker.com.br.dao.DaoLeitura;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LeituraBLL {

    private DaoLeitura daoLeitura = new DaoLeitura();

   /* Este método basicamente valida com IF's se existem os campos na model que está vindo
      e que são obrigatórios na tabela (ver tabela) e também ele reune a chamada dos métodos
      que acessam a BLL
    */
    public boolean verificaCampos (LeituraModel oLeitura) {
            if(oLeitura.getId_Livro() != 0 && oLeitura.getId_Leitor() != 0 && oLeitura.getStatusLeitura() != 0) {
                return true;
            }

            return false;
    }

    public boolean novaLeitura(LeituraModel oLeitura){

        try {
            if (daoLeitura.Save(oLeitura)){
                return true;
            }
        }catch (Exception ex){
            Logger.getLogger(LeituraBLL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    public boolean editarLeitura(LeituraModel oLeitura){
        try {
            if (daoLeitura.Update(oLeitura)) {
                return true;
            }
        }catch (Exception ex){
            Logger.getLogger(LeituraBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<LeituraModel> listarLeituras(int id){
        List<LeituraModel> lstLeitura = new ArrayList<LeituraModel>();

        lstLeitura = daoLeitura.getListById(id);

        if(lstLeitura.equals(null)){
            return null;
        }

        return lstLeitura;

    }

}