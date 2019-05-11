package readingTracker.com.br.dao;

import readingTracker.com.br.factory.ConnectionFactory;
import readingTracker.com.br.model.PessoaModel;
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
                stmt.setBoolean(6, pessoa.getAtivo());
                stmt.setString(7, pessoa.getApiId());
                stmt.execute();
                stmt.close();
                /*----------------------------------*/

                comando = "select max(id) id from pessoa";
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

        int status = pessoa.getAtivo() ? 1 : 0;
        String comando = "update pessoa set nome = ? ,sobrenome = ? ,dataNascimento = ?,email = ?,senha = ?, statusPessoa = ? where id = ?";

        try {
            Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(comando);
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getSobrenome());
            stmt.setString(3, pessoa.getDataNascimento());
            stmt.setString(4, pessoa.getEmail());
            stmt.setString(5, pessoa.getSenha());
            stmt.setInt(6, status);
            stmt.setInt(7,pessoa.getId());
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
                        rs.getString("apiId"),
                        rs.getBoolean("statusPessoa")
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
                        rs.getString("apiId"),
                        rs.getBoolean("statusPessoa")
                );
                lstPessoa.add(pessoa);
            }

            return lstPessoa;
        }catch(SQLException ex){
            Logger.getLogger(DaoPessoa.class.getName()).log(Level.SEVERE, null, ex + "Erro ao teornar lista");
        }

        return null;
    }

    public List<Object> get(boolean status) {
        List<Object> lstPessoa = new ArrayList<>();
        String comando = "select * from pessoa WHERE statusPessoa = ?";

        int valor = status ? 1 : 0;

        try{
            PreparedStatement stmt = new ConnectionFactory().getConnection().prepareStatement(comando);
            stmt.setInt(1, valor);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PessoaModel pessoa = new PessoaModel(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        rs.getString("dataNascimento"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("apiId"),
                        rs.getBoolean("statusPessoa")
                );
                lstPessoa.add(pessoa);
            }

            return lstPessoa;
        }catch(SQLException ex){
            Logger.getLogger(DaoPessoa.class.getName()).log(Level.SEVERE, null, ex + "Erro ao teornar lista");
        }

        return null;
    }

    public Object get(String apiId){
        PessoaModel pessoa;
        String comando = "select * from pessoa where apiId = ?";

        try{
            PreparedStatement stmt =  new ConnectionFactory().getConnection().prepareStatement(comando);
            stmt.setString(1, apiId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                pessoa = new PessoaModel(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        rs.getString("dataNascimento"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("apiId"),
                        rs.getBoolean("statusPessoa")
                );
                return pessoa;
            }

        } catch (SQLException e){
            Logger.getLogger(DaoPessoa.class.getName()).log(Level.SEVERE, null, e + "Erro ao executar busca por apiId");
            return null;
        }

        return null;
    }

    public String get (String login, String senha){
        PessoaModel pessoa;
        String comando = "select * from pessoa where login = ? and senha = ?";

        try{
            PreparedStatement stmt =  new ConnectionFactory().getConnection().prepareStatement(comando);
            stmt.setString(1, login);
            stmt.setString(1, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                pessoa = new PessoaModel(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        rs.getString("dataNascimento"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("apiId"),
                        rs.getBoolean("statusPessoa")
                );
                return pessoa.getApiId();
            }

        } catch (SQLException e){
            Logger.getLogger(DaoPessoa.class.getName()).log(Level.SEVERE, null, e + "Erro ao executar busca por apiId");
            return "";
        }

        return null;
    }
}
