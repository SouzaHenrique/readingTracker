package readingTracker.com.br.model;

public class PessoaModel {

    private int Id;
    private String Nome;
    private String Sobrenome;
    private String DataNascimento;
    private String Email;
    private String Senha;
    private String ApiId;
    private Boolean isAtivo;

    public PessoaModel(){}

    public PessoaModel(int id, String nome, String sobrenome, String dataNascimento, String email, String senha, String apiId ,Boolean isAtivo) {
        this.setNome(nome);
        this.setSobrenome(sobrenome);
        this.setDataNascimento(dataNascimento);
        this.setEmail(email);
        this.setSenha(senha);
        this.setApiId(apiId);
        this.setAtivo(isAtivo);
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

    public String getApiId() { return ApiId; }

    public void setApiId(String apiId) {ApiId = apiId; }

    public Boolean getAtivo() {return isAtivo; }

    public void setAtivo(Boolean ativo) { isAtivo = ativo; }
}