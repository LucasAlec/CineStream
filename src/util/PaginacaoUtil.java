package util;

import model.Filme;
import model.Serie;
import view.FilmeView;


import java.util.List;
import java.util.Scanner;

public class PaginacaoUtil {

    public static void exibirFilmesPaginados(List<Filme> filmes, Scanner scanner, FilmeView filmeView) {
        int totalFilmes = filmes.size();
        int filmesPorPagina = 3;  // Exibindo 3 filmes por página
        int paginaAtual = 0;
        boolean continuar = true;

        while (continuar) {
            int inicio = paginaAtual * filmesPorPagina;
            int fim = Math.min(inicio + filmesPorPagina, totalFilmes);

            System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.printf("           🎬 Página %d 🎬           \n", paginaAtual + 1);
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

            // Exibe os filmes com números para seleção
            for (int i = inicio; i < fim; i++) {
                // Exibe apenas o nome, ano e avaliação do filme
                System.out.printf("%d - 🎬 %s (%d) - Avaliação: %s\n",
                        i + 1,
                        filmes.get(i).getNome(),
                        filmes.get(i).getAno(),
                        FormatoUtil.converterAvaliacaoEmEstrelas(filmes.get(i).getAvaliacao()));
            }

            // Informações da página
            System.out.printf("📄 Página %d de %d\n", paginaAtual + 1, (totalFilmes + filmesPorPagina - 1) / filmesPorPagina);

            // Comandos de navegação
            System.out.print("➡️ Digite 'P' para próxima página, 'A' para anterior, ou 'S' para sair, ou o número do filme para mais informações: ");
            String comando = scanner.nextLine().toLowerCase();

            if (comando.equals("p")) {
                if (fim < totalFilmes) {
                    paginaAtual++; // Avança para a próxima página
                } else {
                    System.out.println("⚠️ Você já está na última página.");
                }
            } else if (comando.equals("a")) {
                if (paginaAtual > 0) {
                    paginaAtual--; // Retorna para a página anterior
                } else {
                    System.out.println("⚠️ Você já está na primeira página.");
                }
            } else if (comando.equals("s")) {
                continuar = false; // Sai da exibição
                System.out.println("👋 Saindo da exibição...");
            } else {
                // Verifica se o comando é um número para seleção
                try {
                    int numeroFilme = Integer.parseInt(comando);
                    if (numeroFilme > 0 && numeroFilme <= filmes.size()) {
                        Filme filmeSelecionado = filmes.get(numeroFilme - 1);
                        // Exibe informações detalhadas do filme
                        FilmesUtil.exibirInfoFilme(filmeSelecionado);

                        // Pergunta se deseja assistir ao filme
                        System.out.print("Deseja assistir a este filme? (s/n): ");
                        String assistir = scanner.nextLine().toLowerCase();
                        if (assistir.equals("s")) {
                            filmeView.assistirFilme(filmeSelecionado); // Método para assistir ao filme
                        }
                    } else {
                        System.out.println("⚠️ Número inválido. Por favor, escolha um número da lista.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("⚠️ Comando inválido. Por favor, use 'P', 'A' ou um número.");
                }
            }
        }
    }


    public static void exibirSeriesPaginadas(List<Serie> series, Scanner scanner) {
        int totalSeries = series.size();
        int seriesPorPagina = 3;  // Exibindo 3 filmes por página
        int paginaAtual = 0;
        boolean continuar = true;

        while (continuar) {
            int inicio = paginaAtual * seriesPorPagina;
            int fim = Math.min(inicio + seriesPorPagina, totalSeries);

            System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.printf("           🎬 Página %d 🎬           \n", paginaAtual + 1);
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

            for (int i = inicio; i < fim; i++) {
                SeriesUtil.exibirInfoSerie(series.get(i));
            }

            System.out.println("\n📄 Página " + (paginaAtual + 1) + " de " + ((totalSeries + seriesPorPagina - 1) / seriesPorPagina));
            System.out.print("\n➡️ Digite 'P' para próxima página, 'A' para anterior, ou 'S' para sair: ");
            String comando = scanner.nextLine().toLowerCase();

            switch (comando) {
                case "p":
                    if (fim < totalSeries) {
                        paginaAtual++;
                    } else {
                        System.out.println("⚠️ Você já está na última página.");
                    }
                    break;
                case "a":
                    if (paginaAtual > 0) {
                        paginaAtual--;
                    } else {
                        System.out.println("⚠️ Você já está na primeira página.");
                    }
                    break;
                case "s":
                    continuar = false;
                    System.out.println("👋 Saindo da exibição de filmes...");
                    break;
                default:
                    System.out.println("⚠️ Comando inválido. Por favor, use 'n', 'p' ou 's'.");
                    break;
            }
        }
    }}

}

