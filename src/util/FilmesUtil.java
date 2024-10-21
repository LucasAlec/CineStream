package util;
import model.Filme;
import view.FilmeView;
import java.util.List;
import java.util.Scanner;

public class FilmesUtil {

    public static void exibirInfoFilme(Filme filme) {
        String titulo = "Título: " + filme.getNome();
        String genero = "Gênero: " + GeneroUtil.obterGeneroComEmoji(filme.getGenero());

        // Exibe a avaliação com estrelas e a quantidade de avaliações entre parênteses
        String avaliacao = "Avaliação: " + FormatoUtil.converterAvaliacaoEmEstrelas(filme.getAvaliacao()) +
                " (" + filme.getQuantidadeAvaliacoes().replace("(", "").replace(")", "") + ")";

        String ano = "Ano: " + filme.getAno();
        String streaming = "Streaming: " + filme.getStreamingService();
        String duracao = "Duração: " + filme.getDuracao();
        String linha = "+--------------------------------------+";

        System.out.println(linha);
        System.out.printf("| %-36s |\n", titulo);
        System.out.printf("| %-36s |\n", genero);
        System.out.printf("| %-36s \n", avaliacao);
        System.out.printf("| %-36s |\n", ano);
        System.out.printf("| %-36s |\n", streaming);
        System.out.printf("| %-36s |\n", duracao);
        System.out.println(linha);
    }

    public static void exibirTodosFilmes(List<Filme> filmes, Scanner scanner, FilmeView filmeView) {
        if (filmes.isEmpty()) {
            System.out.println("⚠️ Nenhum filme encontrado.");
            return;
        }

        int filmesPorPagina = 10; // Exibindo 3 filmes por página
        int totalPaginas = (int) Math.ceil((double) filmes.size() / filmesPorPagina);
        int paginaAtual = 0;
        boolean continuar = true;

        while (continuar) {
            int inicio = paginaAtual * filmesPorPagina;
            int fim = Math.min(inicio + filmesPorPagina, filmes.size());

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

            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

            // Informações da página
            System.out.printf("         📄 Página %d de %d\n\n", paginaAtual + 1, totalPaginas);

            // Comandos de navegação
            System.out.print("➡️ Digite 'P' para próxima página, 'A' para anterior, ou 'S' para sair, ou o número do filme para mais informações: ");
            String comando = scanner.nextLine().toLowerCase();

            if (comando.equals("p")) {
                if (fim < filmes.size()) {
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
                    if (numeroFilme > 0 && indexFilme < filmes.size()) {
                        Filme filmeSelecionado = filmes.get(indexFilme);
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