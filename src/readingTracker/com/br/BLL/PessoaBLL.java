package readingTracker.com.br.BLL;

import javafx.collections.ArrayChangeListener;
import org.jetbrains.annotations.Contract;
import readingTracker.com.br.model.PessoaModel;
import readingTracker.com.br.dao.DaoPessoa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PessoaBLL {


        /*  Este método válida os campos do objeto que veio para verificar se
            ele condiz com os campos not null da tabela pessoa retornando uma string com
            informando que tal campo é requerido.
        */

    public String log; /*essa variável será a responsável por mostrar para os outros métodos, qual é o problema
                       que está ocorrendo na validação*/

    public enum MensagemErro {
        NULL,
        INVALID,
        OBJECT,
        DAO,
        NOTFOUND;
    }

    MensagemErro erro;

    public void setErro(MensagemErro MsgErro, String log){
        erro = MsgErro;
        this.log = log;
    }

    public String getErro(){
        String mensagem = "";
        switch(erro){

            case NULL: {
                mensagem = "O preenchimento do campo '" + this.log + "' é obrigatório!";
            }

            case INVALID: {
                mensagem = this.log + "é inválido!";
            }

            case DAO: {
                mensagem = "Não foi possível " + this.log + " o registro do usuário";
            }

            case OBJECT: {
                mensagem = "Objeto não correnponde a uma instância de " + this.log;

            }

            case NOTFOUND: {
                mensagem = this.log + " não encontrado!";
            }
            default:{
                mensagem = "sem erros aparentes!";
            }
        }

        return mensagem;
    }

     public boolean isObjetoValido(Object object){
         PessoaModel pessoa = null;
         if (object instanceof PessoaModel) {
             pessoa = (PessoaModel) object;
         } else {
             setErro(MensagemErro.OBJECT, "PessoaModel");
             return false;
         }
         /*
            esse método faz o caminho reveso, ele recebe como padrão o verdadeiro, e se todas as validações
            estiverem ok, ele continuará true até o final da validação
         */
         //começar validando campos que não podem ser nuláveis

         if(pessoa.getNome() == null){
             setErro(MensagemErro.NULL, "nome");
             return false;
         }

         if(pessoa.getSenha() == null){
             setErro(MensagemErro.NULL, "senha");
             return false;
         }

         //agora eu preciso validar se a data recebida pelo objeto condiz como uma data real (dd/mm/aaaa)


         try {
             SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
             formatoData.setLenient(false);
             Date dataParseada = formatoData.parse(pessoa.getDataNascimento());
             // se parseou, data válida (de verdade)!
         } catch (ParseException ex) {
             // data inválida! Não parseou.
             // então, não válido!
             setErro(MensagemErro.INVALID, "DataNascimento");
             return false;
         }

         // agora eu preciso validar se o email digitado é de fato um email!
         if(pessoa.getEmail() != null) {
             int arroba = pessoa.getEmail().indexOf('@');
             int ponto = pessoa.getEmail().indexOf('.');

             if (!(arroba > 0 && ponto > arroba)) {
                 setErro(MensagemErro.INVALID, "email");
                 return false;
             }
         } else {
             setErro(MensagemErro.NULL, "email");
             return false;
         }

         return true;

     }

     public boolean save(Object object){
         PessoaModel pessoa = null;
         if (object instanceof PessoaModel) {
             pessoa = (PessoaModel) object;
         } else {
             setErro(MensagemErro.OBJECT, "PessoaModel");
             return false;
         }

         UUID uuid = UUID.randomUUID();
         if(isObjetoValido(pessoa)){
             pessoa.setAtivo(true);
             pessoa.setApiId(uuid.toString());
             if(new DaoPessoa().Save(pessoa)){
                 return true;
             } else {
                 setErro(MensagemErro.DAO, "inserir");
             }
         } else {
             Logger.getLogger(DaoPessoa.class.getName()).log(Level.SEVERE, getErro());
         }

         return false;
     }

     public boolean update (Object object) {
         PessoaModel pessoa = null;
         if (object instanceof PessoaModel) {
             pessoa = (PessoaModel) object;
         } else {
             setErro(MensagemErro.OBJECT, "PessoaModel");
             return false;
         }

         if(isObjetoValido(pessoa)){
             if(new DaoPessoa().Update(pessoa)){
                 return true;
             } else {
                 setErro(MensagemErro.DAO, "alterar");
             }
         } else {
             Logger.getLogger(DaoPessoa.class.getName()).log(Level.SEVERE, getErro());
         }
         return false;
     }

    public boolean update (boolean status) {

        if(new DaoPessoa().Update(status)){
            return true;
        } else {
            setErro(MensagemErro.DAO, "alterar");
        }
        return false;
    }

    //get por id
    public Object get(int id){
        Object obj = new DaoPessoa().get(id);
        PessoaModel pessoa = null;
        if (obj instanceof PessoaModel) {
            pessoa = (PessoaModel) obj;
        } else {
            setErro(MensagemErro.OBJECT, "PessoaModel");
            return null;
        }

        if(isObjetoValido(obj)){
            return pessoa;
        } else {
            Logger.getLogger(DaoPessoa.class.getName()).log(Level.SEVERE, getErro());
        }
        return null;
    }
    //retorna uma lista com todos os registros
    public List<PessoaModel> get(){
        List<Object> lstObj = new DaoPessoa().get();
        PessoaModel pessoa = null;
        List<PessoaModel> lstPessoa = null;
        for(Object obj : lstObj) {
            if (obj instanceof PessoaModel) {
                if(isObjetoValido(obj)) {
                    lstPessoa.add((PessoaModel) obj);
                } else {
                    Logger.getLogger(DaoPessoa.class.getName()).log(Level.SEVERE, getErro());
                }
            } else {
                setErro(MensagemErro.OBJECT, "PessoaModel");
                return null;
            }
        }
        return lstPessoa;
    }

    //retorna uma lista com os usuários ativos ou inativos do sistema (campo statusPessoa)
    public List<PessoaModel> get(boolean status){
        List<Object> lstObj = new DaoPessoa().get(status);
        PessoaModel pessoa = null;
        List<PessoaModel> lstPessoa = null;
        for(Object obj : lstObj) {
            if (obj instanceof PessoaModel) {
                if(isObjetoValido(obj)) {
                    lstPessoa.add((PessoaModel) obj);
                } else {
                    Logger.getLogger(DaoPessoa.class.getName()).log(Level.SEVERE, getErro());
                }
            } else {
                setErro(MensagemErro.OBJECT, "PessoaModel");
                return null;
            }
        }
        return lstPessoa;
    }
    //retorna o id do usuário a partir do apiId
    public int get(String apiId){
        int id = new DaoPessoa().get(apiId);
        setErro(MensagemErro.NOTFOUND, "id");
        return id;
    }



    //TODO - Walter : método para validar usuário usando email e senha e retornar API_ID
        /*  Este método ira precisar receber como parametro email e senha, chamar o método
            associado na DAOPessoa (precisa criar sobrecarga de método de busca, mas baseado
            nestes parâmetros e retornar o API_ID associado a esta pessoa.
        */

// PS: Use os comentários que seguem os to do como descritivo para o método criado


}
