
import java.time.LocalDate;

public abstract class Livro {// classe abstrata não pode ser instanciada
    private String titulo;
    private String autor;
    private int anoDePublic;
    private int numPag;

    @Override // @ significa que é uma anotação, ou seja, um recurso que fornece metadados
              // sobre o código
    public String toString() {
        String descricao = "Titulo: " + titulo + "\n" +
                " - Autor: " + autor + "\n" +
                " - Ano de Publicação: " + anoDePublic + "\n" +
                " - Número de Páginas: " + numPag + "\n" +
                " - Tempo de Publicação: " + calcularTempoPublicacao() + " anos\n";

        return descricao;
    }

    public final int calcularTempoPublicacao() {
        int anoAtual = LocalDate.now().getYear(); // Pega o ano atual
        return anoAtual - anoDePublic; // Calcula o tempo de publicação
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoDePublic() {
        return anoDePublic;
    }

    public void setAnoDePublic(int anoDePublic) {
        this.anoDePublic = anoDePublic;
    }

    public int getNumPag() {
        return numPag;
    }

    public void setNumPag(int numPag) {
        this.numPag = numPag;
    }

}
