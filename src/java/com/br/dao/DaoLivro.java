
package java.com.br.dao;

import java.com.br.model.LivroModel;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoLivro implements Dao{
    @Override
    public boolean Save(Object object) {

        LivroModel oLivro = null;
        if (object instanceof LivroModel) {
            oLivro = (LivroModel) object;

        } else {
            return false;
        }

        String comando = "insert into Livro(titulo, autor, anoPublicacao, editora, quantidadePaginas, quantidadeLeituras) values (?,?,?,?,?,?)";

        try {
            PreparedStatement stmt = new ConnectionFactory().getConnection().prepareStatement(comando);
            stmt.setString(1, oLivro.getTitulo());
            stmt.setString(2, oLivro.getAutor());
            stmt.setDate(3, (Date) oLivro.getAnoPublicacao());
            stmt.setString(4, oLivro.getEditora());
            stmt.setInt(5, oLivro.getQuantidadePaginas());
            stmt.setLong(6, oLivro.getQuantidadeLeituras());
            stmt.execute();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(DaoLivro.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean Update(Object object) {
        return false;
    }

    @Override
    public boolean Delete(int id) {
        String comando = "delete from livro where id = ?";

        try{
            PreparedStatement stmt = new ConnectionFactory().getConnection().prepareStatement(comando);
            stmt.setInt(1, id);
            stmt.execute();
            return true;

        } catch(SQLException e){
            Logger.getLogger(DaoLivro.class.getName()).log(Level.SEVERE, null, e);
        }

        return false;
    }

    @Override
    public Object get(int id) {
        return null;
    }

    @Override
    public List<Object> get() {
        List<Object> lstLivro = new ArrayList<>();
        try {
            String comando = "select * from Livro";
            PreparedStatement stmt = new ConnectionFactory().getConnection().prepareStatement(comando);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LivroModel livro = new LivroModel(rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getDate("anoPublicacao"),
                        rs.getString("editora"),
                        rs.getInt("quantidadePaginas"),
                        rs.getLong("quantidadeLeituras"));
                lstLivro.add(livro);
            }
            return lstLivro;
        } catch (SQLException ex) {
            System.out.println("Erro ao listar Livros" + ex.getMessage());
        }

        return null;
    }
}