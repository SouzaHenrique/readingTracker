package java.com.br.model;

/*
*  @author Davi Lima - 24/04 18:54:35
*
*   Definição: Esta classe implementa os atributos de uma Pessoa conforme a especificação
*
* */

import java.util.Date;

public class Pessoa {

    private int Id;
    private String Nome;
    private String Sobrenome;
    private Date DataNascimento;
    private String Email;
    private String Senha;

    public Pessoa() {
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

    public Pessoa(int id, String nome, String sobrenome, Date dataNascimento, String email, String senha) {
        Id = id;
        Nome = nome;
        Sobrenome = sobrenome;
        DataNascimento = dataNascimento;
        Email = email;
        Senha = senha;
    }


}
