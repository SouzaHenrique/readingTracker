package java.com.br.dao;

import java.com.br.dao.Dao;
import java.com.br.dao.DaoTenant;
import java.com.br.factory.ConnectionFactory;
import java.com.br.model.PessoaModel;
import java.com.br.model.TenantModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoPessoa implements Dao {

    @Override
    public boolean Save(Object object){

        PessoaModel pessoa = null;
        if (object instanceof PessoaModel) {
            pessoa = (PessoaModel) object;
        } else {
            return false;
        }

        String comando = "insert into pessoa(nome,sobrenome,dataNascimento,email,senha,statusPessoa,apiId) values (?,?,?,?,?,?,?)";

        try (Connection con = new ConnectionFactory().getConnection()) {
            try {

                con.setAutoCommit(false);

                PreparedStatement stmt = con.prepareStatement(comando);
                stmt.setString(1, pessoa.getNome());
                stmt.setString(2, pessoa.getSobrenome());
                stmt.setString(3, pessoa.getDataNascimento());
                stmt.setString(4, pessoa.getEmail());
                stmt.setString(5, pessoa.getSenha());
                stmt.setInt(6, pessoa.getStatusPessoa());
                stmt.setString(7, pessoa.getApiId());
                stmt.execute();
                stmt.close();
                /*----------------------------------*/

                comando = "select max(id) from pessoa";
                stmt = con.prepareStatement(comando);
                ResultSet rs = stmt.executeQuery();
                if(rs.next()){
                    pessoa.setId(rs.getInt("id"));
                    new DaoTenant().Save(pessoa);
                    con.commit();
                } else {
                    con.rollback();
                    return false;
                }

                rs.close();
                stmt.close();

                return true;
            } catch (SQLException ex) {
                Logger.getLogger(DaoPessoa.class.getName()).log(Level.SEVERE, null, ex + "Erro ao adicionar os dados");
                con.rollback();
            } finally {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoPessoa.class.getName()).log(Level.SEVERE, null, ex + "Erro ao adicionar os dados");
        }
        return false;
    }

    @Override
    public boolean Update(Object object) {
        PessoaModel pessoa = null;
        if (object instanceof PessoaModel) {
            pessoa = (PessoaModel) object;
        } else {
            return false;
        }
        String comando = "update pessoa set nome = ? ,sobrenome = ? ,dataNascimento = ?,email = ?,senha = ?,statusPessoa = ?, apiId = ? where id = ?";

        try {
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(comando);
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getSobrenome());
            stmt.setString(3, pessoa.getDataNascimento());
            stmt.setString(4, pessoa.getEmail());
            stmt.setString(5, pessoa.getSenha());
            stmt.setInt(6, pessoa.getStatusPessoa());
            stmt.setString(7, pessoa.getApiId());
            stmt.setInt(8,pessoa.getId());
            stmt.execute();
            stmt.close();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DaoPessoa.class.getName()).log(Level.SEVERE, null, ex + "Erro ao alterar os dados");

        }
        return false;
    }

    @Override
    public boolean Delete(int id) {
        String comando = "delete from pessoa where id = ?";

        try {
            PreparedStatement stmt = new ConnectionFactory().getConnection().prepareStatement(comando);
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DaoPessoa.class.getName()).log(Level.SEVERE, null, ex + "Erro ao deletar os dados");
        }

        return false;
    }

    @Override
    public Object get(int id) {
        PessoaModel pessoa;
        String comando = "select * from pessoa where id = ?";

        try{
            PreparedStatement stmt = new ConnectionFactory().getConnection().prepareStatement(comando);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                pessoa = new PessoaModel(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        rs.getString("dataNascimento"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getInt("statusPessoa"),
                        rs.getString("apiId")
                );
                return pessoa;
            }
        }catch(SQLException ex){
            Logger.getLogger(DaoPessoa.class.getName()).log(Level.SEVERE, null, ex + "Erro ao executar busca");
        }

        return null;
    }

    @Override
    public List<Object> get() {
        List<Object> lstPessoa = new ArrayList<>();
        String comando = "select * from pessoa";

        try{
            PreparedStatement stmt = new ConnectionFactory().getConnection().prepareStatement(comando);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PessoaModel pessoa = new PessoaModel(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        rs.getString("dataNascimento"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getInt("statusPessoa"),
                        rs.getString("apiId")
                );
                lstPessoa.add(pessoa);
            }

            return lstPessoa;
        }catch(SQLException ex){
            Logger.getLogger(DaoPessoa.class.getName()).log(Level.SEVERE, null, ex + "Erro ao executar busca");
        }

        return null;
    }
}
