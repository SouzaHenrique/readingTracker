package java.com.br.dao;

import java.com.br.factory.ConnectionFactory;
import java.com.br.model.PessoaModel;
import java.com.br.model.TenantModel;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
            Logger.getLogger(DaoTenant.class.getName()).log(Level.SEVERE, null, ex);
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
        return null;
    }

    @Override
    public List<Object> get() {
        return null;
    }
}
