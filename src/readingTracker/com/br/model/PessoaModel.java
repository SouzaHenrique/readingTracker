package readingTracker.com.br.model;

import java.util.Date;

public class PessoaModel {

    private int Id;
    private String Nome;
    private String Sobrenome;
    private Date DataNascimento;
    private String Email;
    private String Senha;

    public PessoaModel() {
    }

    public PessoaModel(int id, String nome, String sobrenome, Date dataNascimento, String email, String senha) {
        Id = id;
        Nome = nome;
        Sobrenome = sobrenome;
        DataNascimento = dataNascimento;
        Email = email;
        Senha = senha;
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

    public Date getDataNascimento() {
        return DataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
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




}
