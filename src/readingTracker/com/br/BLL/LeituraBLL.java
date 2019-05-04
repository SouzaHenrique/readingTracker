package readingTracker.com.br.BLL;

import readingTracker.com.br.model.LeituraModel;
import readingTracker.com.br.dao.DaoLeitura;

import java.util.ArrayList;
import java.util.List;

public class LeituraBLL {

    private DaoLeitura daoLeitura = new DaoLeitura();

   /* Este método basicamente valida com IF's se existem os campos na model que está vindo
      e que são obrigatórios na tabela (ver tabela) e também ele reune a chamada dos métodos
      que acessam a BLL
    */
    public boolean verificaCampos (LeituraModel oLeitura) {
            if (oLeitura.getId_Livro() != 0 || oLeitura.getId_Leitor() != 0 || oLeitura.getStatusLeitura() != 0) {
                return true;
            }
            return false;
    }

    public boolean novaLeitura(LeituraModel oLeitura){

        if (daoLeitura.Save(oLeitura)){
            return true;
        }

        return false;

    }

    public boolean editarLeitura(LeituraModel oLeitura){

        if(daoLeitura.Update(oLeitura)){
            return true;
        }

        return false;
    }

    public List<Object> listarLeituras(int id){
        List<Object> lstLeitura = new ArrayList<>();

        lstLeitura = daoLeitura.getListById(id);

        if(lstLeitura.equals(null)){
            return null;
        }

        return lstLeitura;

    }

}