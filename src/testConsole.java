import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import readingTracker.com.br.BLL.PessoaBLL;
import readingTracker.com.br.factory.ConnectionFactory;
import readingTracker.com.br.model.LeituraModel;
import readingTracker.com.br.model.PessoaModel;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class testConsole {

    public static void main(String[] args) {

        PessoaModel oPessoaModel = new PessoaModel();
        PessoaBLL oPessoaBLL = new PessoaBLL();

        oPessoaModel.setEmail("hszm20941@outlook.com");
        oPessoaModel.setSenha("521197");

       // String resultado = oPessoaBLL.ObterPessoaPorEmailSenha(oPessoaModel);

        //System.out.println(resultado);



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
