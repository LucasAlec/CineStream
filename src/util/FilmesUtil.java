package util;

import model.Filme;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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

    public static void exibirESelecionarFilmes(List<Filme> filmes, Scanner scanner) {
        if (filmes.isEmpty()) {
            System.out.println("⚠️ Nenhum filme encontrado.");
            return;
        }

        // Usa o map para formatar os filmes
        List<String> filmesFormatados = filmes.stream()
                .map(filme -> String.format("🎬 %s (%d) - Avaliação: %s",
                        filme.getNome(), filme.getAno(),
                        FormatoUtil.converterAvaliacaoEmEstrelas(filme.getAvaliacao())))
                .collect(Collectors.toList());

        // Exibe os filmes formatados com numeração
        System.out.println("\nFilmes encontrados:");
        for (int i = 0; i < filmesFormatados.size(); i++) {
            System.out.printf("%d - %s\n", i + 1, filmesFormatados.get(i));
        }

        // Permite que o usuário selecione um filme para ver mais informações
        System.out.print("Digite o número do filme para ver mais informações ou 's' para sair: ");
        String comando = scanner.nextLine().trim().toLowerCase();

        if (comando.equals("s")) {
            System.out.println("👋 Saindo da exibição de filmes...");
            return; // Volta ao menu
        }

        try {
            int numeroFilme = Integer.parseInt(comando);
            if (numeroFilme >= 1 && numeroFilme <= filmes.size()) {
                exibirInfoFilme(filmes.get(numeroFilme - 1));
            } else {
                System.out.println("⚠️ Número inválido. Por favor, escolha um número da lista.");
            }
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Comando inválido. Por favor, use um número.");
        }
    }

}