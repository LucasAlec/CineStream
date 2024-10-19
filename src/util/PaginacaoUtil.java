package util;

import model.Filme;
import model.Serie;

import java.util.List;
import java.util.Scanner;

public class PaginacaoUtil {

    public static void exibirFilmesPaginados(List<Filme> filmes, Scanner scanner) {
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

            for (int i = inicio; i < fim; i++) {
                FilmesUtil.exibirInfoFilme(filmes.get(i));
            }

            System.out.println("\n📄 Página " + (paginaAtual + 1) + " de " + ((totalFilmes + filmesPorPagina - 1) / filmesPorPagina));
            System.out.print("\n➡️ Digite 'P' para próxima página, 'A' para anterior, ou 'S' para sair: ");
            String comando = scanner.nextLine().toLowerCase();

            switch (comando) {
                case "p":
                    if (fim < totalFilmes) {
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
