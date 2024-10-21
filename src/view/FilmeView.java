package view;

import exception.AnoInvalidoException;
import exception.FilmeNaoEncontradoException;
import exception.FilmesIndisponiveisException;
import exception.OpcaoInvalidaException;
import model.Filme;
import service.filme.FilmeService;
import util.FilmesUtil;
import util.GeneroUtil;
import util.PaginacaoUtil;

import java.util.List;
import java.util.Scanner;

public class FilmeView {
    private FilmeService filmeService;

    public FilmeView(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    public void exibirFilmeView() {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n+--------------------------------------------------+");
            System.out.println("|                 🎬   Filmes   🎬                 |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|  1️⃣ → 🔎 Buscar filmes por nome                  |");
            System.out.println("|  2️⃣ → 📅 Buscar por ano de estréia               |");
            System.out.println("|  3️⃣ → 🎭 Sugerir por gênero                      |");
            System.out.println("|  4️⃣ → 🏆 Melhor filme                            |");
            System.out.println("|  5️⃣ → 📃 Listar todos os filmes                  |");
            System.out.println("|  6️⃣ → 🔄 Voltar ao menu principal                |");
            System.out.println("+--------------------------------------------------+");
            System.out.print("📝 Escolha uma opção: ");

            try {
                int opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        buscarFilmesPorNome(scanner);
                        break;
                    case 2:
                        buscarFilmesPorAno(scanner);
                        break;
                    case 3:
                        sugerirFilmesPorGenero(scanner);
                        break;
                    case 4:
                        mostrarMelhorFilme(scanner);
                        break;
                    case 5:
                        listarTodosFilmes(scanner);
                        break;
                    case 6:
                        continuar = false;
                        System.out.println("Voltando ao menu principal...");
                        break;
                    default:
                        throw new OpcaoInvalidaException("Opção inválida. Por favor, escolha uma opção válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println(" ❌ Entrada inválida. Por favor, digite um número.");
            } catch (OpcaoInvalidaException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void buscarFilmesPorNome(Scanner scanner) {
        System.out.print("Digite o nome do filme ou parte dele para buscar: ");
        String nome = scanner.nextLine();

        List<Filme> filmesEncontrados = filmeService.buscarFilmesPorNome(nome);

        try {
            if (filmesEncontrados.isEmpty()) {
                throw new FilmeNaoEncontradoException("⚠️ Nenhum filme encontrado com esse nome.");
            } else {
                // Exibe os filmes formatados com paginação
                PaginacaoUtil.exibirFilmesPaginados(filmesEncontrados, scanner, this);
            }
        } catch (FilmeNaoEncontradoException e){
            System.out.println(e.getMessage());
        }
    }

    private void buscarFilmesPorAno(Scanner scanner) {
        System.out.print("Digite o ano para buscar filmes: ");
        try {
            int ano = Integer.parseInt(scanner.nextLine());

            // o primeiro filme lançado na história foi em 1888
            if (ano < 1888 || ano > 2024) {
                throw new AnoInvalidoException("⚠\uFE0F Ano inválido. Por favor, digite um ano até 2024.");
            }

            List<Filme> filmes = filmeService.buscarFilmesPorAno(ano);
            if (filmes.isEmpty()) {
                System.out.println("Nenhum filme encontrado para o ano informado.");
            } else {
                // Chama o método de paginar filmes
                PaginacaoUtil.exibirFilmesPaginados(filmes, scanner, this);
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, digite um ano válido.");
        } catch (AnoInvalidoException e) {
            System.out.println(e.getMessage());
        }
    }

    private void sugerirFilmesPorGenero(Scanner scanner) {
        List<String> generosDisponiveis = List.of("Action", "Adventure", "Comedy", "Drama",
                "Horror", "Mystery", "Sci-Fi", "Fantasy",
                "Romance", "Crime");

        GeneroUtil.exibirGeneros(generosDisponiveis);

        System.out.print("Digite o número do gênero para sugerir filmes: ");
        int escolha = Integer.parseInt(scanner.nextLine());

        try {
            if (escolha > 0 && escolha <= generosDisponiveis.size()) {
                String generoSelecionado = generosDisponiveis.get(escolha - 1);
                List<Filme> filmes = filmeService.sugerirFilmesPorGenero(generoSelecionado);

                if (!filmes.isEmpty()) {
                    PaginacaoUtil.exibirFilmesPaginados(filmes, scanner, this);
                } else {
                    System.out.println("⚠️ Nenhum filme encontrado para o gênero selecionado.");
                }
            } else {
                throw new OpcaoInvalidaException("⚠️ Opção inválida. Por favor, escolha um número da lista.");
            }
        } catch (OpcaoInvalidaException e){
            System.out.println(e.getMessage());
        }
    }

    public void mostrarMelhorFilme(Scanner scanner) {
        Filme melhorFilme = filmeService.encontrarMelhorFilme();

        try {
            if (melhorFilme != null) {
                System.out.println("O melhor filme é:");
                FilmesUtil.exibirInfoFilme(melhorFilme);

                System.out.print("Deseja assistir a este filme? (s/n): ");
                String resposta = scanner.nextLine().trim().toLowerCase();
                if (resposta.equals("s")) {
                    assistirFilme(melhorFilme);
                } else {
                    System.out.println("🔙 Voltando ao menu principal...");
                }
            } else {
                throw new FilmesIndisponiveisException("Não há filmes disponíveis no momento.");
            }
        } catch (FilmesIndisponiveisException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listarTodosFilmes(Scanner scanner) {
        List<Filme> todosOsFilmes = filmeService.obterTodosFilmes();
        if (todosOsFilmes.isEmpty()) {
            System.out.println("⚠️ Não há filmes disponíveis.");
        } else {
            FilmesUtil.exibirTodosFilmes(todosOsFilmes, scanner, this);
        }
    }

    public void assistirFilme(Filme filme) {
        String[] mensagens = {
                "🧹 Limpando a prateleira de filmes...",
                "📦 Organizando...",
                "🔍 Procurando...",
                "✅ Filme encontrado!",
                "🎬 Iniciando o filme: " + filme.getNome() + "..."
        };

        for (String mensagem : mensagens) {
            System.out.println(mensagem);
            try {
                Thread.sleep(1000); // Espera 1 segundo entre cada mensagem
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("\n🍿 Aproveite o filme! 🎥");
        System.exit(0);
    }
}