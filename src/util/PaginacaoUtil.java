package util;

import model.Filme;
import view.FilmeView;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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

            // Cria uma lista formatada dos filmes
            List<String> filmesFormatados = filmes.subList(inicio, fim).stream()
                    .map(filme -> String.format("🎬 %s (%d) - Avaliação: %s",
                            filme.getNome(), filme.getAno(),
                            FormatoUtil.converterAvaliacaoEmEstrelas(filme.getAvaliacao())))
                    .collect(Collectors.toList());

            // Exibe os filmes formatados
            for (int i = 0; i < filmesFormatados.size(); i++) {
                System.out.printf("%d - %s\n", inicio + i + 1, filmesFormatados.get(i));
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

}
