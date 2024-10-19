package service.serie;

import database.CarregarDados;
import database.CarregarDadosSeriesImpl;
import model.Serie;
import util.QuantidadeAvaliacoesUtil;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SerieServiceImpl implements SerieService {
    private List<Serie> series;

    public SerieServiceImpl(String caminhoCsvSeries) {
        CarregarDados carregarDados = new CarregarDadosSeriesImpl(caminhoCsvSeries);
        this.series = carregarDados.carregarDadosDoArquivo();
    }

    @Override
    public List<Serie> buscarSeriePorNome(String nome) {
        return series.stream()
                .filter(serie -> serie.getNome().toLowerCase().startsWith(nome.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Serie> buscarSeriesPorAno(int ano) {
        return series.stream()
                .filter(serie -> serie.getAno() == ano)
                .collect(Collectors.toList());
    }

    @Override
    public List<Serie> sugerirSeriesPorGenero(String genero) {
        List<Serie> seriesDoGenero = series.stream()
                .filter(serie -> {
                    String[] generosSerie = serie.getGenero().split(",\\s*");
                    return List.of(generosSerie).stream()
                            .anyMatch(generoSerie -> generoSerie.equalsIgnoreCase(genero));
                })
                .collect(Collectors.toList());
        Collections.shuffle(seriesDoGenero);
        return seriesDoGenero.stream().limit(5).collect(Collectors.toList());

    }

    @Override
    public Serie encontrarMelhorSerie() {
        return series.stream()
                .reduce((s1, s2) -> {
                    double score1 = s1.getAvaliacao() * QuantidadeAvaliacoesUtil.converter(s1.getQuantidadeAvaliacoes());
                    double score2 = s2.getAvaliacao() * QuantidadeAvaliacoesUtil.converter(s2.getQuantidadeAvaliacoes());
                    return score1 > score2 ? s1 : s2;
                }).orElse(null);
    }

    // Método auxiliar para converter a quantidade de avaliações de String para número
    private double parseQuantidadeAvaliacoes(String quantidadeAvaliacoes) {
        if (quantidadeAvaliacoes.endsWith("k")) {
            return Double.parseDouble(quantidadeAvaliacoes.replace("k", "")) * 1000;
        } else if (quantidadeAvaliacoes.endsWith("M")) {
            return Double.parseDouble(quantidadeAvaliacoes.replace("M", "")) * 1_000_000;
        } else {
            try {
                return Double.parseDouble(quantidadeAvaliacoes);
            } catch (NumberFormatException e) {
                System.out.println("Erro ao converter quantidade de avaliações: " + quantidadeAvaliacoes);
                return 0;
            }
        }
    }
}
