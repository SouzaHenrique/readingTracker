package readingTracker.com.br.model;

public class LivroModel {

    private int Id;
    private String Titulo;
    private String Autor;
    private String AnoPublicacao;
    private String Editora;
    private int QuantidadePaginas;
    private long QuantidadeLeitura;

    public LivroModel() {
    }

    public LivroModel(int id, String titulo, String autor, String anoPublicacao, String editora, int quantidadePaginas, long quantidadeLeitura) {
        this.setId(id);
        this.setTitulo(titulo);
        this.setAutor(autor);
        this.setAnoPublicacao(anoPublicacao);
        this.setEditora(editora);
        this.setQuantidadePaginas(quantidadePaginas);
        this.setQuantidadeLeitura(quantidadeLeitura);
    }

    public int getId() { return Id; }

    public void setId(int id) {
        Id = id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String autor) { Autor = autor;    }

    public String getAnoPublicacao() { return AnoPublicacao; }

    public void setAnoPublicacao(String anoPublicacao) {
        AnoPublicacao = anoPublicacao;
    }

    public String getEditora() {
        return Editora;
    }

    public void setEditora(String editora) {
        Editora = editora;
    }

    public int getQuantidadePaginas() { return QuantidadePaginas; }

    public void setQuantidadePaginas(int quantidadePaginas) {
        QuantidadePaginas = quantidadePaginas;
    }

    public long getQuantidadeLeitura() {
        return QuantidadeLeitura;
    }

    public void setQuantidadeLeitura(long quantidadeLeitura) {
        QuantidadeLeitura = quantidadeLeitura;
    }


}
