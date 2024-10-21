package util;

import model.Filme;
import model.Serie;
import view.FilmeView;
import view.SerieView;

import java.util.List;
import java.util.Scanner;

public class SeriesUtil {
    public static void exibirInfoSerie(Serie serie) {
        String titulo = "Título: " + serie.getNome();
        String genero = "Gênero: " + GeneroUtil.obterGeneroComEmoji(serie.getGenero());
        String avaliacao = "Avaliação: " + FormatoUtil.converterAvaliacaoEmEstrelas(serie.getAvaliacao()) +
                " (" + serie.getQuantidadeAvaliacoes().replace("(", "").replace(")", "") + ")";
        String ano = "Ano: " + serie.getAno();
        String streaming = "Streaming: " + serie.getStreamingService();
        String temporadas = "Temporadas: " + serie.getTemporadas();
        String episodios = "Episódios: " + serie.getEpisodios();
        String linha = "+--------------------------------------+";

        System.out.println(linha);
        System.out.printf("| %-36s |\n", titulo);
        System.out.printf("| %-36s |\n", genero);
        System.out.printf("| %-36s \n", avaliacao);
        System.out.printf("| %-36s |\n", ano);
        System.out.printf("| %-36s |\n", streaming);
        System.out.printf("| %-36s |\n", temporadas);
        System.out.printf("| %-36s |\n", episodios);
        System.out.println(linha);
    }

    public static void exibirTodasSeries(List<Serie> series, Scanner scanner, SerieView serieView) {
        if (series.isEmpty()) {
            System.out.println("⚠️ Nenhuma serie encontrado.");
            return;
        }

        int seriesPorPagina = 10; // Exibindo 3 filmes por página
        int totalPaginas = (int) Math.ceil((double) series.size() / seriesPorPagina);
        int paginaAtual = 0;
        boolean continuar = true;

        while (continuar) {
            int inicio = paginaAtual * seriesPorPagina;
            int fim = Math.min(inicio + seriesPorPagina, series.size());

            System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.printf("           🎬 Página %d 🎬           \n", paginaAtual + 1);
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

            // Exibe os filmes com números para seleção
            for (int i = inicio; i < fim; i++) {
                // Exibe apenas o nome, ano e avaliação do filme
                System.out.printf("%d - 🎬 %s (%d) - Avaliação: %s\n",
                        i + 1,
                        series.get(i).getNome(),
                        series.get(i).getAno(),
                        FormatoUtil.converterAvaliacaoEmEstrelas(series.get(i).getAvaliacao()));
            }

            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

            // Informações da página
            System.out.printf("         📄 Página %d de %d\n\n", paginaAtual + 1, totalPaginas);

            // Comandos de navegação
            System.out.print("➡️ Digite 'P' para próxima página, 'A' para anterior, ou 'S' para sair, ou o número do serie para mais informações: ");
            String comando = scanner.nextLine().toLowerCase();

            if (comando.equals("p")) {
                if (fim < series.size()) {
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
                System.out.println("👋 Saindo da exibição de filmes...");
            } else {
                // Verifica se o comando é um número para seleção
                try {
                    int numeroFilme = Integer.parseInt(comando);
                    int indexFilme = inicio + numeroFilme - 1; // Ajusta o índice para a página atual
                    if (numeroFilme > 0 && indexFilme < series.size()) {
                        Serie serieSelecionada = series.get(indexFilme);
                        // Exibe informações detalhadas do filme
                        SeriesUtil.exibirInfoSerie(serieSelecionada);

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

