package java.com.br.model;

public class TenantModel {

    private int Id;
    private int Id_pessoa;

    public TenantModel() {
    }

    public TenantModel(int id, int id_pessoa) {
        Id = id;
        Id_pessoa = id_pessoa;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getId_pessoa() {
        return Id_pessoa;
    }

    public void setId_pessoa(int id_pessoa) {
        Id_pessoa = id_pessoa;
    }


}
