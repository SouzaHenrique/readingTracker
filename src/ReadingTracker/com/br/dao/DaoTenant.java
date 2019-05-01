package ReadingTracker.com.br.dao;

import ReadingTracker.com.br.factory.ConnectionFactory;
import ReadingTracker.com.br.model.*;
import com.mysql.cj.protocol.Resultset;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoTenant implements Dao{
    @Override
    public boolean Save(Object object) {

        PessoaModel oPessoa = null;
        if(object instanceof PessoaModel){
            oPessoa = (PessoaModel) object;
        }else
        {
            return false;
        }

        TenantModel oTenant = new TenantModel();
        oTenant.setId_pessoa(oPessoa.getId());

        String comando = "insert into Tenant(id_pessoa) values (?)";

        try {
            PreparedStatement stmt = new ConnectionFactory().getConnection().prepareStatement(comando);
            stmt.setInt(1, oTenant.getId_pessoa());
            stmt.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Daoleitura.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean Update(Object object) {
        return false;
    }

    @Override
    public boolean Delete(int id) {
        return false;
    }

    @Override
    public Object get(int id) {

        TenantModel oTenant = null;
        String comando = "SELECT * FROM tenant where id = ?";

        try{
            PreparedStatement stmt = new ConnectionFactory().getConnection().prepareStatement(comando);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                oTenant = new TenantModel();
                    rs.getInt("id");
                    rs.getInt("id_pessoa");
            };
            return oTenant;

        }catch(SQLException ex){
            Logger.getLogger(DaoTenant.class.getName()).log(Level.SEVERE, null, ex + "Erro ao executar busca");
        }

        return false;
    }

    @Override
    public List<Object> get() {
        List<Object> lstTenant = new ArrayList<>();
        String comando = "SELECT * FROM tenant";

        try{
            PreparedStatement stmt = new ConnectionFactory().getConnection().prepareStatement(comando);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                TenantModel oTenant = new TenantModel(
                        rs.getInt("id"),
                        rs.getInt("id_pessoa")
                );
                lstTenant.add(oTenant);
            }

            return lstTenant;

        }catch (SQLException ex){
            Logger.getLogger(DaoTenant.class.getName()).log(Level.SEVERE, null, ex + "Erro ao executar busca");
        }

        return null;
    }
}
