package database;

import model.Filme;
import model.Serie;

import java.util.List;
import java.util.stream.Collectors;

public class CarregarDadosImpl implements CarregarDados {

    private String caminhoCsvFilmes;
    private String caminhoCsvSeries;

    public CarregarDadosImpl(String caminhoCsvFilmes) {
        this.caminhoCsvFilmes = caminhoCsvFilmes;
    }

    public CarregarDadosImpl(String caminhoCsvFilmes, String caminhoCsvSeries) {
        this.caminhoCsvFilmes = caminhoCsvFilmes;
        this.caminhoCsvSeries = caminhoCsvSeries;
    }

    @Override
    public List<Filme> carregarFilmes() {
        return DadosCSV.lerCSV(caminhoCsvFilmes)
                .stream()
                .skip(1) // Ignora a primeira linha
                .map(this::converterParaFilme)
                .filter(filme -> filme != null) // Filtra filmes inválidos
                .collect(Collectors.toList());
    }


    @Override
    public List<Serie> carregarSeries() {
        if (caminhoCsvSeries == null) {
            throw new UnsupportedOperationException("Caminho para séries não foi fornecido.");
        }
        return DadosCSV.lerCSV(caminhoCsvSeries)
                .stream()
                .skip(1) // Ignora a primeira linha
                .map(this::converterParaSerie)
                .collect(Collectors.toList());
    }

    public Filme converterParaFilme(String[] dados) {
        // Verifica se a linha possui o número esperado de elementos
        if (dados.length < 7) {
            System.out.println("Linha inválida no CSV: dados incompletos. Dados: " + String.join(", ", dados));
            return null; // Retorna nulo para linhas inválidas
        }

        String nome = dados[0];
        int ano;
        String genero = dados[2];
        double imdbRating;
        String quantidadeAvaliacoes; // Mantém como string
        String url = dados[4];
        String streamingService = dados[5];
        String duracao = dados[6];

        // Tenta converter o ano e trata possíveis exceções
        try {
            ano = Integer.parseInt(dados[1]);
        } catch (NumberFormatException e) {
            System.out.println("Ano inválido para o filme: " + nome);
            ano = 0; // Define um valor padrão
        }

        // Tenta converter a avaliação do IMDb
        try {
            String[] partesRating = dados[3].split(" ");
            imdbRating = Double.parseDouble(partesRating[0]); // Apenas pega a média
            quantidadeAvaliacoes = partesRating.length > 1 ? partesRating[1] : "0"; // Mantém a quantidade
        } catch (Exception e) {
            System.out.println("Erro ao processar avaliações para o filme: " + nome);
            imdbRating = 0.0; // Define um valor padrão
            quantidadeAvaliacoes = "0"; // Define um valor padrão
        }

        // Retorna um novo objeto Filme
        return new Filme(nome, ano, genero, imdbRating, quantidadeAvaliacoes, url, streamingService, duracao);
    }

    private Serie converterParaSerie(String[] dados) {
        String nome = dados[0];
        String genero = dados[1];
        double avaliacao;
        String quantidadeAvaliacoes;
        int ano;
        String streaming;
        int temporadas;
        int episodios;

        try {
            avaliacao = Double.parseDouble(dados[2]);
        } catch (NumberFormatException e) {
            System.out.println("Avaliação inválida para a série: " + nome);
            avaliacao = 0.0;
        }

        quantidadeAvaliacoes = dados[3];

        try {
            ano = Integer.parseInt(dados[4]);
        } catch (NumberFormatException e) {
            System.out.println("Ano inválido para a série: " + nome);
            ano = 0;
        }

        streaming = dados[5];

        try {
            temporadas = Integer.parseInt(dados[6]);
            episodios = Integer.parseInt(dados[7]);
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter temporadas ou episódios para a série: " + nome);
            temporadas = 0;
            episodios = 0;
        }

        return new Serie(nome, genero, avaliacao, quantidadeAvaliacoes, ano, streaming, temporadas, episodios);
    }
}