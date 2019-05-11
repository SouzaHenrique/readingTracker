package readingTracker.com.br.BLL;

import com.json.parsers.JSONParser;
import com.json.parsers.JsonParserFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import readingTracker.com.br.model.LivroModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BooksBLL {
    private static String url = "";
    private final String key = "AIzaSyBTXFc-POH9lOeugbtYQFjDiQgIECni3Wk";

    private static BooksBLL instance;

    private CloseableHttpClient clienteHTTP;

    private BooksBLL() {
        this.clienteHTTP = HttpClients.createDefault();
    }



    public static BooksBLL getInstance(){
        if(instance == null){
            instance = new BooksBLL();
        }
        return instance;
    }

    public String getLink(String query){
        query = query.replace(" ","+");

        String url = "https://www.googleapis.com/books/v1/volumes?q=" + query
                +"&download=epub&filter=ebooks&maxAllowedMaturityRating=mature&printType=books&projection=full&showPreorders=false"
                + "&fields=items(volumeInfo(authors%2CpageCount%2CpublishedDate%2Cpublisher%2Ctitle))&key=";
        url += this.key;
        return url;
    }

    private String doRequest(String path){
        String responseBody = null;

        try{
            HttpGet HttpGet = new HttpGet(getLink(path));


            ResponseHandler<String> responseHandler = new ResponseHandler<String>(){
                @Override
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();

                    if(status >= 200 && status < 300){
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unxpected response status: " + status);
                    }
                }
            };

            responseBody = this.clienteHTTP.execute(HttpGet, responseHandler);

        }catch (IOException e){
            Logger.getLogger(BooksBLL.class.getName()).log(Level.SEVERE, null, e);
        }
        return responseBody;
    }

    public List<LivroModel> doGetList(String query){
        String json = doRequest(query);

        JsonParserFactory factory = JsonParserFactory.getInstance();
        JSONParser parser = factory.newJsonParser();
        Map jsonMap = parser.parseJson(json);

        List<LivroModel> lstLivro = new ArrayList<>();

        jsonMap.forEach((Object key, Object value) -> {
            ArrayList<Map> items = (ArrayList<Map>) value;

            for (Map item : items) {
                Map volumeInfo = (Map) item.get("volumeInfo");


                LivroModel modelLivro = new LivroModel();
                modelLivro.setAnoPublicacao(volumeInfo.get("publishedDate") != null ? (String) volumeInfo.get("publishDate") : null);
                modelLivro.setEditora(volumeInfo.get("publisher") != null ? (String) volumeInfo.get("publisher") : null);
                modelLivro.setQuantidadePaginas(volumeInfo.get("pageCount") != null ? Integer.parseInt((String)volumeInfo.get("pageCount")) : null);
                modelLivro.setTitulo(volumeInfo.get("title") != null ? (String) volumeInfo.get("title") : null);
                ArrayList<String> autor = (ArrayList<String>) volumeInfo.get("authors");
                modelLivro.setAutor(autor.get(0) != null ? (String) autor.get(0) : null);
                lstLivro.add(modelLivro);
            }
        });

        return lstLivro;
    }









}
