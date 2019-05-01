package java.com.br.model;

import java.util.Date;


public class PessoaModel {

    private int id;
    private String nome;
    private String sobrenome;
    private String dataNascimento;
    private String email;
    private String senha;
    private int statusPessoa;
    private String apiId;

    public PessoaModel(int id, String nome, String sobrenome, String dataNascimento, String email, String senha, int statusPessoa, String apiId) {
        this.setId(id);
        this.setNome(nome);
        this.setSobrenome(sobrenome);
        this.setDataNascimento(dataNascimento);
        this.setEmail(email);
        this.setSenha(senha);
        this.setStatusPessoa(statusPessoa);
        this.setApiId(apiId);
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getStatusPessoa() {
        return statusPessoa;
    }

    public void setStatusPessoa(int statusPessoa) {
        this.statusPessoa = statusPessoa;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }
}




