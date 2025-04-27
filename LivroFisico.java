public final class LivroFisico extends Livro {

    private int numeroExemplares;
    private String dimensoes;

    @Override
    public String toString() {
        String descricao = super.toString();// Chama o método toString da classe pai (Livro) para obter a descrição
                                            // básica
        descricao += " - Livro Físico" + "\n" +
                " - Dimensões do livro: " + getDimensoes() + "\n" +
                " - Número de Exemplares: " + getNumeroExemplares() + "\n";
        return descricao;
    }

    public int getNumeroExemplares() {
        return numeroExemplares;
    }

    public void setNumeroExemplares(int numeroExemplares) {
        this.numeroExemplares = numeroExemplares;
    }

    public String getDimensoes() {
        return dimensoes;
    }

    public void setDimensoes(String dimensoes) {
        this.dimensoes = dimensoes;
    }
}
