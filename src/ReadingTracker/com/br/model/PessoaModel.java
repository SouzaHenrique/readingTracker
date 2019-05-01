package ReadingTracker.com.br.model;

import java.util.Date;

public class PessoaModel {

    private int Id;
    private String Nome;
    private String Sobrenome;
    private String DataNascimento;
    private String Email;
    private String Senha;
    private Boolean isAtivo;

    public PessoaModel(int id, String nome, String sobrenome, String dataNascimento, String email, String senha, Boolean isAtivo) {
        Id = id;
        Nome = nome;
        Sobrenome = sobrenome;
        DataNascimento = dataNascimento;
        Email = email;
        Senha = senha;
        this.isAtivo = isAtivo;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getSobrenome() {
        return Sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        Sobrenome = sobrenome;
    }

    public String getDataNascimento() {
        return DataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        DataNascimento = dataNascimento;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    public Boolean getStatusPessoa() { return isAtivo;    }

    public void setStatusPessoa(Boolean statusPessoa) { this.isAtivo = isAtivo; }
}




