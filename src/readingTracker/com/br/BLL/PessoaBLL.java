package readingTracker.com.br.BLL;

import readingTracker.com.br.model.PessoaModel;
import readingTracker.com.br.dao.DaoPessoa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PessoaBLL {


        /*  Este método válida os campos do objeto que veio para verificar se
            ele condiz com os campos not null da tabela pessoa retornando uma string com
            informando que tal campo é requerido.
        */
     public int log; /*essa variável será a responsável por mostrar para os outros métodos, qual é o problema
                       que está ocorrendo na validação*/

     public String msgErro(int i){
         String mensagem = "";

         switch(i){
             case 1 :{
                 mensagem = "O preenchimento do campo 'nome' é obrigatório!";
                 break;
             }

             case 2: {
                 mensagem = "O preenchimento do campo 'senha' é obrigratório!";
             }

             case 3: {
                 mensagem = "DataNascimento inválida!";
             }

             case 4: {
                 mensagem = "Email inválido!";
             }

             case 5: {
                 mensagem = "O preenchimento do campo 'emaill' é obrigatorio";
             }

             case 6: {
                 mensagem = "Objeto não corresponde a uma instância de DaoPessoa";
             }
             case 7: {
                 mensagem = "Não foi possível adicionar o registro do usuário!";
             }
             default:{

             }
         }
         return mensagem;
     }
     public boolean isObjetoValido(Object object){
         PessoaModel pessoa = null;
         if (object instanceof PessoaModel) {
             pessoa = (PessoaModel) object;
         } else {
             this.log = 6;
             return false;
         }
         /*
            esse método faz o caminho reveso, ele recebe como padrão o verdadeiro, e se todas as validações
            estiverem ok, ele continuará true até o final da validação
         */
         //começar validando campos que não podem ser nuláveis

         if(pessoa.getNome() == null){
             this.log = 1;
             return false;
         }

         if(pessoa.getSenha() == null){
             this.log = 2;
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
             this.log = 3;
             return false;
         }

         // agora eu preciso validar se o email digitado é de fato um email!
         if(pessoa.getEmail() != null) {
             int arroba = pessoa.getEmail().indexOf('@');
             int ponto = pessoa.getEmail().indexOf('.');

             if (!(arroba > 0 && ponto > arroba)) {
                 this.log = 4;
                 return false;
             }
         } else {
             this.log = 5;
             return false;
         }

         return true;

     }

     public boolean save(Object object){
         PessoaModel pessoa = null;
         if (object instanceof PessoaModel) {
             pessoa = (PessoaModel) object;
         } else {
             this.log = 6;
             return false;
         }

         UUID uuid = UUID.randomUUID();
         if(isObjetoValido(pessoa)){
             pessoa.setAtivo(true);
             pessoa.setApiId(uuid.toString());
             if(new DaoPessoa().Save(pessoa)){
                 return true;
             } else {
                 this.log = 7;
             }
         } else {
             Logger.getLogger(DaoPessoa.class.getName()).log(Level.SEVERE,  msgErro(this.log));
         }

         return false;
     }

     public boolean update (Object object){
         return false;
     }
    //TODO - Walter : método que chama a DAOPessoa para persistir um objeto de pessoa e retornar um bool
        /*  Este método apenas encapsula o método da DAOPessoa para persistir o objeto e deve
            ser chamado pelo método acima.
        */

    //TODO - Walter : método para validar usuário usando email e senha e retornar API_ID
        /*  Este método ira precisar receber como parametro email e senha, chamar o método
            associado na DAOPessoa (precisa criar sobrecarga de método de busca, mas baseado
            nestes parâmetros e retornar o API_ID associado a esta pessoa.
        */

// PS: Use os comentários que seguem os to do como descritivo para o método criado


}
