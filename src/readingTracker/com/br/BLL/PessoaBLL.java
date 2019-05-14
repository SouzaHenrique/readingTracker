package readingTracker.com.br.BLL;

import readingTracker.com.br.dao.DaoPessoa;
import readingTracker.com.br.model.PessoaModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PessoaBLL {

    private DaoPessoa oDaoPessoa = new DaoPessoa();


    public boolean isPessoaValid(PessoaModel oPessoaModel) {

        if (oPessoaModel.getNome().isEmpty()) {
            return false;
        }

        if (oPessoaModel.getSobrenome().isEmpty()) {
            return false;
        }

        if (oPessoaModel.getEmail().isEmpty()) {
            return false;
        }

        if (oPessoaModel.getSenha().isEmpty()) {
            return false;
        }

        //Valida formato de data
        try {
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
            formatoData.setLenient(false);
            Date dataParseada = formatoData.parse(oPessoaModel.getDataNascimento());

        } catch (ParseException ex) {
            return false;
        }

        // Valida email - regex
        final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(oPessoaModel.getEmail());
        if (!matcher.find()) {
            return false;
        }

         /*Se for um objeto valido e não tiver ID
         Trata-se de uma inserção então não geramos API ID nem mexemos no status
         */
        if (oPessoaModel.getId() == 0) {
            oPessoaModel.setAtivo(true);
            oPessoaModel.setApiId(UUID.randomUUID().toString());
        }

        return true;
    }

    public boolean save(PessoaModel oPessoaModel) {

        if (isPessoaValid(oPessoaModel)) {

            return oDaoPessoa.Save(oPessoaModel);

        } else {

            return false;

        }
    }

    public boolean update(PessoaModel oPessoaModel) {

        if (isPessoaValid(oPessoaModel)) {
            return oDaoPessoa.Update(oPessoaModel);
        }

        return false;
    }

    public PessoaModel ObterPessoaPorID(int id) {

        PessoaModel oPessoaModel = new PessoaModel();
        return oPessoaModel = (PessoaModel) oDaoPessoa.get(id);
    }

    public List<PessoaModel> ObterPessoas() {

        // Casting de um supertipo para um subtipo
        List<PessoaModel> lstPessoas = new ArrayList<>();
        return lstPessoas = (List<PessoaModel>) (List<?>) oDaoPessoa.get();
    }

    public PessoaModel ObterPessoaPorAPIID(String apiId) {

        PessoaModel oPessoaModel = new PessoaModel();

        return oPessoaModel = (PessoaModel) oDaoPessoa.get(apiId);

    }

    public String ObterAPIID(PessoaModel oPessoaModel){

        oPessoaModel = (PessoaModel) oDaoPessoa.get(oPessoaModel.getEmail(), oPessoaModel.getSenha());

        String API_ID = oPessoaModel.getApiId();

        if(!API_ID.isEmpty() && !API_ID.equals(null)){
            return API_ID;
        }

        return null;
    }

    public PessoaModel ObterLogin (String login, String senha){
        PessoaModel oPessoaModel = new PessoaModel();
        return oPessoaModel = (PessoaModel) oDaoPessoa.get(login,senha);
    }

}
