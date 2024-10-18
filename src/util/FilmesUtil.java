package util;

import model.Filme;

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
        System.out.printf("| %-36s |\n", avaliacao);
        System.out.printf("| %-36s |\n", ano);
        System.out.printf("| %-36s |\n", streaming);
        System.out.printf("| %-36s |\n", duracao);
        System.out.println(linha);
    }
}
