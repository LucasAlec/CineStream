package view;

import model.Filme;
import service.filme.FilmeService;
import util.FilmesUtil;
import util.FormatoUtil;
import util.GeneroUtil;
import util.PaginacaoUtil;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
            System.out.println("|  5️⃣ → 🔄 Voltar ao menu principal                |");
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
                        continuar = false;
                        System.out.println("Voltando ao menu principal...");
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println(" ❌ Entrada inválida. Por favor, digite um número.");
            }
        }
    }

    private void buscarFilmesPorNome(Scanner scanner) {
        System.out.print("Digite o nome do filme ou parte dele para buscar: ");
        String nome = scanner.nextLine();

        List<Filme> filmesEncontrados = filmeService.buscarFilmesPorNome(nome);

        if (filmesEncontrados.isEmpty()) {
            System.out.println("⚠️ Nenhum filme encontrado com esse nome.");
        } else {
            List<String> filmesFormatados = filmesEncontrados.stream()
                    .map(filme -> String.format("🎬 %s (%d) - Avaliação: %s",
                            filme.getNome(), filme.getAno(),
                            FormatoUtil.converterAvaliacaoEmEstrelas(filme.getAvaliacao())))
                    .collect(Collectors.toList());

            PaginacaoUtil.exibirFilmesPaginados(filmesEncontrados, scanner, this);
        }
    }

    private void buscarFilmesPorAno(Scanner scanner) {
        System.out.print("Digite o ano para buscar filmes: ");
        try {
            int ano = Integer.parseInt(scanner.nextLine());
            List<Filme> filmes = filmeService.buscarFilmesPorAno(ano);
            if (filmes.isEmpty()) {
                System.out.println("Nenhum filme encontrado para o ano informado.");
            } else {
                // Chama o método de paginar filmes
                PaginacaoUtil.exibirFilmesPaginados(filmes, scanner, this);
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, digite um ano válido.");
        }
    }

    private void sugerirFilmesPorGenero(Scanner scanner) {
        List<String> generosDisponiveis = List.of("Action", "Adventure", "Comedy", "Drama",
                "Horror", "Mystery", "Sci-Fi", "Fantasy",
                "Romance", "Crime");

        GeneroUtil.exibirGeneros(generosDisponiveis);

        System.out.print("Digite o número do gênero para sugerir filmes: ");
        int escolha = Integer.parseInt(scanner.nextLine());

        if (escolha > 0 && escolha <= generosDisponiveis.size()) {
            String generoSelecionado = generosDisponiveis.get(escolha - 1);
            List<Filme> filmes = filmeService.sugerirFilmesPorGenero(generoSelecionado);

            if (!filmes.isEmpty()) {
                PaginacaoUtil.exibirFilmesPaginados(filmes, scanner, this);
            } else {
                System.out.println("⚠️ Nenhum filme encontrado para o gênero selecionado.");
            }
        } else {
            System.out.println("⚠️ Opção inválida. Por favor, escolha um número da lista.");
        }
    }

    private void mostrarMelhorFilme(Scanner scanner) {
        Filme melhorFilme = filmeService.encontrarMelhorFilme();
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
            System.out.println("Não há filmes disponíveis no momento.");
        }
    }

    public void assistirFilme(Filme filme) {
        System.out.println("\n🎬 Iniciando o filme: " + filme.getNome() + "...");
        String[] mensagens = {
                "* Limpando a prateleira de filmes...",
                "* Procurando filmes...",
                "* Organizando...",
                "* Iniciando Filme..."
        };

        for (String mensagem : mensagens) {
            System.out.println(mensagem);
            try {
                Thread.sleep(1000); // Espera 1 segundo entre cada mensagem
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("🔁 Aproveite o filme! 🎥");
        System.exit(0);
    }
}