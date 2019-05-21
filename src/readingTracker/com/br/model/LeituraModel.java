package readingTracker.com.br.model;

public class LeituraModel {

    private int Id;
    private int Id_Leitor;
    private int Id_Livro;
    private int StatusLeitura;
    private int PaginasLidas;
    private String DataterminoPlanejado;
    private String TituloDoLivro;
    private int QuantidadePaginasLivro;

    public LeituraModel() {
    }

    public LeituraModel(int id, int id_Leitor, int id_Livro, int statusLeitura, int paginasLidas, String dataterminoPlanejado) {
        Id = id;
        Id_Leitor = id_Leitor;
        Id_Livro = id_Livro;
        StatusLeitura = statusLeitura;
        PaginasLidas = paginasLidas;
        DataterminoPlanejado = dataterminoPlanejado;
    }

    public LeituraModel(int id, int id_leitor, int id_livro, int statusLeitura, int paginasLidas, String dataterminoPlanejado, String tituloDoLivo, int quantidadePaginasLivro){
        Id = id;
        Id_Leitor = id_leitor;
        Id_Livro = id_livro;
        StatusLeitura = statusLeitura;
        PaginasLidas = paginasLidas;
        DataterminoPlanejado = dataterminoPlanejado;
        TituloDoLivro = tituloDoLivo;
        QuantidadePaginasLivro = quantidadePaginasLivro;
    }

    public int getId() { return Id; }

    public void setId(int id) { Id = id; }

    public int getId_Leitor() { return Id_Leitor; }

    public void setId_Leitor(int id_Leitor) { Id_Leitor = id_Leitor; }

    public int getId_Livro() { return Id_Livro; }

    public void setId_Livro(int id_Livro) { Id_Livro = id_Livro; }

    public int getStatusLeitura() { return StatusLeitura; }

    public void setStatusLeitura(int statusLeitura) { StatusLeitura = statusLeitura; }

    public int getPaginasLidas() { return PaginasLidas; }

    public void setPaginasLidas(int paginasLidas) { PaginasLidas = paginasLidas; }

    public String getDataterminoPlanejado() { return DataterminoPlanejado;  }

    public void setDataterminoPlanejado(String dataterminoPlanejado) { DataterminoPlanejado = dataterminoPlanejado; }

    public String getTituloDoLivro() {
        return TituloDoLivro;
    }

    public void setTituloDoLivro(String tituloDoLivro) {
        TituloDoLivro = tituloDoLivro;
    }

    public int getQuantidadePaginasLivro() {
        return QuantidadePaginasLivro;
    }

    public void setQuantidadePaginasLivro(int quantidadePaginasLivro) {
        QuantidadePaginasLivro = quantidadePaginasLivro;
    }
}