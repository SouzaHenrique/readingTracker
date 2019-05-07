package readingTracker.com.br.model;

public enum opcaoLeitura {

    CRIAR(1), EDITAR(2), LISTAR(3), EXCLUIR(4);

    private int valor;

    opcaoLeitura(int valor) {
        this.valor = valor;
    }

    public int getValor(){
        return valor;
    }

}
