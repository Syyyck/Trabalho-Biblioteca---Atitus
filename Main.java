import java.util.List;
import java.util.Scanner;

// A camada View é a responsável por interagir com o usuário, recebendo entradas e exibindo saídas.
// Ela não deve conter lógica de negócio, apenas apresentar e solicitar informações. 
public class Main {
    private static Biblioteca biblio = new Biblioteca();

    public static void main(String[] args) {
        String menu = """

                ====== MENU ======
                Escolha uma das opções abaixo:
                1 - Adicionar novo livro
                2 - Pesquisar livro por título
                3 - Pesquisar livro por autor
                4 - Pesquisar livro por ano de publicação
                5 - Listar todos os livros
                6 - Remover livro por título
                7 - Tranformar acervo em arquivo.txt
                0 - Sair
                """;
        int opcao;
        Scanner lerTeclado = new Scanner(System.in);

        do {
            opcao = inputNumerico(lerTeclado, menu);
            switch (opcao) {
                case 1:
                    limparTela();
                    adicionarLivro(lerTeclado);
                    break;
                case 2:
                    limparTela();
                    pesquisarPorTitulo(lerTeclado);
                    break;
                case 3:
                    limparTela();
                    pesquisaPorAutor(lerTeclado);
                    break;
                case 4:
                    limparTela();
                    pesquisaPorAno(lerTeclado);
                    break;
                case 5:
                    limparTela();
                    listarTodos();
                    break;

                case 6:
                    limparTela();
                    removerPorTitulo(lerTeclado);
                    break;
                case 7:
                    limparTela();
                    transformaAcervoTxt(lerTeclado);
                    break;
                case 0:
                    limparTela();
                    System.out.println("Encerrando programa ...");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while (opcao != 0);
        lerTeclado.close();
        System.out.println("Programa encerrado.");
    }

    private static void adicionarLivro(Scanner lerTeclado) {
        String titulo = inputTitulo(lerTeclado);
        String autor = inputAutor(lerTeclado);
        int anoPublicacao = inputAno(lerTeclado);
        int numeroPaginas = inputNumeroPaginas(lerTeclado);

        Livro novoLivro;

        int tipoLivro = inputNumerico(lerTeclado, "Digite o tipo do livro (1 - Físico, 2 - Digital):");
        if (tipoLivro == 1) {
            novoLivro = new LivroFisico();

            System.out.println("Digite as dimensões do livro:");
            String dimensoes = lerTeclado.nextLine();
            int numeroExemplares = inputNumerico(lerTeclado, "Digite o número de exemplares:");

            LivroFisico novoLivroFisico = (LivroFisico) novoLivro;
            novoLivroFisico.setDimensoes(dimensoes);
            novoLivroFisico.setNumeroExemplares(numeroExemplares);
        } else {
            novoLivro = new LivroDigital();

            System.out.println("Digite o tamanho do arquivo: ");
            String tamanhoArquivo = lerTeclado.nextLine();
            System.out.println("Digite o formato do arquivo: ");
            String formatoArquivo = lerTeclado.nextLine();

            LivroDigital novoLivroDigital = (LivroDigital) novoLivro;
            novoLivroDigital.setTamanhoArquivo(tamanhoArquivo);
            novoLivroDigital.setFormatoArquivo(formatoArquivo);
        } // Polimorfismo aplicado

        novoLivro.setTitulo(titulo);
        novoLivro.setAutor(autor);
        novoLivro.setAnoDePublic(anoPublicacao);
        novoLivro.setNumPag(numeroPaginas);

        try {
            biblio.addLivro(novoLivro);
            System.out.println("Livro adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void pesquisarPorTitulo(Scanner lerTeclado) {
        System.out.println("Digite o título do livro para pesquisa:");
        String titulo = lerTeclado.nextLine();
        List<Livro> encontrados = biblio.pesquisaPorTitulo(titulo);
        if (encontrados.isEmpty()) {
            System.out.println("Nenhum livro encontrado com esse título.");
        } else {
            System.out.println("Livros encontrados:");
            for (Livro livro : encontrados) {
                System.out.println("- " + livro.getTitulo());
            }
        }
    }

    private static void listarTodos() {
        List<Livro> listaLivros = biblio.listarLivros();
        if (listaLivros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
        } else {
            System.out.println("Lista de livros:");
            for (Livro livro : listaLivros) {
                System.out.println("- " + livro.toString());
            }
        }
    }

    private static void removerPorTitulo(Scanner lerTeclado) {
        System.out.println("Digite o título do livro para remover:");
        String titulo = lerTeclado.nextLine();
        List<Livro> encontrados = biblio.pesquisaPorTitulo(titulo);
        if (encontrados.isEmpty()) {
            System.out.println("Livro não encontrado.");
        } else {
            System.out.println("Livro(s) encontrados:");

            for (int i = 0; i < encontrados.size(); i++) {
                Livro livro = encontrados.get(i);
                System.out.println((i + 1) + ". " + livro.getTitulo() + " - Autor: " + livro.getAutor());
            }
            encontrados.forEach(livro -> biblio.removerLivro(livro));
            System.out.println("Livro(s) removido(s) com sucesso!");
        }
    }

    private static void pesquisaPorAutor(Scanner lerTeclado) {
        System.out.println("Digite o nome do autor: ");
        String nomeAutor = lerTeclado.nextLine();
        List<Livro> encontrados = biblio.pesquisaPorAutor(nomeAutor);
        if (encontrados.isEmpty()) {
            System.out.println("Autor não encontrado.");
        } else {
            System.out.println("Livro(s) encontrados:");
            for (int i = 0; i < encontrados.size(); i++) {
                Livro livro = encontrados.get(i);
                System.out.println((i + 1) + ". " + livro.getTitulo() + " - Autor: " + livro.getAutor());
            }
        }
    }

    private static void pesquisaPorAno(Scanner lerTeclado) {
        System.out.println("Digite o ano que você deseja: ");
        int anoParaPesquisa = lerTeclado.nextInt();
        List<Livro> encontrados = biblio.pesquisaPorAno(anoParaPesquisa);
        if (encontrados.isEmpty()) {
            System.out.println("Ainda não foram cadastrados livros publicados neste ano. Que tal cadastrar um?.");
        } else {
            System.out.println("Livro(s) encontrados:");
            for (int i = 0; i < encontrados.size(); i++) {
                Livro livro = encontrados.get(i);
                System.out.println((i + 1) + ". " + livro.getTitulo() + " - Autor: " + livro.getAutor());
            }
        }
    }

    private static void transformaAcervoTxt(Scanner lerTeclado) {
        lerTeclado.nextLine();
        System.out.println("Digite um nome para o seu arquivo: ");
        String nomeArquivo = lerTeclado.nextLine();
        biblio.salvarAcervoEmArquivo(nomeArquivo);
    }

    private static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static int inputNumerico(Scanner lerTeclado, String mensagem) {
        int valor = 0;
        boolean entradaValida = false;
        System.out.println(mensagem);
        do {
            String valorStr = lerTeclado.nextLine();
            try {
                valor = Integer.parseInt(valorStr);
                entradaValida = true;
            } catch (Exception e) {
                System.out.println("Erro. Por favor informe um número inteiro:");
            }
        } while (!entradaValida);
        return valor;
    }

    // Para evitar que o usuário precise digitar novamente todos os dados do livro
    // caso cometa um erro, ele refaz somente o que for necessário.
    private static String inputTitulo(Scanner lerTeclado) {
        String titulo;
        do {
            System.out.println("Digite o título do livro:");
            titulo = lerTeclado.nextLine().trim(); // .trim() remove espaços em branco no início e no fim da string

            if (titulo.isEmpty()) {
                System.out.println("Título inválido. Tente novamente.");
                continue;
            }

            if (biblio.existeTitulo(titulo)) {
                System.out.println("Já existe um livro com esse título. Tente novamente.");
                titulo = null;
            }
        } while (titulo == null || titulo.isEmpty());
        return titulo;
    }

    private static String inputAutor(Scanner lerTeclado) {
        String autor;
        do {
            System.out.println("Digite o autor do livro:");
            autor = lerTeclado.nextLine();
            if (autor == null || autor.trim().isEmpty()) {
                System.out.println("Autor inválido. Tente novamente.");
            }
        } while (autor == null || autor.trim().isEmpty());
        return autor;
    }

    private static int inputAno(Scanner lerTeclado) {
        int ano = 0;
        boolean valido = false;
        do {
            ano = inputNumerico(lerTeclado, "Digite o ano de publicação:");
            if (ano >= 1400) {
                valido = true;
            } else {
                System.out.println("Ano inválido. Ano deve ser a partir de 1400.");
            }
        } while (!valido);
        return ano;
    }

    private static int inputNumeroPaginas(Scanner lerTeclado) {
        int paginas = 0;
        boolean valido = false;
        do {
            paginas = inputNumerico(lerTeclado, "Digite o número de páginas:");
            if (paginas > 0) {
                valido = true;
            } else {
                System.out.println("Número de páginas inválido. Deve ser maior que zero.");
            }
        } while (!valido);
        return paginas;
    }

}
