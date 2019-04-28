package java.com.br.dao;

import java.com.br.factory.ConnectionFactory;
import java.com.br.model.LeituraModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Daoleitura implements Dao {

    @Override
    public boolean Save(Object object) {

        LeituraModel oLeitura = null;
        if (object instanceof LeituraModel) {
            oLeitura = (LeituraModel) object;
        } else {
            return false;
        }

        String comando = "insert into Leitura(id_Leitor, id_Livro, statusLeitura, paginasLidas, dataterminoPlanejado) values (?,?,?,?,?)";


        try {
            PreparedStatement stmt = new ConnectionFactory().getConnection().prepareStatement(comando);
            stmt.setInt(1, oLeitura.getId_Leitor());
            stmt.setInt(2, oLeitura.getId_Livro());
            stmt.setInt(3, oLeitura.getStatusLeitura());
            stmt.setInt(4, oLeitura.getPaginasLidas());
            stmt.setString(5, oLeitura.getDataterminoPlanejado());
            stmt.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(DaoLivro.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean Update(Object object) {

        LeituraModel oLeitura = null;
        if(object instanceof  LeituraModel){
            oLeitura = (LeituraModel) object;
        }else{
            return false;
        }

        String comando = "update leitura set statusLeitura = ? , paginasLidas = ? , dataterminoPlanejado = ? where id_leitor = ? ";

        try{
            PreparedStatement stmt = new ConnectionFactory().getConnection().prepareStatement(comando);
            stmt.setInt(1, oLeitura.getStatusLeitura());
            stmt.setInt(2, oLeitura.getPaginasLidas());
            stmt.setString(3, oLeitura.getDataterminoPlanejado());
            stmt.setInt(4, oLeitura.getId_Leitor());
            stmt.execute();
            return true;
        }catch (SQLException ex) {
            Logger.getLogger(DaoLivro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean Delete(int id) {
        return false;
    }

    @Override
    public Object get(int id) {

        LeituraModel oLeitura;

        String comando = "select * from leitura where id = ?";

        try{
            PreparedStatement stmt = new ConnectionFactory().getConnection().prepareStatement(comando);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                oLeitura = new LeituraModel(
                        rs.getInt("id"),
                        rs.getInt("id_Leitor"),
                        rs.getInt("id_Livro"),
                        rs.getInt("statusLeitura"),
                        rs.getInt("paginasLidas"),
                        rs.getString("dataterminoPlanejado")

                );
                return oLeitura;
            }
        }catch(SQLException ex){
            Logger.getLogger(DaoPessoa.class.getName()).log(Level.SEVERE, null, ex + "Erro ao executar busca");
        }

        return null;
    }

        @Override
        public List<Object> get() {
            List<Object> lstLeitura = new ArrayList<>();
            String comando = "select * from Leitura";

            try{
                PreparedStatement stmt = new ConnectionFactory().getConnection().prepareStatement(comando);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    LeituraModel oLeitura = new LeituraModel(
                            rs.getInt("id"),
                            rs.getInt("id_Leitor"),
                            rs.getInt("id_Livro"),
                            rs.getInt("statusLeitura"),
                            rs.getInt("paginasLidas"),
                            rs.getString("dataterminoPlanejado")
                    );
                    lstLeitura.add(oLeitura);
                }

                return lstLeitura;
            }catch(SQLException ex){
                Logger.getLogger(DaoPessoa.class.getName()).log(Level.SEVERE, null, ex + "Erro ao executar busca");
            }

            return null;
        }

}