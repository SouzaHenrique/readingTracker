package readingTracker.com.br.model;

public enum opcaoStatusLeitura {

        A_LER(1), LENDO(2), LIDO(3);

    private int valor;

    opcaoStatusLeitura(int valor){
        this.valor = valor;
    }

    public int getValor(){
        return valor;
    }
}
