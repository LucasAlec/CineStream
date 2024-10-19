package util;

import model.Serie;

public class SeriesUtil {
    public static void exibirInfoSerie(Serie serie) {
        String titulo = "Título: " + serie.getNome();
        String genero = "Gênero: " + GeneroUtil.obterGeneroComEmoji(serie.getGenero());
        // Exibe a avaliação com estrelas e a quantidade de avaliações entre parênteses
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
}

