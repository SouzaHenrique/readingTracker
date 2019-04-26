package java.com.br.model;

import java.util.Date;

public class PessoaModel {

    private int Id;
    private String Nome;
    private String Sobrenome;
    private String DataNascimento;
    private String Email;
    private String Senha;
    private int statusPessoa;

    public PessoaModel(int id, String nome, String sobrenome, String dataNascimento, String email, String senha, int statusPessoa) {
        Id = id;
        Nome = nome;
        Sobrenome = sobrenome;
        DataNascimento = dataNascimento;
        Email = email;
        Senha = senha;
        this.statusPessoa = statusPessoa;
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

    public int getStatusPessoa() {
        return statusPessoa;
    }

    public void setStatusPessoa(int statusPessoa) {
        this.statusPessoa = statusPessoa;
    }

}




