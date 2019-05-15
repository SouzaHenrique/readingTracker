import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import readingTracker.com.br.BLL.LivroBLL;
import readingTracker.com.br.BLL.PessoaBLL;
import readingTracker.com.br.factory.ConnectionFactory;
import readingTracker.com.br.model.LeituraModel;
import readingTracker.com.br.model.LivroModel;
import readingTracker.com.br.model.PessoaModel;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class testConsole {

    public static void main(String[] args) {

      LivroModel oLivroModel = new LivroModel();
      LivroBLL oLivroBLL = new LivroBLL();

      oLivroModel.setTitulo("Harry Potter");

      List<LivroModel> lstLivros = oLivroBLL.buscaLivros(oLivroModel.getTitulo());

      if(!lstLivros.isEmpty()){

          for(LivroModel item : lstLivros){
              System.out.println(item.getTitulo());
          }

      }



    }

    private static List<LeituraModel> retListaLeitura(){

        List<LeituraModel> lstLeituras = new ArrayList<LeituraModel>(){
            {
                add(new LeituraModel(2,2,3,1,80,"09052019"));
                add(new LeituraModel(1,2,3,1,80,"09052019"));
                add(new LeituraModel(3,2,3,1,80,"09052019"));
                add(new LeituraModel(4,2,3,1,80,"09052019"));
            }
        };

        return   lstLeituras;
    }



}
