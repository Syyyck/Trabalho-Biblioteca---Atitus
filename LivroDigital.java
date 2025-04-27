public final class LivroDigital extends Livro {

    private String tamanhoArquivo;
    private String formatoArquivo;

    // chamando ano publicação

    @Override // @ significa que é uma anotação, ou seja, um recurso que fornece metadados
    // sobre o código
    public String toString() {
        String descricao = super.toString();
        descricao += " - Livro Digital" + "\n" +
                " - Formato do arquivo: " + getFormatoArquivo() + "\n" +
                " - Tamanho do arquivo: " + getTamanhoArquivo() + "\n";
        return descricao;
    }

    public String getTamanhoArquivo() {
        return tamanhoArquivo;
    }

    public void setTamanhoArquivo(String tamanhoArquivo) {
        this.tamanhoArquivo = tamanhoArquivo;
    }

    public String getFormatoArquivo() {
        return formatoArquivo;
    }

    public void setFormatoArquivo(String formatoArquivo) {
        this.formatoArquivo = formatoArquivo;
    }

}
