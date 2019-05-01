package ReadingTracker.com.br.model;

public class LeituraModel {

    private int Id;
    private int Id_Leitor;
    private int Id_Livro;
    private int StatusLeitura;
    private int PaginasLidas;
    private String DataterminoPlanejado;

    public LeituraModel(int id, int id_Leitor, int id_Livro, int statusLeitura, int paginasLidas, String dataterminoPlanejado) {
        Id = id;
        Id_Leitor = id_Leitor;
        Id_Livro = id_Livro;
        StatusLeitura = statusLeitura;
        PaginasLidas = paginasLidas;
        DataterminoPlanejado = dataterminoPlanejado;
    }

    public LeituraModel() {
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
}
