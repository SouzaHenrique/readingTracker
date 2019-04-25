package java.com.br.model;

import java.util.Date;

public class Livro {

    private int Id;
    private String Titulo;
    private String Autor;
    private Date AnoPublicacao;
    private String Editora;
    private int QuantidadePaginas;
    private long QuantidadeLeituras;

    public Livro() {
    }

    public int getId() {
        return Id;
    }

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

    public void setAutor(String autor) {
        Autor = autor;
    }

    public Date getAnoPublicacao() {
        return AnoPublicacao;
    }

    public void setAnoPublicacao(Date anoPublicacao) {
        AnoPublicacao = anoPublicacao;
    }

    public String getEditora() {
        return Editora;
    }

    public void setEditora(String editora) {
        Editora = editora;
    }

    public int getQuantidadePaginas() {
        return QuantidadePaginas;
    }

    public void setQuantidadePaginas(int quantidadePaginas) {
        QuantidadePaginas = quantidadePaginas;
    }

    public long getQuantidadeLeituras() {
        return QuantidadeLeituras;
    }

    public void setQuantidadeLeituras(long quantidadeLeituras) {
        QuantidadeLeituras = quantidadeLeituras;
    }

    public Livro(int id, String titulo, String autor, Date anoPublicacao, String editora, int quantidadePaginas, long quantidadeLeituras) {
        Id = id;
        Titulo = titulo;
        Autor = autor;
        AnoPublicacao = anoPublicacao;
        Editora = editora;
        QuantidadePaginas = quantidadePaginas;
        QuantidadeLeituras = quantidadeLeituras;
    }
}
