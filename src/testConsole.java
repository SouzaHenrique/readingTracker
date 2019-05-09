import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import readingTracker.com.br.factory.ConnectionFactory;
import readingTracker.com.br.model.LeituraModel;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class testConsole {

    public static void main(String[] args) {

        /*Type listOfTestObject = new TypeToken<List<TestObject>>(){}.getType();
        String s = gson.toJson(list, listOfTestObject);
        List<TestObject> list2 = gson.fromJson(s, listOfTestObject);

        Gson gson = new Gson();

        List<LeituraModel> lstLeitura =  retListaLeitura();
        Type listOfLeituraObject = new TypeToken<List<LeituraModel>>(){}.getType();

        String myJson = gson.toJson(lstLeitura,listOfLeituraObject);


        System.out.println(myJson);*/

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
