
//Importando a classe Livro
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private static final int ANO_PUBLICACAO_MINIMO = 1400;
    private List<Livro> acervo = new ArrayList<>(); // Criando uma lista de livros

    public Livro addLivro(Livro novoLivro) {
        if (novoLivro.getTitulo() == null || novoLivro.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("Título do livro não pode ser vazio.");
        }
        if (novoLivro.getAutor() == null || novoLivro.getAutor().trim().isEmpty()) {
            throw new IllegalArgumentException("Autor do livro não pode ser vazio.");
        }
        if (novoLivro.getAnoDePublic() < ANO_PUBLICACAO_MINIMO) {
            throw new IllegalArgumentException("Ano de publicação não pode ser menor que " + ANO_PUBLICACAO_MINIMO);
        }
        if (novoLivro.getNumPag() <= 0) {
            throw new IllegalArgumentException("Número de páginas deve ser maior que zero.");
        }
        for (Livro livro : acervo) {
            if (livro.getTitulo().equalsIgnoreCase(novoLivro.getTitulo())) {
                throw new IllegalArgumentException("Já existe um livro com esse título.");
            }
        }
        acervo.add(novoLivro);
        return novoLivro;
    }

    public List<Livro> pesquisaPorTitulo(String titulo) {
        List<Livro> livrosEncontrados = new ArrayList<>(); // Criando uma lista para armazenar os livros encontrados
        for (Livro livro : acervo) { // Percorrendo a lista de livros
            if (livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) { // Verificando se o título do livro
                                                                                  // contém a string de pesquisa
                livrosEncontrados.add(livro);
            }
        }
        return livrosEncontrados; // Retornando a lista de livros encontrados
    }

    public List<Livro> pesquisaPorAutor(String autor) {
        List<Livro> livrosEncontrados = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getAutor().toLowerCase().contains(autor.toLowerCase())) {
                livrosEncontrados.add(livro);
            }
        }
        return livrosEncontrados;
    }

    public List<Livro> pesquisaPorAno(int ano) {
        List<Livro> livrosEncontrados = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getAnoDePublic() == ano) {
                livrosEncontrados.add(livro);
            }
        }
        return livrosEncontrados;
    }

    public List<Livro> listarLivros() {
        return acervo;
    }

    public void removerLivro(Livro livro) {
        acervo.remove(livro);
    }

    public void salvarAcervoEmArquivo(String nomeArquivo) {
        if (acervo.isEmpty()) {
            System.out.println("Nenhum livro para salvar.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            // Em Java, FileWriter é uma classe para escrever em arquivos de caracteres,
            // enquanto BufferedWriter é uma classe que utiliza um buffer para otimizar a
            // escrita,
            // tornando-a mais eficiente para múltiplas escritas pequenas.
            for (Livro livro : acervo) {
                writer.write(livro.toString());
                writer.newLine();
                writer.write("--------------------------------------------------");
                writer.newLine();
            }
            System.out.println("Acervo salvo com sucesso no arquivo: " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o acervo: " + e.getMessage());
        }
    }

    public boolean existeTitulo(String titulo) {
        return acervo.stream()
                .anyMatch(livro -> livro.getTitulo().equalsIgnoreCase(titulo));
    }
}
