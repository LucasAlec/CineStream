package database;

import model.Serie;

import java.util.List;
import java.util.stream.Collectors;

public class CarregarDadosSeriesImpl implements CarregarDados<Serie> {
    private String caminhoCsvSeries;

    public CarregarDadosSeriesImpl(String caminhoCsvSeries) {
        this.caminhoCsvSeries = caminhoCsvSeries;
    }

    @Override
    public List<Serie> carregarDadosDoArquivo() {
        return DadosCSV.lerCSV(caminhoCsvSeries)
                .stream()
                .skip(1) // Ignora a primeira linha
                .map(this::converterParaSerie)
                .filter(serie -> serie != null) // Filtra séries inválidas
                .collect(Collectors.toList());
    }

    private Serie converterParaSerie(String[] dados) {
        if (dados.length < 8) {
            System.out.println("Linha inválida no CSV: dados incompletos. Dados: " + String.join(", ", dados));
            return null; // Retorna nulo para linhas inválidas
        }

        String nome = dados[0];
        int ano;
        String genero = dados[2];
        double avaliacao;
        String quantidadeAvaliacoes;
        String url = dados[3];
        String streamingService = dados[5];
        int temporadas;
        int episodios;

        try {
            ano = Integer.parseInt(dados[1]);
        } catch (NumberFormatException e) {
            System.out.println("Ano inválido para a série: " + nome);
            ano = 0; // Define um valor padrão
        }

        try {
            String[] partesRating = dados[4].split(" ");
            avaliacao = Double.parseDouble(partesRating[0]);
            quantidadeAvaliacoes = partesRating.length > 1 ? partesRating[1].replace("(", "").replace(")", "") : "0";
        } catch (Exception e) {
            System.out.println("Erro ao processar avaliações para a série: " + nome);
            avaliacao = 0.0; // Define um valor padrão
            quantidadeAvaliacoes = "0"; // Define um valor padrão
        }

        streamingService = dados[5];
        url = dados[3];

        try {
            temporadas = Integer.parseInt(dados[6]);
            episodios = Integer.parseInt(dados[7]);
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter temporadas ou episódios para a série: " + nome);
            temporadas = 0;
            episodios = 0;
        }

        return new Serie(nome, genero, avaliacao, quantidadeAvaliacoes, url, ano,streamingService, temporadas, episodios);
    }
}
